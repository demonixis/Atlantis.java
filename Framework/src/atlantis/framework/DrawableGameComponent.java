package atlantis.framework;

import java.awt.Graphics;

/**
 * A DrawableGameComponent is a GameComponent that is updated and drawn on each frame.
 * It can be enabled or disabled and visible or invisible
 * @author Yannick
 */
public class DrawableGameComponent extends GameComponent implements IDrawable {
	protected boolean visible;
	
	public DrawableGameComponent(BaseGame game) {
		super(game);
		this.visible = true;
	}
	
	/**
	 * Load assets of the component
	 */
	public void loadContent() { }
	
	/**
	 * Unload and dispose all assets of the component
	 */
	public void unloadContent() { }
	
	/**
	 * Draw the component
	 * @param a Graphics object
	 */
	@Override
	public void draw(Graphics graphics) { }

	/**
	 * Gets the visibility of the component
	 * @return true if visible then false
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Set the component visible or invisible
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
