// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * A matrix 4x4 
 * Some methods has been ported from the open source project SharpDX.
 * Check this project here : https://github.com/sharpdx/SharpDX
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
	 * Create an empty matrix with all field at 0.0f.
	 */
	public Matrix() {
		float[] values = {
			0.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 0.0f
		};
		this.set(values);
	}
	
	/**
	 * Create a matrix with an existing matrix. Values are copied.
	 * @param matrix A matrix.
	 */
	public Matrix(Matrix matrix) {
		this.set(matrix.toArray());
	}
	
	/**
	 * Create a matrix with an array of 16 values.
	 * @param values An array of 16 float values.
	 * @throws Exception Thrown if the array hasn't 16 values.
	 */
	public Matrix(float[] values) throws Exception {
		if (values.length != 16) {
			throw new Exception("The array of values must contains 16 float values");
		}
		this.set(values);
	}
	
	/**
	 * Set all values of the matrix
	 * @param values An array of 16 values who start at M11 and stop at M44
	 */
	public void set(float [] values) {
		if (values.length == 16) {
			this.M11 = values[0]; this.M12 = values[1]; this.M13 = values[2]; this.M14 = values[3];
			this.M21 = values[4]; this.M22 = values[5]; this.M23 = values[6]; this.M24 = values[7];
			this.M31 = values[8]; this.M32 = values[9]; this.M33 = values[10]; this.M34 = values[11];
			this.M41 = values[12]; this.M42 = values[13]; this.M43 = values[14]; this.M44 = values[15];
		}
	}
	
	// ---
	// --- Getters and setters
	// ---
	
	/**
	 * Sets the matrix to identity
	 */
	public void setIdentity () {
		set(getIdentityValues());
	}
	
	/**
	 * Gets identity value for push it into matrix.
	 * @return Return an array that correspond of identity matrix.
	 */
	public float[] getIdentityValues() {
		float [] values = {
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1
		};
		return values;
	}
	
	/**
	 * Gets an identity matrix.
	 * @return Return an identity matrix.
	 */
	public static Matrix getMatrixIdentity() {
		Matrix matrix = new Matrix();
		matrix.setIdentity();
		return matrix;
	}
	
	/**
	 * Sets the left of the matrix.
	 * @param vector
	 */
	public void setLeft(Vector3 vector) {
		this.M11 = -vector.x;
		this.M12 = -vector.y;
		this.M13 = -vector.z;
	}
	
	/**
	 * Gets the left of the matrix.
	 * @return Return a the left vector of the matrix.
	 */
	public Vector3 getLeft() {
		Vector3 vector = new Vector3();
		vector.x = -this.M11;
		vector.y = -this.M12;
		vector.z = -this.M13;
		return vector;
	}
	
	/**
	 * Sets the right of the matrix.
	 * @param vector
	 */
	public void setRight(Vector3 vector) {
		this.M11 = vector.x;
		this.M12 = vector.y;
		this.M13 = vector.z;
	}
	
	/**
	 * Gets the right of the matrix.
	 * @return Return a the right vector of the matrix.
	 */
	public Vector3 getRight() {
		Vector3 vector = new Vector3();
		vector.x = this.M11;
		vector.y = this.M12;
		vector.z = this.M13;
		return vector;
	}
	
	/**
	 * Sets the up of the matrix.
	 * @param vector
	 */
	public void setUp(Vector3 vector) {
		this.M21 = vector.x;
		this.M22 = vector.y;
		this.M23 = vector.z;
	}
	
	/**
	 * Gets the up of the matrix.
	 * @return Return a the up vector of the matrix.
	 */
	public Vector3 getUp() {
		Vector3 vector = new Vector3();
		vector.x = this.M21;
		vector.y = this.M22;
		vector.z = this.M23;
		return vector;
	}
	
	/**
	 * Sets the down of the matrix.
	 * @param vector
	 */
	public void setDown(Vector3 vector) {
		this.M21 = -vector.x;
		this.M22 = -vector.y;
		this.M23 = -vector.z;
	}
	
	/**
	 * Gets the down of the matrix.
	 * @return Return a the down vector of the matrix.
	 */
	public Vector3 getDown() {
		Vector3 vector = new Vector3();
		vector.x = -this.M21;
		vector.y = -this.M22;
		vector.z = -this.M23;
		return vector;
	}
	
	/**
	 * Sets the backward of the matrix.
	 * @param vector
	 */
	public void setBackward(Vector3 vector) {
		this.M31 = vector.x;
		this.M32 = vector.y;
		this.M33 = vector.z;
	}
	
	/**
	 * Gets the backward of the matrix.
	 * @return Return a the backward vector of the matrix.
	 */
	public Vector3 getBackward() {
		Vector3 vector = new Vector3();
		vector.x = this.M31;
		vector.y = this.M32;
		vector.z = this.M33;
		return vector;
	}
	
	/**
	 * Sets the forward of the matrix.
	 * @param vector
	 */
	public void setForward(Vector3 vector) {
		this.M31 = -vector.x;
        this.M32 = -vector.y;
        this.M33 = -vector.z;
	}
	
	/**
	 * Gets the forward of the matrix.
	 * @return Return a the forward vector of the matrix.
	 */
	public Vector3 getForward() {
		Vector3 vector = new Vector3();
		vector.x = -this.M31;
		vector.y = -this.M32;
		vector.z = -this.M33;
		return vector;
	}
	
	/**
	 * Sets translation
	 * @param position The position to set.
	 */
	public void setTranslation(Vector3 position) {
		this.M41 = position.x;
		this.M42 = position.y;
		this.M43 = position.z;
	}
	
	/**
	 * Gets values of matrix in array. Start at M11 to M44.
	 * @return An array of values.
	 */
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
	
	// ---
	// --- Methods declaration
	// ---
	
	/**
	 * Add a matrix to this matrix.
	 * @param matrix A matrix to add.
	 */
	public void add(Matrix matrix) {
		float [] mValues = this.toArray();
		float [] eValues = matrix.toArray();
		
		for(int i = 0; i < 16; i++) {
			mValues[i] += eValues[i];
		}
		this.set(mValues);
	}
	
	/**
	 * Add two matrix.
	 * @param matA A matrix
	 * @param matB Another matrix to add with the first
	 * @return Return a new matrix.
	 */
	public static Matrix add(Matrix matA, Matrix matB) {
		Matrix matrix = new Matrix(matA);
		matrix.add(matB);
		return matrix;
	}
	
	/**
	 * Create a rotation matrix on X axis.
	 * @param rotation An angle in radians
	 * @return Return a rotation matrix on X axis.
	 */
	public static Matrix createRotationX(float rotation) {
		Matrix matrix = getMatrixIdentity();
		
		float cos = (float)Math.cos(rotation);
		float sin = (float)Math.sin(rotation);
		
		matrix.M22 = cos;
		matrix.M23 = sin;
		matrix.M32 = -sin;
		matrix.M33 = cos;
		
		return matrix;
	}
	
	/**
	 * Create a rotation matrix on Y axis.
	 * @param rotation An angle in radians
	 * @return Return a rotation matrix on Y axis.
	 */
	public static Matrix createRotationY(float rotation) {
		Matrix matrix = getMatrixIdentity();
		
		float cos = (float)Math.cos(rotation);
		float sin = (float)Math.sin(rotation);
		
		matrix.M11 = cos;
		matrix.M13 = -sin;
		matrix.M31 = sin;
		matrix.M33 = cos;
		
		return matrix;
	}
	
	/**
	 * Create a rotation matrix on Z axis.
	 * @param rotation An angle in radians
	 * @return Return a rotation matrix on Z axis.
	 */
	public static Matrix createRotationZ(float rotation) {
		Matrix matrix = getMatrixIdentity();
		
		float cos = (float)Math.cos(rotation);
		float sin = (float)Math.sin(rotation);
		
		matrix.M11 = cos;
		matrix.M13 = sin;
		matrix.M31 = -sin;
		matrix.M33 = cos;
		
		return matrix;
	}
	
	/**
	 * Create a rotation matrix from a quaternion
	 * @param quaternion A quaternion to use
	 * @return Return a rotation matrix.
	 */
	public static Matrix createRotationFromQuaternion(Quaternion quaternion) {
		Matrix result = new Matrix();
		result.setIdentity();
		
		float xx = quaternion.x * quaternion.x;
        float yy = quaternion.y * quaternion.y;
        float zz = quaternion.z * quaternion.z;
        float xy = quaternion.x * quaternion.y;
        float zw = quaternion.z * quaternion.w;
        float zx = quaternion.z * quaternion.x;
        float yw = quaternion.y * quaternion.w;
        float yz = quaternion.y * quaternion.z;
        float xw = quaternion.x * quaternion.w;

        result.M11 = 1.0f - (2.0f * (yy + zz));
        result.M12 = 2.0f * (xy + zw);
        result.M13 = 2.0f * (zx - yw);
        result.M21 = 2.0f * (xy - zw);
        result.M22 = 1.0f - (2.0f * (zz + xx));
        result.M23 = 2.0f * (yz + xw);
        result.M31 = 2.0f * (zx + yw);
        result.M32 = 2.0f * (yz - xw);
        result.M33 = 1.0f - (2.0f * (yy + xx));
		
		return result;
	}
	
	/**
	 * Create a rotation matrix with three rotations.
	 * @param yaw
	 * @param pitch
	 * @param roll
	 * @return
	 */
	public static Matrix createRotationYawPitchRoll(float yaw, float pitch, float roll) {
		Quaternion quaternion = Quaternion.createFromYawPitchRoll(yaw, pitch, roll);
		return createRotationFromQuaternion(quaternion);
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
		Matrix matrix = getMatrixIdentity();
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
		Matrix matrix = getMatrixIdentity();
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
		Vector3 zAxis = Vector3.subtract(target, position);
		zAxis.normalize();
		Vector3 xAxis = Vector3.cross(upVector, zAxis);
		xAxis.normalize();
		Vector3 yAxis = Vector3.cross(zAxis, xAxis);
		yAxis.normalize();
		
		Matrix matrix = getMatrixIdentity();
		
		matrix.M11 = xAxis.x;
		matrix.M21 = xAxis.y;
		matrix.M31 = xAxis.z;
		
		matrix.M12 = yAxis.x;
		matrix.M22 = yAxis.y;
		matrix.M32 = yAxis.z;
		
		matrix.M13 = zAxis.x;
		matrix.M23 = zAxis.y;
		matrix.M33 = zAxis.z;
		
		matrix.M41 = -Vector3.dot(xAxis, position);
		matrix.M42 = -Vector3.dot(yAxis, position);
		matrix.M43 = -Vector3.dot(zAxis, position);
		
		return matrix;
	}
	
	/**
	 * Create a perspective field of view matrix with Left hand notation.
	 * @param fov Desired field of view (Math.PI / 4 is a good value)
	 * @param aspect Desired aspect ratio (Screen width / height)
	 * @param near Near clip
	 * @param far Far clip
	 * @return Return a matrix of this type of perspective.
	 */
	public static Matrix createPerspectiveFieldOfView(float fov, float aspect, float zNear, float zFar) {
		float yScale = (float)(1.0f / Math.tan(fov * 0.5f));
		float xScale = yScale / aspect;
		float halfWidth = zNear / xScale;
		float halfHeight = zNear / yScale;
		
		return createPerspectiveOffCenter(-halfWidth, halfWidth, -halfHeight, halfHeight, zNear, zFar);
	}
	
	/**
	 * Create a perspective field of view matrix with Right hand notation.
	 * @param fov Desired field of view (Math.PI / 4 is a good value)
	 * @param aspect Desired aspect ratio (Screen width / height)
	 * @param near Near clip
	 * @param far Far clip
	 * @return Return a matrix of this type of perspective.
	 */
	public static Matrix createPerspetiveFieldOfViewRH(float fov, float aspect, float zNear, float zFar) {
		Matrix matrix = createPerspectiveFieldOfView(fov, aspect, zNear, zFar);
		matrix.M31 *= -1.0f;
		matrix.M32 *= -1.0f;
		matrix.M33 *= -1.0f;
		matrix.M34 *= -1.0f;
		return matrix;
	}
	
	/**
	 * Create a custom perspective matrix.
	 * @param left Minimum X value of the viewing volume.
	 * @param right Maximum X value of the viewing volume.
	 * @param bottom Minimum Y value of the viewing volume.
	 * @param top Maximum Y value of the viewing volume.
	 * @param zNear Minimum Z value of the viewing volume.
	 * @param zFar Maximum Z value of the viewing volume.
	 * @return Return a new custom perspective matrix.
	 */
	public static Matrix createPerspectiveOffCenter(float left, float right, float bottom, float top, float zNear, float zFar) {
		float zRange = zFar / (zFar - zNear);
		
		Matrix matrix = new Matrix();
		matrix.M11 = 2.0f * zNear / (right - left);
		matrix.M22 = 2.0f * zNear / (top - bottom);
		matrix.M31 = (left + right) / (left - right);
		matrix.M32 = (top + bottom) / (bottom - top);
		matrix.M33 = zRange;
		matrix.M34 = 1.0f;
		matrix.M43 = -zNear * zRange;
		return matrix;
	}
	
	/**
	 * Multiply this matrix by another matrix.
	 * @param matrix A matrix to multiply.
	 */
	public void multiply(Matrix matrix) {
		float m11 = (((this.M11 * matrix.M11) + (this.M12 * matrix.M21)) + (this.M13 * matrix.M31)) + (this.M14 * matrix.M41);
        float m12 = (((this.M11 * matrix.M12) + (this.M12 * matrix.M22)) + (this.M13 * matrix.M32)) + (this.M14 * matrix.M42);
        float m13 = (((this.M11 * matrix.M13) + (this.M12 * matrix.M23)) + (this.M13 * matrix.M33)) + (this.M14 * matrix.M43);
        float m14 = (((this.M11 * matrix.M14) + (this.M12 * matrix.M24)) + (this.M13 * matrix.M34)) + (this.M14 * matrix.M44);
        float m21 = (((this.M21 * matrix.M11) + (this.M22 * matrix.M21)) + (this.M23 * matrix.M31)) + (this.M24 * matrix.M41);
        float m22 = (((this.M21 * matrix.M12) + (this.M22 * matrix.M22)) + (this.M23 * matrix.M32)) + (this.M24 * matrix.M42);
        float m23 = (((this.M21 * matrix.M13) + (this.M22 * matrix.M23)) + (this.M23 * matrix.M33)) + (this.M24 * matrix.M43);
        float m24 = (((this.M21 * matrix.M14) + (this.M22 * matrix.M24)) + (this.M23 * matrix.M34)) + (this.M24 * matrix.M44);
        float m31 = (((this.M31 * matrix.M11) + (this.M32 * matrix.M21)) + (this.M33 * matrix.M31)) + (this.M34 * matrix.M41);
        float m32 = (((this.M31 * matrix.M12) + (this.M32 * matrix.M22)) + (this.M33 * matrix.M32)) + (this.M34 * matrix.M42);
        float m33 = (((this.M31 * matrix.M13) + (this.M32 * matrix.M23)) + (this.M33 * matrix.M33)) + (this.M34 * matrix.M43);
        float m34 = (((this.M31 * matrix.M14) + (this.M32 * matrix.M24)) + (this.M33 * matrix.M34)) + (this.M34 * matrix.M44);
        float m41 = (((this.M41 * matrix.M11) + (this.M42 * matrix.M21)) + (this.M43 * matrix.M31)) + (this.M44 * matrix.M41);
        float m42 = (((this.M41 * matrix.M12) + (this.M42 * matrix.M22)) + (this.M43 * matrix.M32)) + (this.M44 * matrix.M42);
        float m43 = (((this.M41 * matrix.M13) + (this.M42 * matrix.M23)) + (this.M43 * matrix.M33)) + (this.M44 * matrix.M43);
       	float m44 = (((this.M41 * matrix.M14) + (this.M42 * matrix.M24)) + (this.M43 * matrix.M34)) + (this.M44 * matrix.M44);
       	
       	this.M11 = m11;
       	this.M12 = m12;
       	this.M13 = m13;
       	this.M14 = m14;
       	this.M21 = m21;
       	this.M22 = m22;
       	this.M23 = m23;
       	this.M24 = m24;
		this.M31 = m31;
		this.M32 = m32;
		this.M33 = m33;
		this.M34 = m34;
		this.M41 = m41;
		this.M42 = m42;
		this.M43 = m43;
		this.M44 = m44;
	}
	
	/**
	 * Multiply a two matrix.
	 * @param matrixA A matrix.
	 * @param matrixB Another matrix.
	 * @return Return a new matrix.
	 */
	public static Matrix multiply(Matrix matrixA, Matrix matrixB) {
		Matrix matrix = new Matrix(matrixA);
		matrix.multiply(matrixB);
		return matrix;
	}
	
	/**
	 * Multiply three matrix.
	 * @param matrixA
	 * @param matrixB
	 * @param matrixC
	 * @return A new matrix.
	 */
	public static Matrix multiply(Matrix matrixA, Matrix matrixB, Matrix matrixC) {
		Matrix multMatrix = new Matrix(matrixA);
		multMatrix.multiply(matrixB);
		multMatrix.multiply(matrixC);
		return multMatrix;
	}
	
	/**
	 * Subtract a matrix to this matrix.
	 * @param matrix A matrix to add.
	 */
	public void subtract(Matrix matrix) {
		float [] mValues = this.toArray();
		float [] eValues = matrix.toArray();
		
		for(int i = 0; i < 16; i++) {
			mValues[i] -= eValues[i];
		}
		this.set(mValues);
	}
	
	/**
	 * Subtract two matrix.
	 * @param matA A matrix.
	 * @param matB Another matrix to use to subtract with the first matrix.
	 * @return Return a new matrix.
	 */
	public static Matrix subtract(Matrix matA, Matrix matB) {
		Matrix mat = new Matrix(matA);
		mat.subtract(matB);
		return mat;
	}
	
	public String toString() {
		float[] values = this.toArray();
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < 16; i += 4) {
			builder.append("[");
			builder.append(values[i] + " ");
			builder.append(values[i + 1] + " ");
			builder.append(values[i + 2] + " ");
			builder.append(values[i + 3]);
			builder.append("] ");
		}
		return builder.toString();
	}
}
