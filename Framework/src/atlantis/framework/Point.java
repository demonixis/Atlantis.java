package atlantis.framework;

/**
 * Define a two dimensional point
 * @author Yannick
 */
public class Point {
	protected int x;
	protected int y;
	
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets X value.
	 * @return Return X value.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets X value.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets Y value.
	 * @return Return Y value.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets Y value.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
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
