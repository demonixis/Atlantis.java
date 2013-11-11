// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine;

import atlantis.engine.input.KeyboardComponent;
import atlantis.engine.input.MouseComponent;
import atlantis.engine.state.StateManager;
import atlantis.framework.Game;

/**
 * AtlantisEngine is the bootstrap class that create the game and prepare
 * all objects used for the game
 */
public class GameApplication extends Game {
	protected StateManager stateManager;
	
	public GameApplication(int width, int height, String title) {
		super(width, height, title);
		
		KeyboardComponent keyboardComponent = new KeyboardComponent(this);
		MouseComponent mouseComponent = new MouseComponent(this);
		
		this.stateManager = new StateManager(this);
		this.components.add(this.stateManager);
		this.components.add(keyboardComponent);
		this.components.add(mouseComponent);
		
		Application.game = this;
		Application.stateManager = stateManager;
		Application.content = this.content;
		Application.components = this.components;
		Application.keyboard = keyboardComponent;
		Application.mouse = mouseComponent;
		Application.width = this.width;
		Application.height = this.height;
	}
	
	public GameApplication(int width, int height) {
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
