// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d;

import atlantis.framework.Vector3;

/**
 * A structure that define a Vertex Position Normal
 * @author Yannick
 */
public class Vertex {
	public Vector3 position;
	public Vector3 worldPosition;
	public Vector3 normal;
	
	public Vertex() {
		this.position = Vector3.Zero();
		this.normal = Vector3.Zero();
		this.worldPosition = Vector3.Zero();
	}
	
	public Vertex(Vertex vertex) {
		this.position = vertex.position;
		this.normal = vertex.normal;
		this.worldPosition = vertex.worldPosition;
	}
	
	public Vertex(Vector3 position, Vector3 normal, Vector3 worldPosition) {
		this.position = position;
		this.normal = normal;
		this.worldPosition = worldPosition;
	}
}
