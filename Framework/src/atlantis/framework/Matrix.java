package atlantis.framework;

/**
 * A matrix 4x4 
 * @author Yannick
 */
public class Matrix {
	public float M11;
	public float M12;
	public float M13;
	public float M14;
	public float M21;
	public float M22;
	public float M23;
	public float M24;
	public float M31;
	public float M32;
	public float M33;
	public float M34;
	public float M41;
	public float M42;
	public float M43;
	public float M44;
	
	public Matrix() {
		setIdentity();
	}
	
	public Matrix getIdentity() {
		return new Matrix();
	}
	
	/**
	 * Set all values of the matrix
	 * @param values An array of 16 values who start at M11 and stop at M44
	 */
	public void setValues(float [] values) {
		if (values.length == 16) {
			this.M11 = values[0]; 
			this.M12 = values[1]; 
			this.M13 = values[2];
			this.M14 = values[3];
			this.M21 = values[4]; 
			this.M22 = values[5];
			this.M23 = values[6];
			this.M24 = values[7];
			this.M31 = values[8];
			this.M32 = values[9];
			this.M33 = values[10]; 
			this.M34 = values[11];
			this.M41 = values[12];
			this.M42 = values[13];
			this.M43 = values[14];
			this.M44 = values[15];
		}
	}
	
	public void setIdentity () {
		this.M11 = 1.0f; this.M12 = 0.0f; this.M13 = 0.0f; this.M14 = 0.0f;
		this.M21 = 0.0f; this.M22 = 1.0f; this.M23 = 0.0f; this.M24 = 0.0f;
		this.M31 = 0.0f; this.M32 = 0.0f; this.M33 = 1.0f; this.M34 = 0.0f;
		this.M41 = 0.0f; this.M42 = 0.0f; this.M43 = 0.0f; this.M44 = 1.0f;
	}
	
	/**
	 * Create a rotation matrix on Y axis.
	 * @param rotation An angle in radians
	 * @return A rotation matrix with the specified angle.
	 */
	public static Matrix createRotationY(float rotation) {
		Matrix matrix = new Matrix();
		
		float tx = (float)Math.cos(rotation);
		float ty = (float)Math.sin(rotation);
		
		matrix.M11 = tx;
		matrix.M13 = -ty;
		matrix.M31 = ty;
		matrix.M33 = tx;
		
		return matrix;
	}
	
	public static Matrix createLookAt(Vector3 position, Vector3 target, Vector3 upVector) {
		Vector3 vector3_1 = Vector3.normalize(Vector3.subtract(position, target));
		Vector3 vector3_2 = Vector3.normalize(Vector3.cross(upVector, vector3_1));
		Vector3 vector1 = Vector3.cross(vector3_1, vector3_2);
		Matrix matrix = new Matrix();
		matrix.M11 = vector3_2.x;
		matrix.M12 = vector1.x;
		matrix.M13 = vector3_1.x;
		matrix.M14 = 0.0f;
		matrix.M21 = vector3_2.y;
		matrix.M22 = vector1.y;
		matrix.M23 = vector3_1.y;
		matrix.M24 = 0.0f;
		matrix.M31 = vector3_2.z;
		matrix.M32 = vector1.z;
		matrix.M33 = vector3_1.z;
		matrix.M34 = 0.0f;
		matrix.M41 = -Vector3.dot(vector3_2, position);
		matrix.M42 = -Vector3.dot(vector1, position);
		matrix.M43 = -Vector3.dot(vector3_1, position);
		matrix.M44 = 1.0f;
		return matrix;
	}
	
	public static Matrix createPerspectiveFieldOfView(float fov, float aspect, float near, float far) {
		Matrix matrix = new Matrix();
		
		// TODO : check values
		
		float num = 1.0f / (float)Math.tan(fov * 0.5f);
		float num9 = num / aspect;
		matrix.M11 = num9;
	    matrix.M12 = matrix.M13 = matrix.M14 = 0f;
	    matrix.M22 = num;
	    matrix.M21 = matrix.M23 = matrix.M24 = 0f;
	    matrix.M31 = matrix.M32 = 0f;
	    matrix.M33 = far / (near - far);
	    matrix.M34 = -1f;
	    matrix.M41 = matrix.M42 = matrix.M44 = 0f;
	    matrix.M43 = (near * far) / (near - far);
		
		return matrix;
	}
}
