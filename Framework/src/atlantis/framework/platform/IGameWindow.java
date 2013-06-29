// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.platform;


/**
 * An interface that define a game platform window
 * @author Yannick
 */
public interface IGameWindow {
	/**
	 * Exit
	 */
	public void exit();
	
	/**
	 * Change the size of the window
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height);
	
	/**
	 * Toggle in full screen mode or window mode
	 */
	public void toggleFullscreen();
	
	/**
	 * Sets the renderer to use.
	 * @param renderer The renderer to use.
	 */
	public void setRenderer(IWindowRenderer renderer);
	
	/**
	 * Gets the active window renderer
	 * @return Return the window renderer
	 */
	public JPanelRenderer getRenderer();
}
