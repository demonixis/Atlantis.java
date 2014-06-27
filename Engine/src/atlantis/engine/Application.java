// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine;

import atlantis.engine.level.LevelManager;
import atlantis.framework.Game;
import atlantis.framework.GameComponentCollection;
import atlantis.framework.content.ContentManager;

/**
 * Registry objects used by the game
 * @author Yannick
 */
public class Application {
	public static Game game;
	public static ContentManager content;
	public static GameComponentCollection components;
	protected static LevelManager levelManager;
	
	public static void loadLevel(int id) {
		Application.levelManager.loadLevel(id);
	}
	
	public static void loadLevel(String name) {
		Application.levelManager.loadLevel(name);
	}
}
