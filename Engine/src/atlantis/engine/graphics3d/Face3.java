// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d;

/**
 * A class that represent a face on a mesh. It's a collection of 3 points
 * @author Yannick
 */
public class Face3 {
	public int a;
	public int b;
	public int c;
	
	/**
	 * Create a face3 with all values to 0.
	 */
	public Face3() {
		this.a = 0;
		this.b = 0;
		this.c = 0;
	}
	
	/**
	 * Create a face3 with specified values.
	 * @param a Value for A.
	 * @param b Value for B.
	 * @param c Value for C.
	 */
	public Face3(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	/**
	 * Create a face3 with an existing face.
	 * @param face A face.
	 */
	public Face3(Face3 face) {
		this.a = face.a;
		this.b = face.b;
		this.c = face.c;
	}
}
