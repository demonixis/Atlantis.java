package atlantis.framework;

public class BoundingSphere {
	public Vector3 center;
	public float radius;
	
	public BoundingSphere(Vector3 center, float radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public int contains(Vector3 point) {
        float distance = (float) Vector3.distance(point, this.center);

        if (distance > this.radius)
            return 0;

        else if (distance < this.radius)
            return 1;

        return 0;
    }
	
	public int contains(BoundingBox box) {
		 //check if all corner is in sphere
        boolean inside = true;
        Vector3[] corners = box.getCorners();
        int i = 0;
        int size = corners.length;
        
        while (i < size && inside) {
        	inside = (this.contains(corners[i]) == 0) ? false : inside;
        	i++;
        }
        
        if (inside) {
            return 1;
        }
        
        //check if the distance from sphere this.center to cube face < this.radius
        double dmin = 0;

        if (this.center.x < box.min.x) {
			dmin += (this.center.x - box.min.x) * (this.center.x - box.min.x);
        }
		else if (this.center.x > box.max.x) {
				dmin += (this.center.x - box.max.x) * (this.center.x - box.max.x);
		}
        
		if (this.center.y < box.min.y) {
			dmin += (this.center.y - box.min.y) * (this.center.y - box.min.y);
		}
		else if (this.center.y > box.max.y) {
			dmin += (this.center.y - box.max.y) * (this.center.y - box.max.y);
		}
		
		if (this.center.z < box.min.z) {
			dmin += (this.center.z - box.min.z) * (this.center.z - box.min.z);
		}
		else if (this.center.z > box.max.z) {
			dmin += (this.center.z - box.max.z) * (this.center.z - box.max.z);
		}
		
		if (dmin <= this.radius * this.radius) 
			return 2;
        
        return 0;
	}
	
	public int contains(BoundingSphere sphere) {
		float val = (float) Vector3.distance(sphere.center, this.center);

        if (val > sphere.radius + this.radius) {
            return 0;
        }
        
        else if (val <= this.radius - sphere.radius) {
            return 1;
        }
        
        return 2;
	}
}
