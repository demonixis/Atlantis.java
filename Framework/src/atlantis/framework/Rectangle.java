// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * Define a rectangle with coordinates and size
 * @author Yannick
 */
public class Rectangle {
	public static final Rectangle Empty = new Rectangle();
	public int x;
	public int y;
	public int width;
	public int height;
	
	/**
	 * Create an empty rectangle.
	 */
	public Rectangle() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}
	
	/**
	 * Create a rectangle with coordinates and size.
	 * @param x Value of X coordinate.
	 * @param y Value of Y coordinate.
	 * @param width Value for width.
	 * @param height Value for height.
	 */
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Create a rectangle with an existing rectangle. Values are copied.
	 * @param rectangle A rectangle.
	 */
	public Rectangle(Rectangle rectangle) {
		this.x = rectangle.x;
		this.y = rectangle.y;
		this.width = rectangle.width;
		this.height = rectangle.height;
	}

	/**
	 * Gets the center of the rectangle
	 * @return
	 */
	public Point getCenter() {
		return new Point(this.x + (this.width / 2), this.y + (this.height / 2));
	}
	
	/**
	 * Check if the coordinates is inside the rectangle.
	 * @param x
	 * @param y
	 * @return Return true if the coordinates are inside the rectangle, otherwise false
	 */
	public boolean contains(int x, int y) {
		return (this.x <= x) && (x < this.getRight()) && (this.y <= y) && (y < this.getBottom());
	}
	
	/**
	 * Check if the specified point is inside rectangle
	 * @param point
	 * @return true if the point is inside the rectangle, otherwise false
	 */
	public boolean contains(Point point) {
		return (this.x <= point.x) && (point.x < this.getRight()) && (this.y <= point.y) && (point.y < this.getBottom());
	}
	
	/**
	 * Check if the specified point is inside rectangle
	 * @param vector
	 * @return true if the vector is inside the rectangle, otherwise false
	 */
	public boolean contains(Vector2 vector) {
		return this.contains((int)vector.x, (int)vector.y);
	}
	
	/**
	 * Check if the specified rectangle is inside rectangle
	 * @param rectangle
	 * @return true if the rectangle is inside the rectangle, otherwise false
	 */
	public boolean contains(Rectangle rectangle) {
		return (this.x <= rectangle.x) && (rectangle.x < this.getRight()) && (this.y <= rectangle.y) && (rectangle.y < this.getBottom());
	}
	
	/**
	 * Check if the specified rectangle intersects rectangle
	 * @param rectangle
	 * @return true if the rectangle intersects rectangle, otherwise false
	 */
	public boolean intersects(Rectangle rectangle) {
		return (rectangle.getLeft() < this.getRight()) && (this.getLeft() < rectangle.getRight()) && (rectangle.getTop() < this.getBottom()) && (this.getTop() < rectangle.getBottom());  
	}

	public String toString() {
		return "x: " + this.x + " y: " + this.y + " width: " + this.width + " height: " + this.height;
	}
	
	/**
	 * Sets the position of the rectangle
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets the size of the rectangle.
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Gets the top position.
	 * @return Return the Y position.
	 */
	public int getTop() {
		return this.y;
	}
	
	/**
	 * Gets the bottom position.
	 * @return Return the bottom position of the rectangle.
	 */
	public int getBottom() {
		return this.y + this.height;
	}
	
	/**
	 * Gets the left position.
	 * @return Return the X position.
	 */
	public int getLeft() {
		return this.x;
	}
	
	/**
	 * Gets the right position.
	 * @return Return the right position of the rectangle.
	 */
	public int getRight() {
		return this.x + this.width;
	}
}
