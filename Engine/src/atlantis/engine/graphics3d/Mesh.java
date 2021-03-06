// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d;

import java.awt.Color;
import java.util.Random;

import atlantis.engine.graphics3d.geometry.MeshGeometry;
import atlantis.framework.Vector3;

/**
 * A mesh is a 3D object composed by vertices and faces.
 * @author Yannick
 */
public class Mesh extends Object3D {
	protected Vertex[] vertices;
	protected Face3[] faces;
	protected boolean wireframe;
	protected Material material;
	
	private Mesh() {
		this.position = new Vector3();
		this.rotation = new Vector3();
		this.scale = new Vector3(1.0f);
		this.wireframe = false;
		this.material = new Material();
	}
	
	public Mesh(String name, int verticesCount, int facesCount) {
		this();
		this.name = name;
		this.vertices = new Vertex[verticesCount];
		this.faces = new Face3[facesCount];
	}
	
	public Mesh(String name, Vertex [] vertices, Face3 [] faces) {
		this();
		this.name = name;
		this.vertices = vertices;
		this.faces = faces;
	}
	
	public Mesh(String name, MeshGeometry geometry) {
		this();
		this.name = name;
		
		Vector3[] vPosition = geometry.getVertices();
		this.vertices = new Vertex[vPosition.length];
		for (int i = 0, l = vPosition.length; i < l; i++) {
			this.vertices[i] = new Vertex();
			this.vertices[i].position = vPosition[i];
		}
		
		this.faces = geometry.getFaces();
		
		computeNormals(vertices, faces);
	}
	
	public static void computeNormals(Vertex[] vertices, Face3[] faces) {
		for (int i = 0; i < vertices.length; i++) {
			vertices[i].normal = Vector3.Zero();
		}

		for (int i = 0; i < faces.length / 3; i++) {
			int index1 = faces[i * 3].a;
			int index2 = faces[i * 3].b;
			int index3 = faces[i * 3].c;

			// Select the face
			Vector3 side1 = Vector3.subtract(vertices[index1].position, vertices[index3].position);
			Vector3 side2 = Vector3.subtract(vertices[index1].position, vertices[index2].position);
			Vector3 normal = Vector3.cross(side1, side2);

			vertices[index1].normal.add(normal);
			vertices[index2].normal.add(normal);
			vertices[index3].normal.add(normal);
		}

		for (int i = 0; i < vertices.length; i++) {
			vertices[i].normal.normalize();
		}
	}
	
	// ---
	// --- Gettters and setters
	// --- 
	
	public void setFacesColor(Color color) {
		for (int i = 0, l = this.faces.length; i < l; i++) {
			this.faces[i].color = color;
		}
	}
	
	public void setFace4Color(int index, Color color) {
		int workingIndex = (index >= this.faces.length - 1) ? this.faces.length - 2 : index;
		
		this.faces[workingIndex].color = color;
		this.faces[workingIndex + 1].color = color;
	}
	
	public void randomizeFaceColor(boolean twoFaces) {
		int counter = 0;
		int step = twoFaces ? 2 : 1;
		Color current;
		
		Random random = new Random();
		Color faceAColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
		Color faceBColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
		
		for (int i = 0, l = this.faces.length - step; i < l; i += step) {
			current = (counter % 2 == 0) ? faceAColor : faceBColor;
			this.faces[i].color = current;
			if (twoFaces) {
				this.faces[i + 1].color = current;
			}
			counter++;
		}
	}
	
	public void randomizeFaceColor() {
		randomizeFaceColor(true);
	}
	
	public void randomizeHeight(Vector3 upVector) {
		Random random = new Random();
		
		for (int i = 0, l = this.faces.length - 2; i < l; i += 2) {
			float value = random.nextFloat();
			this.vertices[this.faces[i].a].position.add(Vector3.multiply(upVector, value)); 
			this.vertices[this.faces[i].b].position.add(Vector3.multiply(upVector, value)); 
			this.vertices[this.faces[i].c].position.add(Vector3.multiply(upVector, value)); 
			this.vertices[this.faces[i + 1].a].position.add(Vector3.multiply(upVector, value)); 
			this.vertices[this.faces[i + 1].b].position.add(Vector3.multiply(upVector, value)); 
			this.vertices[this.faces[i + 1].c].position.add(Vector3.multiply(upVector, value)); 
		}
	}
	
	public Vertex[] getVertices() {
		return this.vertices;
	}
	
	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}
	
	public Vertex getVertex(int index) {
		return this.vertices[index];
	}
	
	public void setVertex(int index, Vertex vertex) {
		this.vertices[index] = vertex;
	}
	
	public Face3[] getFaces() {
		return this.faces;
	}
	
	public void setFaces(Face3[] faces) {
		this.faces = faces;
	}
	
	public Face3 getFace(int index) {
		return this.faces[index];
	}
	
	public void setFace(int index, Face3 face) {
		this.faces[index] = face;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public void setWireframeMode(boolean isWireframe) {
		this.wireframe = isWireframe;
	}
	
	public boolean isWireframe() {
		return this.wireframe;
	}
}
