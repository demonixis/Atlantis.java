// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d;

import java.awt.Color;

import atlantis.engine.graphics3d.geometry.MeshGeometry;
import atlantis.framework.Vector3;

/**
 * A mesh is a 3D object composed by vertices and faces.
 * @author Yannick
 */
public class Mesh {
	protected String name;
	protected Vertex[] vertices;
	protected Face3[] faces;
	public Color color;
	public Vector3 position;
	public Vector3 rotation;
	public Vector3 scale;
	protected boolean wireframe;
	
	private Mesh() {
		this.position = new Vector3();
		this.rotation = new Vector3();
		this.scale = new Vector3(1.0f);
		this.color = Color.yellow;
		this.wireframe = false;
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
	
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	public void setRotation(Vector3 rotation) {
		this.rotation = rotation;
	}
	
	public void setScale(Vector3 scale) {
		this.scale = scale;
	}
	
	public void setWireframeMode(boolean isWireframe) {
		this.wireframe = isWireframe;
	}
	
	public boolean isWireframe() {
		return this.wireframe;
	}
}
