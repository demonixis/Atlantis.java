// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * Define a rectangle with coordinates and size
 * @author Yannick
 */
public class Rectangle {
	public static final Rectangle Empty() {
		return new Rectangle();
	}
	
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
	 * Add a rectangle to this rectangle
	 * @param rectangle The rectangle to add.
	 */
	public void add(Rectangle rectangle) {
		this.x += rectangle.x;
		this.y += rectangle.y;
		this.width += rectangle.width;
		this.height += rectangle.height;
	}
	
	/**
	 * Divide a rectangle to this rectangle
	 * @param rectangle The rectangle to divide.
	 */
	public void divide(Rectangle rectangle) {
		this.x /= rectangle.x;
		this.y /= rectangle.y;
		this.width /= rectangle.width;
		this.height /= rectangle.height;
	}
	
	/**
	 * Multiply a rectangle to this rectangle
	 * @param rectangle The rectangle to multiply.
	 */
	public void multiply(Rectangle rectangle) {
		this.x *= rectangle.x;
		this.y *= rectangle.y;
		this.width *= rectangle.width;
		this.height *= rectangle.height;
	}
	
	/**
	 * Subtract a rectangle to this rectangle
	 * @param rectangle The rectangle to adsubtractd.
	 */
	public void subtract(Rectangle rectangle) {
		this.x -= rectangle.x;
		this.y -= rectangle.y;
		this.width -= rectangle.width;
		this.height -= rectangle.height;
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
	 * Check if two rectangle contains itself
	 * @param rectA
	 * @param rectB
	 * @return true if rectA contains rectB, otherwise false
	 */
	public static boolean contains(Rectangle rectA, Rectangle rectB) {
		return rectA.contains(rectB);
	}
	
	/**
	 * Check if the specified rectangle intersects rectangle
	 * @param rectangle
	 * @return true if the rectangle intersects rectangle, otherwise false
	 */
	public boolean intersects(Rectangle rectangle) {
		return (rectangle.getLeft() < this.getRight()) && (this.getLeft() < rectangle.getRight()) && (rectangle.getTop() < this.getBottom()) && (this.getTop() < rectangle.getBottom());  
	}
	
	/**
	 * Check if the rectA intersects rectB
	 * @param rectangle
	 * @return true if rectA intersects rectB, otherwise false
	 */
	public static boolean intersects(Rectangle rectA, Rectangle rectB) {
		return rectA.intersects(rectB);
	}

	public String toString() {
		return "x: " + this.x + " y: " + this.y + " width: " + this.width + " height: " + this.height;
	}
	
	 /**
     * gets the position.
     * @return The position.
	 */
    public Vector2 toVector2() {
        return new Vector2(this.x, this.y);
    }
    
    /**
     * Gets the position.
     * @return The position.
     */
    public Point toPoint() {
    	return new Point(this.x, this.y);
    }
	
	// ---
	// --- Getters and setters
	// ---
	
    /**
	 * Gets the center of the rectangle
	 * @return
	 */
	public Point getCenter() {
		return new Point(this.x + (this.width / 2), this.y + (this.height / 2));
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
	
	/**
	 * Gets width
	 * @return
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Gets height
	 * @return
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
     *  gets the top center.
     * @return The top center of the rectangle
	 */
    public Vector2 getTopCenter() {
        return new Vector2(this.x + this.width / 2, this.y);
    }

    /**
     * gets the bottom center.
     * @return The bottom center.
	 */
    public Vector2 getBottomCenter() {
        return new Vector2(this.x + this.width / 2, this.y + this.height);
    }

    /**
     * gets the left center.
     * @return The left center.
	 */
    public Vector2 getLeftCenter() {
        return new Vector2(this.x, this.y + this.height / 2);
    }

    /**
     *  gets the right center.
	 * @return The right center.
	 */
    public Vector2 getRightCenter() {
        return new Vector2(this.x + this.width, this.y + this.height / 2);
    }
    
    /**
     * Sets values to rectangle.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void set(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	 * Sets the position of the rectangle
	 * @param x
	 * @param y
	 */
	public void setPosition(float x, float y) {
		this.setPosition((int)x, (int)y);
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
}
