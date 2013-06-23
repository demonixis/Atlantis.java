// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine;

import atlantis.engine.input.KeyboardComponent;
import atlantis.engine.input.MouseComponent;
import atlantis.engine.state.StateManager;
import atlantis.framework.Game;
import atlantis.framework.GameComponentCollection;
import atlantis.framework.content.ContentManager;

/**
 * Registry objects used by the game
 * @author Yannick
 */
public class Atlantis {
	public static Game game;
	public static ContentManager content;
	public static GameComponentCollection components;
	public static StateManager stateManager;
	public static KeyboardComponent keyboard;
	public static MouseComponent mouse;
	public static int width;
	public static int height;
}
