package atlantis.framework.platform;

/**
 * An interface that define a game plateform window
 * @author Yannick
 */
public interface IGamePlatform {
	/**
	 * Sets the renderer 
	 * @param renderer
	 */
	public void setRenderer(JPanelRenderer renderer);
	
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
	 * Toggle in fullscreen mode or window mode
	 */
	public void toggleFullscreen();
}
