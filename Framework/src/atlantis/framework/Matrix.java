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
	
	/**
	 * Create an identity matrix.
	 */
	public Matrix() {
		this.setIdentity();
	}
	
	/**
	 * Create a matrix with an existing matrix. Values are copied.
	 * @param matrix A matrix.
	 */
	public Matrix(Matrix matrix) {
		this.setValues(matrix.toArray());
	}
	
	/**
	 * Set all values of the matrix
	 * @param values An array of 16 values who start at M11 and stop at M44
	 */
	public void setValues(float [] values) {
		if (values.length == 16) {
			this.M11 = values[0]; this.M12 = values[1]; this.M13 = values[2]; this.M14 = values[3];
			this.M21 = values[4]; this.M22 = values[5]; this.M23 = values[6]; this.M24 = values[7];
			this.M31 = values[8]; this.M32 = values[9]; this.M33 = values[10]; this.M34 = values[11];
			this.M41 = values[12]; this.M42 = values[13]; this.M43 = values[14]; this.M44 = values[15];
		}
	}
	
	/**
	 * Sets the matrix to identity
	 */
	public void setIdentity () {
		setValues(getIdentity());
	}
	
	/**
	 * Gets identity value for push it into matrix.
	 * @return Return an array that correspond of identity matrix.
	 */
	public float[] getIdentity() {
		float [] values = {
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1
		};
		return values;
	}
	
	public void setLeft(Vector3 vector) {
		this.M11 = -vector.x;
		this.M12 = -vector.y;
		this.M13 = -vector.z;
	}
	
	public void setRight(Vector3 vector) {
		this.M11 = vector.x;
		this.M12 = vector.y;
		this.M13 = vector.z;
	}
	
	public void setUp(Vector3 vector) {
		this.M21 = vector.x;
		this.M22 = vector.y;
		this.M23 = vector.z;
	}
	
	public void setDown(Vector3 vector) {
		this.M21 = -vector.x;
		this.M22 = -vector.y;
		this.M23 = -vector.z;
	}
	
	public void setBackward(Vector3 vector) {
		this.M31 = vector.x;
		this.M32 = vector.y;
		this.M33 = vector.z;
	}
	
	public void setForward(Vector3 vector) {
		this.M31 = -vector.x;
        this.M32 = -vector.y;
        this.M33 = -vector.z;
	}
	
	public void setTranslation(Vector3 position) {
		this.M41 = position.x;
		this.M42 = position.y;
		this.M43 = position.z;
	}
	
	public float[] toArray() {
		float[] values = 
		{
			M11, M12, M13, M14,
			M21, M22, M23, M24,
			M31, M32, M33, M34,
			M41, M42, M43, M44,
		};
		return values;
	}
	
	/**
	 * Create a rotation matrix on X axis.
	 * @param rotation An angle in radians
	 * @return Return a rotation matrix on X axis.
	 */
	public static Matrix createRotationX(float rotation) {
		Matrix matrix = new Matrix();
		
		float t1 = (float)Math.cos(rotation);
		float t2 = (float)Math.sin(rotation);
		
		matrix.M22 = t1;
		matrix.M23 = t2;
		matrix.M32 = -t2;
		matrix.M33 = t1;
		
		return matrix;
	}
	
	/**
	 * Create a rotation matrix on Y axis.
	 * @param rotation An angle in radians
	 * @return Return a rotation matrix on Y axis.
	 */
	public static Matrix createRotationY(float rotation) {
		Matrix matrix = new Matrix();
		
		float t1 = (float)Math.cos(rotation);
		float t2 = (float)Math.sin(rotation);
		
		matrix.M11 = t1;
		matrix.M13 = -t2;
		matrix.M31 = t2;
		matrix.M33 = t1;
		
		return matrix;
	}
	
	public static Matrix createRotationZ(float rotation) {
		Matrix matrix = new Matrix();
		
		float t1 = (float)Math.cos(rotation);
		float t2 = (float)Math.sin(rotation);
		
		matrix.M11 = t1;
		matrix.M13 = t2;
		matrix.M31 = -t2;
		matrix.M33 = t1;
		
		return matrix;
	}
	
	/**
	 * Create a scale matrix.
	 * @param scale Scale to use.
	 * @return Return a scale matrix.
	 */
	public static Matrix createScale(float scale) {
		return createScale(scale, scale, scale);
	}
	
	/**
	 * Create a scale matrix.
	 * @param scale Scale to use.
	 * @return Return a scale matrix.
	 */
	public static Matrix createScale(Vector3 scale) {
		return createScale(scale.x, scale.y, scale.z);
	}
	
	/**
	 * Create a scale matrix.
	 * @param sx Desired scale on X axis.
	 * @param sy Desired scale on Y axis.
	 * @param sz Desired scale on Z axis.
	 * @return Return a scale matrix.
	 */
	public static Matrix createScale(float sx, float sy, float sz) {
		Matrix matrix = new Matrix();
		matrix.M11 = sx;
		matrix.M22 = sy;
		matrix.M33 = sz;
		return matrix;
	}
	
	/**
	 * Create a translation matrix.
	 * @param x Position on X axis.
	 * @param y Position on Y axis.
	 * @param z Position on Z axis.
	 * @return Return a matrix translation.
	 */
	public static Matrix createTranslation(float x, float y, float z) {
		Matrix matrix = new Matrix();
		matrix.M41 = x;
		matrix.M42 = y;
		matrix.M43 = z;
		return matrix;
	}
	
	/**
	 * Create a translation matrix.
	 * @param vector A vector to use for translation.
	 * @return Return a matrix translation.
	 */
	public static Matrix createTranslation(Vector3 vector) {
		return createTranslation(vector.x, vector.y, vector.z);
	}
	
	/**
	 * Create a world matrix.
	 * @param position
	 * @param forward
	 * @param upVector
	 * @return Return a world matrix.
	 */
	public static Matrix createWorld(Vector3 position, Vector3 forward, Vector3 upVector) {
		Matrix matrix = new Matrix();
		
		Vector3 x = Vector3.cross(forward, upVector);
		Vector3 y = Vector3.cross(x, forward);
		Vector3 z = Vector3.normalize(forward);
		x.normalize();
		y.normalize();
		
		matrix.setRight(x);
		matrix.setUp(y);
		matrix.setForward(z);
		matrix.setTranslation(position);
		matrix.M44 = 1.0f;
		
		return matrix;
	}
	
	/**
	 * Create a view matrix
	 * @param position The position of the camera.
	 * @param target The target of the camera.
	 * @param upVector Vector up
	 * @return Return a view camera.
	 */
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
	
	/**
	 * Create a perspective field of view matrix.
	 * @param fov Desired field of view (Math.PI / 4 is a good value)
	 * @param aspect Desired aspect ratio (Screen width / height)
	 * @param near Near clip
	 * @param far Far clip
	 * @return Return a matrix of this type of perspective.
	 */
	public static Matrix createPerspectiveFieldOfView(float fov, float aspect, float near, float far) {
		Matrix matrix = new Matrix();
		
		if (fov > 0 && aspect > 0 && near > 0 && far > 0) {
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
		}
		return matrix;
	}
	
	/**
	 * Multiply a two matrix.
	 * @param matrixA A matrix.
	 * @param matrixB Another matrix.
	 * @return Return a new matrix.
	 */
	public static Matrix multiply(Matrix matrixA, Matrix matrixB) {
		Matrix matrix = new Matrix(matrixA);
		
		float m11 = (((matrixA.M11 * matrixB.M11) + (matrixA.M12 * matrixB.M21)) + (matrixA.M13 * matrixB.M31)) + (matrixA.M14 * matrixB.M41);
        float m12 = (((matrixA.M11 * matrixB.M12) + (matrixA.M12 * matrixB.M22)) + (matrixA.M13 * matrixB.M32)) + (matrixA.M14 * matrixB.M42);
        float m13 = (((matrixA.M11 * matrixB.M13) + (matrixA.M12 * matrixB.M23)) + (matrixA.M13 * matrixB.M33)) + (matrixA.M14 * matrixB.M43);
        float m14 = (((matrixA.M11 * matrixB.M14) + (matrixA.M12 * matrixB.M24)) + (matrixA.M13 * matrixB.M34)) + (matrixA.M14 * matrixB.M44);
        float m21 = (((matrixA.M21 * matrixB.M11) + (matrixA.M22 * matrixB.M21)) + (matrixA.M23 * matrixB.M31)) + (matrixA.M24 * matrixB.M41);
        float m22 = (((matrixA.M21 * matrixB.M12) + (matrixA.M22 * matrixB.M22)) + (matrixA.M23 * matrixB.M32)) + (matrixA.M24 * matrixB.M42);
        float m23 = (((matrixA.M21 * matrixB.M13) + (matrixA.M22 * matrixB.M23)) + (matrixA.M23 * matrixB.M33)) + (matrixA.M24 * matrixB.M43);
        float m24 = (((matrixA.M21 * matrixB.M14) + (matrixA.M22 * matrixB.M24)) + (matrixA.M23 * matrixB.M34)) + (matrixA.M24 * matrixB.M44);
        float m31 = (((matrixA.M31 * matrixB.M11) + (matrixA.M32 * matrixB.M21)) + (matrixA.M33 * matrixB.M31)) + (matrixA.M34 * matrixB.M41);
        float m32 = (((matrixA.M31 * matrixB.M12) + (matrixA.M32 * matrixB.M22)) + (matrixA.M33 * matrixB.M32)) + (matrixA.M34 * matrixB.M42);
        float m33 = (((matrixA.M31 * matrixB.M13) + (matrixA.M32 * matrixB.M23)) + (matrixA.M33 * matrixB.M33)) + (matrixA.M34 * matrixB.M43);
        float m34 = (((matrixA.M31 * matrixB.M14) + (matrixA.M32 * matrixB.M24)) + (matrixA.M33 * matrixB.M34)) + (matrixA.M34 * matrixB.M44);
        float m41 = (((matrixA.M41 * matrixB.M11) + (matrixA.M42 * matrixB.M21)) + (matrixA.M43 * matrixB.M31)) + (matrixA.M44 * matrixB.M41);
        float m42 = (((matrixA.M41 * matrixB.M12) + (matrixA.M42 * matrixB.M22)) + (matrixA.M43 * matrixB.M32)) + (matrixA.M44 * matrixB.M42);
        float m43 = (((matrixA.M41 * matrixB.M13) + (matrixA.M42 * matrixB.M23)) + (matrixA.M43 * matrixB.M33)) + (matrixA.M44 * matrixB.M43);
       	float m44 = (((matrixA.M41 * matrixB.M14) + (matrixA.M42 * matrixB.M24)) + (matrixA.M43 * matrixB.M34)) + (matrixA.M44 * matrixB.M44);
       	
       	matrix.M11 = m11;
       	matrix.M12 = m12;
       	matrix.M13 = m13;
       	matrix.M14 = m14;
       	matrix.M21 = m21;
       	matrix.M22 = m22;
       	matrix.M23 = m23;
       	matrix.M24 = m24;
		matrix.M31 = m31;
		matrix.M32 = m32;
		matrix.M33 = m33;
		matrix.M34 = m34;
		matrix.M41 = m41;
		matrix.M42 = m42;
		matrix.M43 = m43;
		matrix.M44 = m44;
		
		return matrix;
	}
}
