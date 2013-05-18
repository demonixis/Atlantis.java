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
	
	public int getTop() {
		return this.y;
	}
	
	public int getBottom() {
		return this.y + this.height;
	}
	
	public int getLeft() {
		return this.x;
	}
	
	public int getRight() {
		return this.x + this.width;
	}
	
	/**
	 * Gets the center of the rectangle
	 * @return
	 */
	public Point getCenter() {
		return new Point(this.x + (this.width / 2), this.y + (this.height / 2));
	}
	
	/**
	 * Check if the specified point is inside rectangle
	 * @param point
	 * @return true if the point is inside rectangle, otherwise false
	 */
	public boolean contains(Point point) {
		return (this.x <= point.x) && (point.x < this.getRight()) && (this.y <= point.y) && (point.y < this.getBottom());
	}
	
	/**
	 * Check if the specified rectangle is inside rectangle
	 * @param rectangle
	 * @return true if the rectangle is inside rectangle, otherwise false
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
