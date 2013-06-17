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
	
	private Mesh() {
		this.position = new Vector3();
		this.rotation = new Vector3();
		this.scale = Vector3.One;
	}
	
	public Mesh(String name, Vector3 [] vertices, Face3 [] faces) {
		this();
		this.name = name;
		this.vertices = vertices;
		this.faces = faces;
		this.color = Color.YELLOW;
	}
	
	public Mesh(String name, MeshGeometry geometry) {
		this();
		this.name = name;
		this.vertices = geometry.getVertices();
		this.faces = geometry.getFaces();
		this.color = Color.YELLOW;
	}
	
	public Vector3[] getVertices() {
		return this.vertices;
	}
	
	public Vector3 getVertex(int index) {
		return this.vertices[index];
	}
	
	public Face3[] getFaces() {
		return this.faces;
	}
	
	public Face3 getFace(int index) {
		return this.faces[index];
	}
}
