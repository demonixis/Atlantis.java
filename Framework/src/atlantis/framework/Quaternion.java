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
}
