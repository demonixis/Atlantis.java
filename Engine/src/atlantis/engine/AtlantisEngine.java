package atlantis.engine;

import atlantis.framework.GameDesktop;

/**
 * AtlantisEngine is the bootstrap class that create the game and prepare
 * all objects used for the game
 */
public class AtlantisEngine extends GameDesktop {
	protected StateManager stateManager;
	
	public AtlantisEngine(int width, int height, String title) {
		super(width, height, title);
		
		this.stateManager = new StateManager(this);
		this.components.add(this.stateManager);
		
		Engine.game = this;
		Engine.content = this.content;
		Engine.components = this.components;
		Engine.keyboard = this.keyboardState;
		Engine.mouse = this.mouseState;
		Engine.width = this.width;
		Engine.height = this.height;
	}
	
	public AtlantisEngine(int width, int height) {
		this(width, height, "Atlantis Game");
	}
	
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
