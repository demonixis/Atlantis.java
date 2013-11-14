// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d.geometry;

import atlantis.engine.graphics3d.Face3;
import atlantis.framework.Vector2;
import atlantis.framework.Vector3;

/**
 * A mesh geometry is an abstract class that contains vertices and faces who are
 * used by a mesh to represent a 3D object.
 * @author Yannick
 *
 */
public abstract class MeshGeometry {
	protected float width;
	protected float height;
	protected float depth;
	protected Vector3 [] vertices;
	protected Vector3 [] normals;
	protected Vector2 [] uvs;
	protected Face3 [] faces;

	protected void computeVertices() {
		for (int i = 0, l = vertices.length; i < l; i++) {
            vertices[i].x *= this.width;
            vertices[i].y *= this.height;
            vertices[i].z *= this.depth;
        }
	}
	
	/**
	 * @return Return an array of vertex
	 */
	public Vector3[] getVertices() {
		return vertices;
	}

	/**
	 * @return Return an array of faces
	 */
	public Face3[] getFaces() {
		return faces;
	}
}
