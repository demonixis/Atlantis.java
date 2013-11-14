package atlantis.engine.graphics3d.geometry;

import atlantis.engine.graphics3d.Face3;
import atlantis.framework.Vector2;
import atlantis.framework.Vector3;

public class Pyramid4Geometry extends MeshGeometry {
	
	public Pyramid4Geometry() {
		this.create();
	}

	private void create() {
        Vector2 topRight = new Vector2(1.0f, 0.0f);
        Vector2 bottomLeft = new Vector2(0.0f, 1.0f);
        Vector2 bottomRight = new Vector2(1.0f, 1.0f);
        
        this.vertices = new Vector3[12];
        this.faces = new Face3[4];
        this.uvs = new Vector2[12];
        
        this.vertices[0] = new Vector3(1.0f, -1.0f, 1.0f);
        this.vertices[1] = new Vector3(-1.0f, -1.0f, 1.0f);
        this.vertices[2] = new Vector3(0.0f, 1.0f, 0.0f);

        this.vertices[3] = new Vector3(1.0f, -1.0f, -1.0f);
        this.vertices[4] = new Vector3(1.0f, -1.0f, 1.0f);
        this.vertices[5] = new Vector3(0.0f, 1.0f, 0.0f);

        this.vertices[6] = new Vector3(-1.0f, -1.0f, -1.0f);
        this.vertices[7] = new Vector3(1.0f, -1.0f, -1.0f);
        this.vertices[8] = new Vector3(0.0f, 1.0f, 0.0f);

        this.vertices[9] = new Vector3(-1.0f, -1.0f, 1.0f);
        this.vertices[10] = new Vector3(-1.0f, -1.0f, -1.0f);
        this.vertices[11] = new Vector3(0.0f, 1.0f, 0.0f);

        this.uvs[0] = bottomRight;
        this.uvs[1] = bottomLeft;
        this.uvs[2] = topRight;
        
        this.uvs[3] = bottomRight;
        this.uvs[4] = bottomLeft;
        this.uvs[5] = topRight;
        
        this.uvs[6] = bottomRight;
        this.uvs[7] = bottomLeft;
        this.uvs[8] = topRight;
        
        this.uvs[9] = bottomRight;
        this.uvs[10] = bottomLeft;
        this.uvs[11] = topRight;
        
        this.faces[0] = new Face3(0, 1, 2); 
        this.faces[1] = new Face3(3, 4, 5);
        this.faces[2] = new Face3(6, 7, 8);
        this.faces[3] = new Face3(9, 10, 11); 
	}
}
