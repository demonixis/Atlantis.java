// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * Define a two dimensional point
 * @author Yannick
 */
public class Point {
	public int x;
	public int y;
	
	/**
	 * Create a default point with X and Y equal 0.
	 */
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Create a point with coordinates.
	 * @param x Value of X coordinate.
	 * @param y Value of Y coordinate.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Create a point with an existing point. Values are copied.
	 * @param point An existing point.
	 */
	public Point(Point point) {
		this.x = point.x;
		this.y = point.y;
	}
	
	/**
	 * Create a point with an existing Vector2. Values are copied.
	 * @param vector An existing Vector2
	 */
	public Point(Vector2 vector) {
		this.x = (int)vector.x;
		this.y = (int)vector.y;
	}
	
	/**
	 * Gets a Vector2 from this point.
	 * @return Return a Vector2 of this point.
	 */
	public Vector2 toVector2() {
		return new Vector2(this.x, this.y);
	}
	
	public String toString() {
		return "x: " + this.x + " y: " + this.y;
	}
}
