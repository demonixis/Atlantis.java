// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d.geometry;

import atlantis.engine.graphics3d.Face3;
import atlantis.framework.Vector3;

/**
 * A pyramid geometry.
 * @author Yannick
 */
public class Pyramid3Geometry extends MeshGeometry {
	
	public Pyramid3Geometry() {
		this(1.0f, 1.0f, 1.0f); 
	}
	
	public Pyramid3Geometry(float width, float height, float depth) {
		this.width = width;
        this.height = height;
        this.depth = depth;

        this.vertices = new Vector3[5];
        this.vertices[0] = new Vector3(-1.0f, -1.0f, -1.0f);
        this.vertices[1] = new Vector3(1.0f, -1.0f, -1.0f);
        this.vertices[2] = new Vector3(1.0f, -1.0f, 1.0f);
        this.vertices[3] = new Vector3(-1.0f, -1.0f, 1.0f);
        this.vertices[4] = new Vector3(0.0f, 1.0f, 0.0f);

        this.faces = new Face3[6];
        this.faces[0] = new Face3(0, 1, 2);
        this.faces[1] = new Face3(2, 0, 3);
        this.faces[2] = new Face3(0, 4, 1);
        this.faces[3] = new Face3(1, 4, 2);
        this.faces[4] = new Face3(2, 4, 3);
        this.faces[5] = new Face3(3, 4, 0);

        computeVertices();
	}
}
