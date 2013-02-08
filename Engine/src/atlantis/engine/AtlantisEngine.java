package atlantis.engine;

import atlantis.framework.Game;

/**
 * AtlantisEngine is the bootstrap class that create the game and prepare
 * all objects used for the game
 */
public class AtlantisEngine extends Game {
	protected StateManager stateManager;
	
	public AtlantisEngine(int width, int height, String title) {
		super(width, height, title);
		
		this.stateManager = new StateManager(this);
		this.components.add(this.stateManager);
		
		Atlantis.game = this;
		Atlantis.content = this.content;
		Atlantis.components = this.components;
		Atlantis.keyboard = this.keyboardState;
		Atlantis.mouse = this.mouseState;
		Atlantis.width = this.width;
		Atlantis.height = this.height;
	}
	
	public AtlantisEngine(int width, int height) {
		this(width, height, "Atlantis Game");
	}
	
	/**
	 * Enabled or disable the state manager
	 * @param isActive
	 */
	public void setStateManager(boolean isActive) {
		if (isActive) {
			if (this.stateManager == null) {
				this.stateManager = new StateManager(this);
				this.components.add(this.stateManager);
			}
		}
		else {
			if (this.stateManager != null) {
				this.components.remove(this.stateManager);
				this.stateManager = null;
			}
		}
	}
}
