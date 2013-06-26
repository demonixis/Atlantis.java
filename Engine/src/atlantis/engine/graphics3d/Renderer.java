// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import atlantis.framework.MathHelper;
import atlantis.framework.Matrix;
import atlantis.framework.Vector3;

/**
 * The graphics device. It's responsible to draw 3D object on screen. 
 * @author Yannick
 */
public class Renderer {
	private BufferedImage frontBuffer;
	private int[] backBuffer;
	private float[] depthBuffer;
	protected int width;
	protected int height;
	protected int backBufferWidth;
	protected int backBufferHeight;
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
		this.depthBuffer = new float[this.backBufferWidth * this.backBufferHeight];
		this.createFrontBuffer(this.width, this.height);
		this.autoClear = autoClear;
		this.autoClearColor = Color.black;
		this.clear(Color.black);
	}
	
	// ---
	// --- Helper methods
	// ---
	
	protected float clamp(float value) {
		return Math.max(0, Math.min(value, 1));
	}
	
	protected float interpolate(float min, float max, float gradiant) {
		return min + (max - min) * clamp(gradiant);
	}
	
	protected void swap(Vector3 vectorA, Vector3 vectorB) {
		Vector3 temp = new Vector3(vectorB);
		vectorB.set(vectorA.x, vectorA.y, vectorA.z);
		vectorB.set(temp.x, temp.y, temp.z); 
	}
	
	// ---
	// --- Draw methods
	// ---
	
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
	 * Clear the screen with a specified color and reset the depth buffer.
	 * @param color Desired color to clear the screen.
	 */
	public void clear(Color color) {
		for (int i = 0, l = this.backBuffer.length; i < l; i += 4) {
			this.backBuffer[i] = color.getAlpha();
			this.backBuffer[i + 1] = color.getBlue();
			this.backBuffer[i + 2] = color.getGreen();
			this.backBuffer[i + 3] = color.getRed();
		}
		
		for (int i = 0, l = this.depthBuffer.length; i < l; i++) {
			this.depthBuffer[i] = Float.MAX_VALUE;
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
	protected void drawPoint(int x, int y, float z, Color color) {
		// Show only if it visible.
        if (x >= 0 && x < this.backBufferWidth && y >= 0 && y < this.backBufferHeight) {
            drawPixel(x, y, z, color);
        }
	}
	
	/**
	 * Draw a pixel to the back buffer.
	 * @param x Value of X coordinate.
	 * @param y Value of Y coordinate.
	 * @param color Desired pixel color for this pixel.
	 */
	protected void drawPixel(int x, int y, float z, Color color) {
		int index = (x + y * this.width);
		int index4 = index * 4;
		
		if (this.depthBuffer[index] < z) {
			return;
		}
		
		this.depthBuffer[index] = z;
		
		this.backBuffer[index4] = color.getAlpha();
		this.backBuffer[index4 + 1] = color.getBlue();
		this.backBuffer[index4 + 2] = color.getGreen();
		this.backBuffer[index4 + 3] = color.getRed();
	}
	
	/**
	 * Draw a line with two Vector2 using Bresenham's line algorithm.
	 * @param pointA Start point.
	 * @param pointB End point.
	 */
	protected void drawLine(Vector3 pointA, Vector3 pointB, Color color) {
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
            drawPoint(x0, y0, Float.MAX_VALUE, color);

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
	 * Sort points of a triangle to have PA -> PB -> PC (in this order)
	 * @param pointA First point of the triangle.
	 * @param pointB Second point of the triangle.
	 * @param pointC Third point of the triangle.
	 */
	protected void sortPoints(Vector3 pointA, Vector3 pointB, Vector3 pointC) {
		if (pointA.y > pointB.y) {
		    Vector3 temp = pointB;
		    pointB = pointA;
		    pointA = temp;
		}
		
		if (pointB.y > pointC.y) {
			Vector3 temp = pointC;
			pointC = pointB;
			pointB = temp;
		}
		
		if (pointA.y > pointB.y) {
		    Vector3 temp = pointB;
		    pointB = pointA;
		    pointA = temp;
		}
	}
	
	/**
	 * Draw a triangle in back buffer.
	 * @param pointA First point of the triangle.
	 * @param pointB Second point of the triangle.
	 * @param pointC Third point of the triangle.
	 * @param color The color that be used to fill the triangle on screen.
	 */
	protected void drawTriangle(Vector3 pointA, Vector3 pointB, Vector3 pointC, Color color) {
		// Sorting points for having P1 -> P2 -> P3 
		//this.sortPoints(pointA, pointB, pointC);
		if (pointA.y > pointB.y) {
		    Vector3 temp = pointB;
		    pointB = pointA;
		    pointA = temp;
		}
		
		if (pointB.y > pointC.y) {
			Vector3 temp = pointC;
			pointC = pointB;
			pointB = temp;
		}
		
		if (pointA.y > pointB.y) {
		    Vector3 temp = pointB;
		    pointB = pointA;
		    pointA = temp;
		}

		// Invert slopes
		float dP1P2 = 0.0f;
		float dP1P3 = 0.0f;

		if (pointB.y - pointA.y > 0) {
			dP1P2 = (pointB.x - pointA.x) / (pointB.y - pointA.y);
		}

		if (pointC.y - pointA.y > 0) {
			dP1P3 = (pointC.x - pointA.x) / (pointC.y - pointA.y);
		}

		// First case P2 is on right
		if (dP1P2 > dP1P3) {
			for (int y = (int)pointA.y; y <= (int)pointC.y; y++) {
				if (y < pointB.y) {
					processScanLine(y, pointA, pointC, pointA, pointB, color);
				}
				else {
					processScanLine(y, pointA, pointC, pointB, pointC, color);
				}
			}
		}
		else { // Second case P2 is on left
			for (int y = (int)pointA.y; y < (int)pointC.y; y++) {
				if (y < pointB.y) {
					processScanLine(y, pointA, pointB, pointA, pointC, color);
				}
				else {
					processScanLine(y, pointB, pointC, pointA, pointC, color);
				}
			}
		} 
	}
	
	protected void processScanLine(int y, Vector3 pointA, Vector3 pointB, Vector3 pointC, Vector3 pointD, Color color) {
		float gradiant1 = pointA.y != pointB.y ? (y - pointA.y) / (pointB.y - pointA.y) : 1;
		float gradiant2 = pointC.y != pointD.y ? (y - pointC.y) / (pointD.y - pointC.y) : 1;
		
		int startX = (int)interpolate(pointA.x, pointB.x, gradiant1);
		int endX = (int)interpolate(pointC.x, pointD.x, gradiant2);
		
		float z1 = interpolate(pointA.z, pointB.z, gradiant1);
		float z2 = interpolate(pointC.z, pointD.z, gradiant2);
		float z = Float.MIN_VALUE;
		float zGradiant = 0.0f;
		
		for (int x = startX; x < endX; x++) {
			zGradiant = ((float)(x - startX) / (float)(endX - startX)); 
			z = interpolate(z1, z2, zGradiant);
			this.drawPoint(x, y, z, color);
		}
	}
	
	/**
	 * Gets 2D coordinates from 3D coordinates.
	 * @param coordinates 
	 * @param transformMatrix
	 * @return Return 2D coordinates.
	 */
	protected Vector3 project(Vector3 coordinates, Matrix transformMatrix) {
		Vector3 point = Vector3.transformCoordinate(coordinates, transformMatrix);
		Vector3 projection = new Vector3();
		projection.x = point.x * this.width + (this.width / 2);
		projection.y = -point.y * this.height + (this.height / 2);
		projection.z = point.z;
		return projection;
	}
	
	/**
	 * Render the scene to back buffer.
	 * @param camera The current camera.
	 * @param meshes A collection of 3D objects. (will be a scene later)
	 */
	protected void internalRender(Camera camera, Mesh[] meshes) {
		Matrix view = camera.getViewMatrix();
		Matrix projection = Matrix.createPerspetiveFieldOfViewRH((float)(MathHelper.Pi / 4), (float)((float)this.width / (float)this.height), 0.01f, 1.0f);
		
		for (int i = 0, l = meshes.length; i < l; i++) {
			Matrix scale = Matrix.createScale(meshes[i].scale);
			//Matrix rotation = Matrix.multiply(Matrix.createRotationX(meshes[i].rotation.x), Matrix.createRotationY(meshes[i].rotation.y));
			Matrix rotation = Matrix.createRotationYawPitchRoll(meshes[i].rotation.y, meshes[i].rotation.x, meshes[i].rotation.z);
			
			Matrix translation = Matrix.createTranslation(meshes[i].position);
			Matrix world = Matrix.multiply(scale, rotation, translation);
			
			Matrix worldViewProject = Matrix.multiply(world, view, projection);
			
			for (int j = 0, m = meshes[i].faces.length; j < m; j++) {
				 Vector3 vecA = meshes[i].getVertex(meshes[i].faces[j].a);
                 Vector3 vecB = meshes[i].getVertex(meshes[i].faces[j].b);
                 Vector3 vecC = meshes[i].getVertex(meshes[i].faces[j].c);

                 Vector3 pointA = project(vecA, worldViewProject);
                 Vector3 pointB = project(vecB, worldViewProject);
                 Vector3 pointC = project(vecC, worldViewProject);
                 
                 if (meshes[i].isWireframe()) {
		             drawLine(pointA, pointB, meshes[i].color);
		             drawLine(pointB, pointC, meshes[i].color);
		             drawLine(pointC, pointA, meshes[i].color);
                 }
                 else {
                	 this.drawTriangle(pointA, pointB, pointC, meshes[i].color);
                 }
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
