package atlantis.framework;

/**
 * Define a rectangle with coordinates and size
 * @author Yannick
 */
public class Rectangle {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public Rectangle() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
		return this.contains((int)vector.getX(), (int)vector.getY());
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
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
