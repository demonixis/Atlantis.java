// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * A DrawableGameComponent is a GameComponent that is updated and drawn on each frame.
 * It can be enabled or disabled and visible or invisible
 * @author Yannick
 */
public class DrawableGameComponent extends GameComponent implements IDrawable {
	protected boolean visible;
	protected boolean assetLoaded;
	
	public DrawableGameComponent(Game game) {
		super(game);
		this.visible = true;
	}
	
	/**
	 * Load assets of the component
	 */
	public void loadContent() {
		this.assetLoaded = true;
	}
	
	/**
	 * Unload and dispose all assets of the component
	 */
	public void unloadContent() {
		this.assetLoaded = false;
	}
	
	/**
	 * Draw the component
	 * @param a Graphics object
	 */
	@Override
	public void draw(GameTime gameTime) { }
	
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
