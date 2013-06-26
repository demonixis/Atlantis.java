// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * Define a vector with four coordinates.
 * @author Yannick
 */
public class Vector4 {
	public float x;
	public float y;
	public float z;
	public float w;
	
	public Vector4() {
		this(0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	public Vector4(float value) {
		this(value, value, value, value);
	}
	
	public Vector4(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Vector4(Vector4 vector) {
		this(vector.x, vector.y, vector.z, vector.w);
	}
}
