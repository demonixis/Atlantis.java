package atlantis.engine.graphics3d.geometry;

import java.util.Random;

import atlantis.engine.graphics3d.Face3;
import atlantis.framework.MathHelper;
import atlantis.framework.Vector2;
import atlantis.framework.Vector3;

public class CylinderGeometry extends MeshGeometry {
	private Vector3 startPosition;
    private Vector3 endPosition;
    private float startRadius;
    private float endRadius;
    private int nbSegments;
    private int nbSlices;
    private boolean invertFaces;
    private static Random random = new Random();
    
	public CylinderGeometry(Vector3 start, Vector3 end, float startRadius, float endRadius, boolean invertFaces, int nbSegments, int nbSlices) {
        this.startPosition = start;
        this.endPosition = end;
        this.startRadius = startRadius;
        this.endRadius = endRadius;
        this.invertFaces = invertFaces;
        this.nbSegments = nbSegments;
        this.nbSlices = nbSlices;
        this.create();
	}
	
	private void create() {
		this.nbSegments = Math.max(1, this.nbSegments);
        this.nbSlices = Math.max(3, this.nbSlices);

        // this vector should not be between start and end
        Vector3 p = new Vector3(random.nextFloat(), random.nextFloat(), random.nextFloat());

        // derive two points on the plane formed by [end - start]
        Vector3 r = Vector3.cross(Vector3.subtract(p, this.startPosition), Vector3.subtract(this.endPosition, this.startPosition));
        Vector3 s = Vector3.cross(r, Vector3.subtract(this.endPosition, this.startPosition));
        r.normalize();
        s.normalize();

        int vertexCount = 0, indexCount = 0;
        float invSegments = 1f / this.nbSegments;
        float invSlices = 1f / this.nbSlices;

        this.vertices = new Vector3[((this.nbSegments + 1) * (this.nbSlices + 1)) + 2];
        this.uvs = new Vector2[((this.nbSegments + 1) * (this.nbSlices + 1)) + 2];
        this.faces = new Face3[(this.nbSlices + (this.nbSlices * this.nbSegments)) * 2];

        for (int j = 0; j <= this.nbSegments; j++) {
            Vector3 center = Vector3.lerp(this.startPosition, this.endPosition, j * invSegments);
            float radius = MathHelper.lerp(this.startRadius, this.endRadius, j * invSegments);

            if (j == 0) {
                this.vertices[vertexCount] = new Vector3(center.x, center.y, center.z);
                this.uvs[vertexCount] = new Vector2(0.5f, j * invSegments);
                vertexCount++;
            }

            for (int i = 0; i <= this.nbSlices; i++) {
                float theta = (float)(i * MathHelper.TwoPi * invSlices);
                float rCosTheta = radius * (float)Math.cos(theta);
                float rSinTheta = radius * (float)Math.sin(theta);

                this.vertices[vertexCount++] = new Vector3(
                		center.x + rCosTheta * r.x + rSinTheta * s.x, 
                		center.y + rCosTheta * r.y + rSinTheta * s.y,
                		center.z + rCosTheta * r.z + rSinTheta * s.z);
                
               this.uvs[vertexCount] = new Vector2(i * invSlices, j * invSegments);

                if (i < this.nbSlices) {
                    // just an alias to assist with think of each vertex that's
                    //  iterated in here as the bottom right corner of a triangle
                    int vRef = vertexCount - 1;

                    if (j == 0) {   
                        // start cap - i0 is always center point on start cap
                        int i0 = 0;
                        int i1 = (int)(vRef + 1);
                        int i2 = (int)(vRef);

                        this.faces[indexCount++] = new Face3(i0, this.invertFaces ? i2 : i1, this.invertFaces ? i1 : i2);
                    }
                    
                    if (j == this.nbSegments) {   
                        // end cap - i0 is always the center point on end cap
                        int i0 = (int)((vRef + this.nbSlices + 2) - (vRef % (this.nbSlices + 1)));
                        int i1 = (int)(vRef);
                        int i2 = (int)(vRef + 1);

                        this.faces[indexCount++] = new Face3(i0, this.invertFaces ? i2 : i1, this.invertFaces ? i1 : i2);
                    }

                    if (j < this.nbSegments) {   
                        // middle area
                        int i0 = (int)(vRef);
                        int i1 = (int)(vRef + 1);
                        int i2 = (int)(vRef + this.nbSlices + 2);
                        int i3 = (int)(vRef + this.nbSlices + 1);
                        
                        this.faces[indexCount++] = new Face3(i0, this.invertFaces ? i2 : i1, this.invertFaces ? i1 : i2);
                        this.faces[indexCount++] = new Face3(i0, this.invertFaces ? i3 : i2, this.invertFaces ? i2 : i3);
                    }
                }
            }

            if (j == this.nbSegments) {
                this.vertices[vertexCount] = new Vector3(center.x, center.y, center.z);
                this.uvs[vertexCount] = new Vector2(0.5f, j * invSegments);
                vertexCount++;
            }
        }
	}
}
