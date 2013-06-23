// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * An interface to update object
 * @author Yannick
 */
public interface IUpdateable {
	/**
	 * Update logic
	 * @param gameTime
	 */
	public void update(GameTime gameTime);
}
