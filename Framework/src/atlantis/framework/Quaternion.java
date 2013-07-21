// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * Define a quaternion.
 * @author Yannick
 * Some methods has been ported from the open source project SharpDX.
 * Check this project here : https://github.com/sharpdx/SharpDX
 */
public class Quaternion {
	public float x;
	public float y;
	public float z;
	public float w;
	
	public static final Quaternion Identity() {
		return new Quaternion(0, 0, 0, 1);
	}
	
	public Quaternion() {
		this(0, 0, 0, 0);
	}
	
	public Quaternion(Vector3 vectorPart, float scalarPart) {
		this(vectorPart.x, vectorPart.y, vectorPart.z, scalarPart);
	}
	
	public Quaternion(Quaternion quaternion) {
		this(quaternion.x, quaternion.y, quaternion.z, quaternion.z);
	}
	
	public Quaternion(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public void add(Quaternion quaternion) {
		this.x += quaternion.x;
		this.y += quaternion.y;
		this.z += quaternion.z;
		this.w += quaternion.w;
	}
	
	public Quaternion add(Quaternion quaternion1, Quaternion quaternion2) {
		Quaternion result = new Quaternion(quaternion1);
		result.add(quaternion2);
		return result;
	}
	
	public void conjugate() {
		this.x *= -1;
		this.y *= -1;
		this.z *= -1;
	}
	
	public static Quaternion conjugate(Quaternion quaternion) {
		Quaternion result = new Quaternion(quaternion);
		result.conjugate();
		return result;
	}
	
	/**
	 * Create a quaternion with three rotations
	 * @param yaw Value of yaw rotation (Y)
	 * @param pitch Value of pitch rotation (X)
	 * @param roll Value of roll rotation (Z)
	 * @return Return a quaternion with three rotations.
	 */
	public static Quaternion createFromYawPitchRoll(float yaw, float pitch, float roll) {
		Quaternion result = new Quaternion();
		
		float halfYaw = yaw * 0.5f;
		float halfPitch = pitch * 0.5f;
		float halfRoll = roll * 0.5f;
		
		float sinYaw = (float)Math.sin(halfYaw);
		float cosYaw = (float)Math.cos(halfYaw);	
		float sinPitch = (float)Math.sin(halfPitch);
		float cosPitch = (float)Math.cos(halfPitch);	
		float sinRoll = (float)Math.sin(halfRoll);
		float cosRoll = (float)Math.cos(halfRoll);
		
		result.x = (cosYaw * sinPitch * cosRoll) + (sinYaw * cosPitch * sinRoll);
		result.y = (sinYaw * cosPitch * cosRoll) - (cosYaw * sinPitch * sinRoll);
		result.z = (cosYaw * cosPitch * sinRoll) - (sinYaw * sinPitch * cosRoll);
		result.w = (cosYaw * cosPitch * cosRoll) - (sinYaw * sinPitch * sinRoll);
		
		return result;
	}
	
	public static Quaternion createFromRotationMatrix(Matrix matrix) {
		float sqrt;
        float half;
        float scale = matrix.M11 + matrix.M22 + matrix.M33;
        Quaternion result = new Quaternion();
        
        if (scale > 0.0f)
        {
            sqrt = (float)Math.sqrt(scale + 1.0f);
            result.w = sqrt * 0.5f;
            sqrt = 0.5f / sqrt;

            result.x = (matrix.M23 - matrix.M32) * sqrt;
            result.y = (matrix.M31 - matrix.M13) * sqrt;
            result.z = (matrix.M12 - matrix.M21) * sqrt;
        }
        else if ((matrix.M11 >= matrix.M22) && (matrix.M11 >= matrix.M33))
        {
            sqrt = (float)Math.sqrt(1.0f + matrix.M11 - matrix.M22 - matrix.M33);
            half = 0.5f / sqrt;

            result.x = 0.5f * sqrt;
            result.y = (matrix.M12 + matrix.M21) * half;
            result.z = (matrix.M13 + matrix.M31) * half;
            result.w = (matrix.M23 - matrix.M32) * half;
        }
        else if (matrix.M22 > matrix.M33)
        {
            sqrt = (float)Math.sqrt(1.0f + matrix.M22 - matrix.M11 - matrix.M33);
            half = 0.5f / sqrt;

            result.x = (matrix.M21 + matrix.M12) * half;
            result.y = 0.5f * sqrt;
            result.z = (matrix.M32 + matrix.M23) * half;
            result.w = (matrix.M31 - matrix.M13) * half;
        }
        else
        {
            sqrt = (float)Math.sqrt(1.0f + matrix.M33 - matrix.M11 - matrix.M22);
            half = 0.5f / sqrt;

            result.x = (matrix.M31 + matrix.M13) * half;
            result.y = (matrix.M32 + matrix.M23) * half;
            result.z = 0.5f * sqrt;
            result.w = (matrix.M12 - matrix.M21) * half;
        }
        
        return result;
	}
}
