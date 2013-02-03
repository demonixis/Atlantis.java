package atlantis.framework;

import java.awt.Graphics;

/**
 * An interface to draw objects on screen
 * @author Yannick
 */
public interface IDrawable {
	/**
	 * Draw object on the screen
	 * @param graphics
	 */
	public void draw(Graphics graphics);
}
