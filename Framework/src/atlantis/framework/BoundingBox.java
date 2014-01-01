package atlantis.framework;

public class BoundingBox {
	public Vector3 min;
	public Vector3 max;
	public static final int cornerCount = 8;
	
	public BoundingBox(Vector3 min, Vector3 max) {
		this.min = min;
		this.max = max;
	}
	
	/**
	 * Test if a the bounding box contains another bounding box.
	 * @param box
	 * @return Return 0 if not contains, 1 if contains and 2 if intersects
	 */
	public int contains(BoundingBox box) {
		// Disjoint
		if (box.max.x < this.min.x || box.min.x > this.max.x || 
				box.max.y < this.min.y || box.min.y > this.max.y || 
				box.max.z < this.min.z || box.min.z > this.max.z) {
			return 0;
		}
		// Contains check
		else if (box.min.x >= this.min.x && box.max.x <= this.max.x && 
				box.min.y >= this.min.y && box.max.y <= this.max.y && 
				box.min.z >= this.min.z && box.max.z <= this.max.z) {
			return 1;
		}
		// Intersects
		return 2;
	}
	
	public Vector3[] getCorners() {
        return new Vector3[] {
            new Vector3(this.min.x, this.max.y, this.max.z), 
            new Vector3(this.max.x, this.max.y, this.max.z),
            new Vector3(this.max.x, this.min.y, this.max.z), 
            new Vector3(this.min.x, this.min.y, this.max.z), 
            new Vector3(this.min.x, this.max.y, this.min.z),
            new Vector3(this.max.x, this.max.y, this.min.z),
            new Vector3(this.max.x, this.min.y, this.min.z),
            new Vector3(this.min.x, this.min.y, this.min.z)
        };
    }
}
