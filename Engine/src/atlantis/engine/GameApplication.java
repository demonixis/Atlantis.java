// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine;

import atlantis.engine.input.KeyboardComponent;
import atlantis.engine.input.MouseComponent;
import atlantis.engine.level.LevelManager;
import atlantis.framework.Game;

/**
 * AtlantisEngine is the bootstrap class that create the game and prepare
 * all objects used for the game
 */
public class GameApplication extends Game {
	protected LevelManager levelManager;
	
	public GameApplication(int width, int height, String title) {
		super(width, height, title);
		
		KeyboardComponent keyboardComponent = new KeyboardComponent(this);
		MouseComponent mouseComponent = new MouseComponent(this);
		
		this.levelManager = new LevelManager(this);
		this.components.add(this.levelManager);
		this.components.add(keyboardComponent);
		this.components.add(mouseComponent);
		
		Application.game = this;
		Application.levelManager = levelManager;
		Application.content = this.content;
		Application.components = this.components;
		
		Input.keys = keyboardComponent;
		Input.mouse = mouseComponent;
	
		Screen.setup(this.width, this.height);
	}
	
	public GameApplication(int width, int height) {
		this(width, height, "Atlantis Game");
	}
}
