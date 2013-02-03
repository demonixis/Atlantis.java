package atlantis.framework;

/**
 * An interface to load and unload content.
 * @author Yannick
 */
public interface IContentable {
	
	/**
	 * Load the content
	 */
	public void loadContent();

	/**
	 * Unload the content
	 */
	public void unloadContent();
}
