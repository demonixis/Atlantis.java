package atlantis.framework;

public class BoundingFrustrum {
	protected Matrix matrix;
	protected Plane bottom;
	protected Plane far;
	protected Plane left;
	protected Plane right;
	protected Plane near;
	protected Plane top;
	protected Vector3[] corners;
	
	public BoundingFrustrum(Matrix value) {
		this.matrix = value;
		this.createPlanes();
		this.createCorners();
	}
	
	public void createPlanes()
	{
		
	}
	
	public void createCorners()
	{
		
	}
}
