package atlantis.engine.graphics3d;

import java.awt.Color;
import java.awt.Graphics;
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
public class Renderer {
	private BufferedImage frontBuffer;
	private int[] backBuffer;
	protected int width;
	protected int height;
	protected int backBufferWidth;
	protected int backBufferHeight;
	protected Color pixelColor;
	protected Color autoClearColor;
	protected boolean autoClear;
	
	/**
	 * Create a software renderer. The front and back buffer have the same size.
	 * The auto clear flags is disable so you must call clear method before each draw.
	 * @param width Desired width.
	 * @param height Desired height.
	 */
	public Renderer(int width, int height) {
		this(width, height, width, height, false);
	}
	
	/**
	 * Create a software renderer. 
	 * @param screenWidth Screen width.
	 * @param screenHeight Screen height.
	 * @param backBufferWidth Back buffer width.
	 * @param backBufferHeight Back buffer height.
	 * @param autoClear Enable or disable auto clear.
	 */
	public Renderer(int screenWidth, int screenHeight, int backBufferWidth, int backBufferHeight, boolean autoClear) {
		this.width = screenWidth;
		this.height = screenHeight;
		this.backBufferWidth = backBufferWidth;
		this.backBufferHeight = backBufferHeight;
		// Alpha Blue Green Red
		this.backBuffer = new int[this.backBufferWidth * this.backBufferHeight * 4];
		this.createFrontBuffer(this.width, this.height);
		this.autoClear = autoClear;
		this.autoClearColor = Color.black;
	}
	
	/**
	 * Create the front buffer with the specified size.
	 * @param width Desired width.
	 * @param height Desired height.
	 */
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
	protected void present() {
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
	protected void drawPoint(int x, int y) {
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
	protected void drawPixel(int x, int y, Color color) {
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
	protected void drawLine(Vector2 pointA, Vector2 pointB) {
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
	protected Vector2 project(Vector3 coordinates, Matrix transformMatrix) {
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
	protected void internalRender(Camera camera, Mesh[] meshes) {
		Matrix view = camera.getViewMatrix();
		Matrix projection = Matrix.createPerspetiveFieldOfViewRightHand((float)(MathHelper.Pi / 4), (float)((float)this.width / (float)this.height), 0.01f, 1.0f);
		
		for (int i = 0, l = meshes.length; i < l; i++) {
			Matrix scale = Matrix.createScale(meshes[i].scale);
			Matrix rotation = Matrix.multiply(Matrix.createRotationX(meshes[i].rotation.x), Matrix.createRotationY(meshes[i].rotation.y));
			Matrix translation = Matrix.createTranslation(meshes[i].position);
			Matrix world = Matrix.multiply(scale, rotation, translation);
			
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
	
	/**
	 * Render a scene to the screen.
	 * @param graphics The graphics context.
	 * @param camera The active camera to use.
	 * @param meshes A collection of meshes. // TODO will be a scene object later.
	 */
	public void render(Graphics graphics, Camera camera, Mesh[] meshes) {
		if (this.autoClear) {
			this.clear(this.autoClearColor);
		}
		this.internalRender(camera, meshes);
		this.present();
		graphics.drawImage(this.frontBuffer, 0, 0, this.width, this.height, null);
	}
	
	// ---
	// --- Getters and setters
	// ---
	
	/**
	 * Gets the front buffer used by the renderer.
	 * @return Return the buffered image used for front buffer. 
	 */
	public BufferedImage getFrontBuffer() {
		return this.frontBuffer;
	}
	
	/**
	 * Enable or disable auto clear before each draw.
	 * @param autoClear Sets to true to enable auto clear.
	 */
	public void setAutoClear(boolean autoClear) {
		this.autoClear = autoClear;
	}
	
	public boolean isAutoClear() {
		return this.autoClear;
	}
	
	/**
	 * Sets the color used to auto clear the screen.
	 * @param color Desired clear color.
	 */
	public void setAutoClearColor(Color color) {
		this.autoClearColor = color;
	}
	
	/**
	 * Gets the color used to auto clear the screen.
	 * @return Return the color used to clear the screen.
	 */
	public Color getAutoClearColor() {
		return this.autoClearColor;
	}
}
