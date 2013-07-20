// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d;

import atlantis.framework.Vector2;
import atlantis.framework.Vector3;

/**
 * A structure that define a Vertex Position Normal
 * @author Yannick
 */
public class Vertex {
	public Vector3 position;
	public Vector3 worldCoordinate;
	public Vector3 normal;
	public Vector2 textureCoordinate;
	
	public Vertex() {
		this.position = Vector3.Zero();
		this.normal = Vector3.Zero();
		this.worldCoordinate = Vector3.Zero();
		this.textureCoordinate = Vector2.Zero();
	}
	
	public Vertex(Vertex vertex) {
		this.position = vertex.position;
		this.normal = vertex.normal;
		this.worldCoordinate = vertex.worldCoordinate;
		this.textureCoordinate = vertex.textureCoordinate;
	}
	
	public Vertex(Vector3 position, Vector3 normal, Vector3 worldPosition, Vector2 textureCoordinate) {
		this.position = position;
		this.normal = normal;
		this.worldCoordinate = worldPosition;
		this.textureCoordinate = textureCoordinate;
	}
}
