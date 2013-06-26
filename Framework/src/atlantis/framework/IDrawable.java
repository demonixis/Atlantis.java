// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
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
