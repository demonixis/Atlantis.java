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
	protected Vector3[] vertices;
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
		this.vertices = new Vector3[verticesCount];
		this.faces = new Face3[facesCount];
	}
	
	public Mesh(String name, Vector3 [] vertices, Face3 [] faces) {
		this();
		this.name = name;
		this.vertices = vertices;
		this.faces = faces;
	}
	
	public Mesh(String name, MeshGeometry geometry) {
		this();
		this.name = name;
		this.vertices = geometry.getVertices();
		this.faces = geometry.getFaces();
	}
	
	// ---
	// --- Gettters and setters
	// --- 
	
	public Vector3[] getVertices() {
		return this.vertices;
	}
	
	public void setVertices(Vector3[] vertices) {
		this.vertices = vertices;
	}
	
	public Vector3 getVertex(int index) {
		return this.vertices[index];
	}
	
	public void setVertex(int index, Vector3 vertex) {
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
