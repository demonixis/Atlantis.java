// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * A GameComponent is a component that is updated on each frame.
 * It can be enabled or disabled
 * @author Yannick
 */
public class GameComponent implements IUpdateable {
	protected Game game;
	protected boolean enabled;
	protected boolean initialized;

	public GameComponent(Game game) {
		this.game = game;
		this.enabled = true;
		this.initialized = false;
	}
	
	public void initialize() { }

	@Override
	public void update(GameTime gameTime) { }
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
	public boolean isInitialized() {
		return this.initialized;
	}
}
