package atlantis.engine.graphics3d.geometry;

import java.awt.Color;

import atlantis.engine.graphics3d.Face3;
import atlantis.framework.Vector3;

public class PlaneGeometry extends MeshGeometry {
	public PlaneGeometry(int width, int depth) {	
		this.width = width;
		this.height = 0;
		this.depth = depth;
		
		int size = (int)(this.width * this.depth);
		this.vertices = new Vector3[size];

        for (int x = 0; x < (int)this.width; x++) {
            for (int z = 0; z < (int)this.depth; z++) {
            	int index = (int)(x + z * this.width);
                this.vertices[index] = new Vector3(x, 0, z);         
           }
        }
        
        this.faces = new Face3[(int)((this.width - 1) * (this.depth - 1) * 2)];

        int counter = 0;
        int counter2 = 0;
        Face3 faceA, faceB;
        
        for (int x = 0; x < (int)this.width - 1; x++) {
            for (int y = 0; y < (int)this.depth - 1; y++) {
                int lowerLeft = (int)(x + y * this.width);
                int lowerRight = (int)((x + 1) + y * this.width);
                int topLeft = (int)(x + (y + 1) * this.width);
                int topRight = (int)((x + 1) + (y + 1) * this.width);
                
                faceA = new Face3(topLeft, lowerLeft, lowerRight);
                faceB = new Face3(topLeft, lowerRight, topRight);
                
                if (counter2 % 2 == 0) {
                	faceA.color = Color.lightGray;
                	faceB.color = Color.lightGray;
                }
                else {
                	faceA.color = Color.white;
                	faceB.color = Color.white;
                }
                
                this.faces[counter++] = faceA;
                this.faces[counter++] = faceB;
                counter2++;
            }
        }
	}
	
	protected void createVertices() {
		
	}
}
