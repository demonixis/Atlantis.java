// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import atlantis.framework.Matrix;
import atlantis.framework.Vector3;
import atlantis.framework.graphics.Texture2D;

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
	protected Light light;

	protected float fieldOfView;
	protected float aspectRatio;
	protected float nearClip;
	protected float farClip;
	
	private Matrix viewMatrix;
	private Matrix projectionMatrix;
	private Matrix worldMeshMatrix;
	private Matrix worldViewProjectionMatrix;
	
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
		this.backBuffer = new int[this.backBufferWidth * this.backBufferHeight * 4];
		this.depthBuffer = new float[this.backBufferWidth * this.backBufferHeight];
		this.createFrontBuffer(this.backBufferWidth, this.backBufferHeight);
		this.autoClear = autoClear;
		this.autoClearColor = Color.black;
		this.light = new Light(0, 50, 50);
		this.clear(Color.black);
		this.fieldOfView = (float) Math.PI / 4;
        this.aspectRatio = (float)this.backBufferWidth / (float)this.backBufferHeight;
        this.nearClip = 10f;
        this.farClip = 105.0f;
        this.viewMatrix = new Matrix();
        this.worldMeshMatrix = new Matrix();
        this.worldViewProjectionMatrix = new Matrix();
        this.projectionMatrix = Matrix.createPerspectiveFieldOfViewRH(this.fieldOfView, this.aspectRatio, this.nearClip, this.farClip);
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
	
	/**
	 * Gets the intensity of the light on a vertex by computing the consine of the angle
	 * between the light position and the vertex normal.
	 * @param vertex A vertex.
	 * @param lightPosition The position of the light.
	 * @return Return a value between 0 and 1.
	 */
	public float computeNDotLight(Vector3 vertex, Vector3 normal, Vector3 lightPosition) {
		Vector3 lightDirection = Vector3.subtract(lightPosition, vertex);
		normal.normalize();
		lightDirection.normalize();
		return Math.max(0,  Vector3.dot(normal, lightDirection));
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
		int index = (x + y * this.backBufferWidth);
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
	 * Draw a triangle in back buffer.
	 * @param pointA First point of the triangle.
	 * @param pointB Second point of the triangle.
	 * @param pointC Third point of the triangle.
	 * @param color The color that be used to fill the triangle on screen.
	 */
	protected void drawTriangle(Vertex vertexA, Vertex vertexB, Vertex vertexC, Color color, Texture2D texture) {
		//this.sortPoints(pointA, pointB, pointC);
		if (vertexA.position.y > vertexB.position.y) {
		    Vertex temp = vertexB;
		    vertexB = vertexA;
		    vertexA = temp;
		}

		if (vertexB.position.y > vertexC.position.y) {
			Vertex temp = vertexB;
			vertexB = vertexC;
			vertexC = temp;
		}

		if (vertexA.position.y > vertexB.position.y) {
		    Vertex temp = vertexB;
		    vertexB = vertexA;
		    vertexA = temp;
		}
		
		Vector3 pointA = vertexA.position;
		Vector3 pointB = vertexB.position;
		Vector3 pointC = vertexC.position;
		
		// Compute light
		ScanLineData data = new ScanLineData();
		
		
		float nl1 = 1.0f;
        float nl2 = 1.0f;
        float nl3 = 1.0f;
		
		if (light.enableFlatShading) {
			Vector3 vnFace = Vector3.divide(Vector3.add(Vector3.add(vertexA.normal, vertexB.normal), vertexC.normal), new Vector3(3));
			Vector3 centerPoint = Vector3.divide(Vector3.add(Vector3.add(vertexA.worldCoordinate, vertexB.worldCoordinate), vertexC.worldCoordinate), new Vector3(3));
			nl1 = computeNDotLight(centerPoint, vnFace, light.getPosition());
        }
        else {
        	nl1 = computeNDotLight(vertexA.worldCoordinate, vertexA.normal, light.getPosition());
    		nl2 = computeNDotLight(vertexB.worldCoordinate, vertexB.normal, light.getPosition());
    		nl3 = computeNDotLight(vertexC.worldCoordinate, vertexC.normal, light.getPosition());
        }
		
		// Invert slopes
		float dP1P2 = 0.0f;
		float dP1P3 = 0.0f;
		
		if (pointB.y - pointA.y > 0) {
			dP1P2 = (pointB.x - pointA.x) / (float)(pointB.y - pointA.y);
		}

		if (pointC.y - pointA.y > 0) {
			dP1P3 = (pointC.x - pointA.x) / (float)(pointC.y - pointA.y);
		}

		// First case P2 is on right
		if (dP1P2 > dP1P3) {
			for (int y = (int)pointA.y; y <= (int)pointC.y; y++) {
				data.y = y;
				if (y < pointB.y) {
					data.nDotLa = nl1;
					data.nDotLb = nl3;
					data.nDotLc = nl1;
					data.nDotLd = nl2;
					
					data.UA = vertexA.textureCoordinate.x;
                    data.UB = vertexC.textureCoordinate.x;
                    data.UC = vertexA.textureCoordinate.x;
                    data.UD = vertexB.textureCoordinate.x;

                    data.VA = vertexA.textureCoordinate.y;
                    data.VB = vertexC.textureCoordinate.y;
                    data.VC = vertexA.textureCoordinate.y;
                    data.VD = vertexB.textureCoordinate.y;
                    
					processScanLine(data, vertexA, vertexC, vertexA, vertexB, color, texture);
				}
				else {
					data.nDotLa = nl1;
					data.nDotLb = nl3;
					data.nDotLc = nl2;
					data.nDotLd = nl3;
					
					data.UA = vertexA.textureCoordinate.x;
                    data.UB = vertexC.textureCoordinate.x;
                    data.UC = vertexB.textureCoordinate.x;
                    data.UD = vertexC.textureCoordinate.x;

                    data.VA = vertexA.textureCoordinate.y;
                    data.VB = vertexC.textureCoordinate.y;
                    data.VC = vertexB.textureCoordinate.y;
                    data.VD = vertexC.textureCoordinate.y;
                    
					processScanLine(data, vertexA, vertexC, vertexB, vertexC, color, texture);
				}
			}
		}
		else { // Second case P2 is on left
			for (int y = (int)pointA.y; y <= (int)pointC.y; y++) {
				data.y = y;
				if (y < pointB.y) {
					data.nDotLa = nl1;
					data.nDotLb = nl2;
					data.nDotLc = nl1;
					data.nDotLd = nl3;
					
					data.UA = vertexA.textureCoordinate.x;
                    data.UB = vertexB.textureCoordinate.x;
                    data.UC = vertexA.textureCoordinate.x;
                    data.UD = vertexC.textureCoordinate.x;

                    data.VA = vertexA.textureCoordinate.y;
                    data.VB = vertexB.textureCoordinate.y;
                    data.VC = vertexA.textureCoordinate.y;
                    data.VD = vertexC.textureCoordinate.y;
                    
					processScanLine(data, vertexA, vertexB, vertexA, vertexC, color, texture);
				}
				else {
					data.nDotLa = nl2;
					data.nDotLb = nl3;
					data.nDotLc = nl1;
					data.nDotLd = nl3;
					
					data.UA = vertexB.textureCoordinate.x;
                    data.UB = vertexC.textureCoordinate.x;
                    data.UC = vertexA.textureCoordinate.x;
                    data.UD = vertexC.textureCoordinate.x;

                    data.VA = vertexB.textureCoordinate.y;
                    data.VB = vertexC.textureCoordinate.y;
                    data.VC = vertexA.textureCoordinate.y;
                    data.VD = vertexC.textureCoordinate.y;
                    
					processScanLine(data, vertexB, vertexC, vertexA, vertexC, color, texture);
				}
			}
		} 
	}
	
	protected void processScanLine(ScanLineData data, Vertex vertexA, Vertex vertexB, Vertex vertexC, Vertex vertexD, Color color, Texture2D texture) {
		Vector3 pointA = vertexA.position;
		Vector3 pointB = vertexB.position;
		Vector3 pointC = vertexC.position;
		Vector3 pointD = vertexD.position;
		
		float gradiant1 = (pointA.y != pointB.y) ? (data.y - pointA.y) / (pointB.y - pointA.y) : 1;
		float gradiant2 = (pointC.y != pointD.y) ? (data.y - pointC.y) / (pointD.y - pointC.y) : 1;
		
		// Start/End position of the line
		int startX = (int)interpolate(pointA.x, pointB.x, gradiant1);
		int endX = (int)interpolate(pointC.x, pointD.x, gradiant2);

		// Start/End z
		float z1 = interpolate(pointA.z, pointB.z, gradiant1);
		float z2 = interpolate(pointC.z, pointD.z, gradiant2);
		
		float startNormal = interpolate(data.nDotLa, data.nDotLb, gradiant1);
        float endNormal = interpolate(data.nDotLc, data.nDotLd, gradiant2);
		
        float startU = interpolate(data.UA, data.UB, gradiant1);
        float endU = interpolate(data.UC, data.UD, gradiant2);
        float startV = interpolate(data.VA, data.VB, gradiant1);
        float endV = interpolate(data.VC, data.VD, gradiant2);
        
        // Temp var
		float z = Float.MIN_VALUE;
		float gradiant = 0.0f;
		float lightFactor = 1.0f;
		Color vertexColor = color;
	
		for (int x = startX; x < endX; x++) {
			gradiant = ((float)(x - startX) / (float)(endX - startX)); 
			z = interpolate(z1, z2, gradiant);
			
			if (this.light.enabled) {
				lightFactor = light.isEnableFlatShading() ? data.nDotLa : interpolate(startNormal, endNormal, gradiant);
				vertexColor = colorAddValue(color, lightFactor, false);
			}
			
			float u = interpolate(startU, endU, gradiant);
			float v = interpolate(startV, endV, gradiant);
			 
			if (texture != null) {
				vertexColor = colorAddColor(vertexColor, texture.getColorUV(u, v), false);
			}
			
			this.drawPoint(x, data.y, z, vertexColor);
		}
	}
	
	private Color colorAddValue(Color color, float value, boolean multiplyAlpha) {
		float r = (float)color.getRed() / 255.0f;
		float g = (float)color.getGreen() / 255.0f;
		float b = (float)color.getBlue() / 255.0f;
		float a = (float)color.getAlpha() / 255.0f;

		float nr = (r * value) % 255;
		float ng = (g * value) % 255;
		float nb = (b * value) % 255;
		float na = (a * value) % 255;

		if (!multiplyAlpha) {
			na = (float)color.getAlpha() / 255.0f;
		}

		return new Color(nr, ng, nb, na);
	}
	
	private Color colorAddColor(Color color1, Color color2, boolean multiplyAlpha) {
		float r1 = (float)color1.getRed() / 255.0f;
		float g1 = (float)color1.getGreen() / 255.0f;
		float b1 = (float)color1.getBlue() / 255.0f;
		float a1 = (float)color1.getAlpha() / 255.0f;
		
		float r2 = (float)color2.getRed() / 255.0f;
		float g2 = (float)color2.getGreen() / 255.0f;
		float b2 = (float)color2.getBlue() / 255.0f;
		float a2 = (float)color2.getAlpha() / 255.0f;

		float nr = (r1 * r2) % 255;
		float ng = (g1 * g2) % 255;
		float nb = (b1 * b2) % 255;
		float na = (a1 * a2) % 255;
		
		if (!multiplyAlpha) {
			na = (float)color1.getAlpha() / 255.0f;
		}

		return new Color(nr, ng, nb, na);
	}
	
	/**
	 * Gets 2D coordinates from 3D coordinates.
	 * @param coordinates 
	 * @param transformMatrix
	 * @return Return 2D coordinates.
	 */
	protected Vertex project(Vertex vertex, Matrix transformMatrix, Matrix worldMatrix) {
		Vector3 point2d = Vector3.transformCoordinate(vertex.position, transformMatrix);
		Vector3 point3d = Vector3.transformCoordinate(vertex.position, worldMatrix);
		Vector3 normal3d = Vector3.transformCoordinate(vertex.normal, worldMatrix);
		
		Vector3 projection = new Vector3();
		projection.x = point2d.x * this.backBufferWidth + (this.backBufferWidth / 2.0f);
		projection.y = -point2d.y * this.backBufferHeight + (this.backBufferHeight / 2.0f);
		projection.z = point2d.z;
		
		return new Vertex(projection, normal3d, point3d, vertex.textureCoordinate);
	}
	
	/**
	 * Render the scene to back buffer.
	 * @param camera The current camera.
	 * @param meshes A collection of 3D objects. (will be a scene later)
	 */
	protected void internalRender(Camera camera, Mesh[] meshes) {
		this.viewMatrix = camera.getViewMatrix();
	
		/*
		this.viewMatrix = Matrix.createLookAt(camera.position, camera.target, Vector3.Up());
		Quaternion rotation = Quaternion.createFromRotationMatrix(Matrix.invert(this.viewMatrix));
		Matrix matrix = Matrix.createFromQuaternion(rotation);
		matrix.setTranslation(camera.position);
		this.viewMatrix = Matrix.invert(matrix);
		*/
		for (int i = 0, l = meshes.length; i < l; i++) {
			this.worldMeshMatrix = Matrix.multiply(
					Matrix.createScale(meshes[i].scale), 
					Matrix.createRotationYawPitchRoll(meshes[i].rotation.y, meshes[i].rotation.x, meshes[i].rotation.z),
					Matrix.createTranslation(meshes[i].position));
			this.worldViewProjectionMatrix = Matrix.multiply(this.worldMeshMatrix, this.viewMatrix, this.projectionMatrix);
			
			for (int j = 0, m = meshes[i].faces.length; j < m; j++) {
				 Vertex vertA = meshes[i].getVertex(meshes[i].faces[j].a);
                 Vertex vertB = meshes[i].getVertex(meshes[i].faces[j].b);
                 Vertex vertC = meshes[i].getVertex(meshes[i].faces[j].c);

                 Vertex pVertA = project(vertA, this.worldViewProjectionMatrix, this.worldMeshMatrix);
                 Vertex pVertB = project(vertB, this.worldViewProjectionMatrix, this.worldMeshMatrix);
                 Vertex pVertC = project(vertC, this.worldViewProjectionMatrix, this.worldMeshMatrix);
                 
                 if (meshes[i].isWireframe()) {
		             drawLine(pVertA.position, pVertB.position, meshes[i].faces[j].color);
		             drawLine(pVertB.position, pVertC.position, meshes[i].faces[j].color);
		             drawLine(pVertC.position, pVertA.position, meshes[i].faces[j].color);
                 }
                 else {
                	 this.drawTriangle(pVertA, pVertB, pVertC, meshes[i].faces[j].color, meshes[i].getMaterial().getTexture());
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
	
	public Light getLight() {
		return this.light;
	}
}
