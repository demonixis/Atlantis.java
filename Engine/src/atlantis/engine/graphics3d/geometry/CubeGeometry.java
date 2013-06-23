// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d.geometry;

import atlantis.engine.graphics3d.Face3;
import atlantis.framework.Vector3;

/**
 * A cube geometry.
 * @author Yannick
 */
public class CubeGeometry extends MeshGeometry {
	
	public CubeGeometry() {
		this(1.0f, 1.0f, 1.0f);
	}
	
	public CubeGeometry(float width, float height, float depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;

        this.vertices = new Vector3[8];
        this.vertices[0] = new Vector3(-1, 1, 1);
        this.vertices[1] = new Vector3(1, 1, 1);
        this.vertices[2] = new Vector3(-1, -1, 1);
        this.vertices[3] = new Vector3(1, -1, 1);
        this.vertices[4] = new Vector3(-1, 1, -1);
        this.vertices[5] = new Vector3(1, 1, -1);
        this.vertices[6] = new Vector3(1, -1, -1);
        this.vertices[7] = new Vector3(-1, -1, -1);

        this.faces = new Face3[12];
        this.faces[0] = new Face3(0, 1, 2);
        this.faces[1] = new Face3( 1, 2, 3);
        this.faces[2] = new Face3(1, 3, 6);
        this.faces[3] = new Face3(1, 5, 6);
        this.faces[4] = new Face3(0, 1, 4);
        this.faces[5] = new Face3(1, 4, 5);
        this.faces[6] = new Face3(2, 3, 7);
        this.faces[7] = new Face3(3, 6, 7);
        this.faces[8] = new Face3(0, 2, 7);
        this.faces[9] = new Face3(0, 4, 7);
        this.faces[10] = new Face3(4, 5, 6);
        this.faces[11] = new Face3(4, 6, 7);

        computeVertices();
	}
}
