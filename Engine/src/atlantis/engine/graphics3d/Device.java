package atlantis.engine.graphics3d;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import atlantis.framework.MathHelper;
import atlantis.framework.Matrix;
import atlantis.framework.Vector2;
import atlantis.framework.Vector3;

/**
 * The graphics device. It's responsible to draw 3D object on screen. 
 * @author Yannick
 */
public class Device {
	private BufferedImage frontBuffer;
	private int[] backBuffer;
	protected int width;
	protected int height;
	protected Color pixelColor;
	
	public Device(int width, int height) {
		this.width = width;
		this.height = height;
		// Alpha Blue Green Red
		this.backBuffer = new int[this.width * this.height * 4];
		this.createFrontBuffer(this.width, this.height);
	}
	
	protected void createFrontBuffer(int width, int height) {
		BufferedImage frontBuffer = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		DataBufferByte buffer = (DataBufferByte)frontBuffer.getRaster().getDataBuffer();
		
		for (int i = 0, l = buffer.getSize(); i < l; i += 4) {
			buffer.setElem(i, 255);
			buffer.setElem(i + 1, 0);
			buffer.setElem(i + 2, 0);
			buffer.setElem(i + 3, 0);
		}
		
		this.frontBuffer = frontBuffer;
	}
	
	public BufferedImage getFrontBuffer() {
		return this.frontBuffer;
	}
	
	/**
	 * Clear the screen with a specified color.
	 * @param color Desired color to clear the screen.
	 */
	public void clear(Color color) {
		for (int i = 0, l = this.backBuffer.length; i < l; i += 4) {
			this.backBuffer[i] = color.getAlpha();
			this.backBuffer[i + 1] = color.getBlue();
			this.backBuffer[i + 2] = color.getGreen();
			this.backBuffer[i + 3] = color.getRed();
		}
	}
	
	/**
	 * Flush the backBuffer to frontBuffer.
	 */
	public void present() {
		DataBufferByte buffer = (DataBufferByte)this.frontBuffer.getRaster().getDataBuffer();

		for (int i = 0, l = backBuffer.length; i < l; i += 4) { 
			buffer.setElem(i, backBuffer[i]);
			buffer.setElem(i + 1, backBuffer[i + 1]);
			buffer.setElem(i + 2, backBuffer[i + 2]);
			buffer.setElem(i + 3, backBuffer[i + 3]);
		}
	}
	
	/**
	 * Draw a point to the back buffer and test if it's visible.
	 * @param x Value of X coordinate.
	 * @param y Value of Y coordinate.
	 */
	public void drawPoint(int x, int y) {
		// Show only if it visible.
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            drawPixel(x, y, this.pixelColor);
        }
	}
	
	/**
	 * Draw a pixel to the back buffer.
	 * @param x Value of X coordinate.
	 * @param y Value of Y coordinate.
	 * @param color Desired pixel color for this pixel.
	 */
	public void drawPixel(int x, int y, Color color) {
		int index = (x + y * this.width) * 4;
		this.backBuffer[index] = color.getAlpha();
		this.backBuffer[index + 1] = color.getBlue();
		this.backBuffer[index + 2] = color.getGreen();
		this.backBuffer[index + 3] = color.getRed();
	}
	
	/**
	 * Draw a line with two Vector2 using Bresenham's line algorithm.
	 * @param pointA Start point.
	 * @param pointB End point.
	 */
	public void drawLine(Vector2 pointA, Vector2 pointB) {
        int x0 = (int)pointA.x;
        int x1 = (int)pointB.x;
        int y0 = (int)pointA.y;
        int y1 = (int)pointB.y;

        int distanceX = Math.abs(x1 - x0);
        int distanceY = Math.abs(y1 - y0);

        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = distanceX - distanceY;

        boolean drawing = true;

        while (drawing) {
            drawPoint(x0, y0);

            if ((x0 == x1) && (y0 == y1)) {
                drawing = false;
            }

            int e2 = 2 * err;

            if (e2 > -distanceY) {
                err -= distanceY;
                x0 += sx;
            }

            if (e2 < distanceX) {
                err += distanceX;
                y0 += sy;
            }
        }
    }
	
	/**
	 * Gets 2D coordinates from 3D coordinates.
	 * @param coordinates 
	 * @param transformMatrix
	 * @return Return 2D coordinates.
	 */
	public Vector2 project(Vector3 coordinates, Matrix transformMatrix) {
		Vector3 point = Vector3.transformCoordinate(coordinates, transformMatrix);
		Vector2 projection = new Vector2();
		projection.x = point.x * this.width + (this.width / 2);
		projection.y = -point.y * this.height + (this.height / 2);
		return projection;
	}
	
	/**
	 * Render the scene to back buffer.
	 * @param camera The current camera.
	 * @param meshes A collection of 3D objects. (will be a scene later)
	 */
	public void render(Camera camera, Mesh[] meshes) {
		Matrix view = Matrix.createLookAt(camera.position, camera.target, Vector3.getUnitY());
		Matrix projection = Matrix.createPerspectiveFieldOfView((float)(MathHelper.Pi / 4), (float)((float)this.width / (float)this.height), 0.01f, 1.0f);
		
		//Matrix rotationMatrix = Matrix.multiply(Matrix.createRotationX(camera.rotation.x), Matrix.createRotationY(camera.rotation.y));
		//Matrix world = Matrix.multiply(rotationMatrix, Matrix.createTranslation(camera.position));
		
		for (int i = 0, l = meshes.length; i < l; i++) {
			Matrix rotation = Matrix.multiply(Matrix.createRotationX(meshes[i].rotation.x), Matrix.createRotationY(meshes[i].rotation.y));
			Matrix translation = Matrix.createTranslation(meshes[i].position);
			Matrix transform = Matrix.multiply(rotation, translation);
			Matrix scale = Matrix.createScale(meshes[i].scale);
			Matrix world = Matrix.multiply(scale, rotation, transform);
			
			Matrix worldViewProject = Matrix.multiply(world, view, projection);
			
			this.pixelColor = meshes[i].color;
			
			for (int j = 0, m = meshes[i].faces.length; j < m; j++) {
				 Vector3 vecA = meshes[i].getVertex(meshes[i].faces[j].a);
                 Vector3 vecB = meshes[i].getVertex(meshes[i].faces[j].b);
                 Vector3 vecC = meshes[i].getVertex(meshes[i].faces[j].c);

                 Vector2 pointA = project(vecA, worldViewProject);
                 Vector2 pointB = project(vecB, worldViewProject);
                 Vector2 pointC = project(vecC, worldViewProject);
                 
                 drawLine(pointA, pointB);
                 drawLine(pointB, pointC);
                 drawLine(pointC, pointA);
			}
		}
		
	}
}
