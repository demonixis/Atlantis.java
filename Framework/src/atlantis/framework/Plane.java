package atlantis.framework;

public class Plane {
	protected float D;
	protected Vector3 normal;
	
	public Plane(Vector4 value) {
		this(new Vector3(value.x, value.y, value.z), value.w);
	}
	
	public Plane(Vector3 normal, float d) {
		this.normal = normal;
		this.D = d;
	}
	
	public Plane(Vector3 a, Vector3 b, Vector3 c) {
		Vector3 ab = Vector3.subtract(b, a);
		Vector3 ac = Vector3.subtract(c, a);
		Vector3 cross = Vector3.cross(ab, ac);
		
		this.normal = Vector3.normalize(cross);
		this.D = -Vector3.dot(cross, a);
	}
	
	public Plane(float a, float b, float c, float d) {
		this(new Vector3(a, b, c), d);
	}
}
