// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine;

import atlantis.engine.input.KeyboardHelper;
import atlantis.engine.input.MouseHelper;
import atlantis.framework.Game;
import atlantis.framework.GameComponentCollection;
import atlantis.framework.content.ContentManager;
import atlantis.framework.input.KeyboardManager;
import atlantis.framework.input.MouseManager;

/**
 * Registry objects used by the game
 * @author Yannick
 */
public class Atlantis {
	public static Game game;
	public static ContentManager content;
	public static GameComponentCollection components;
	
	/**
	 * @deprecated
	 */
	public static KeyboardManager keyboard;
	/**
	 * @deprecated
	 */
	public static MouseManager mouse;
	
	public static KeyboardHelper keyboardH;
	public static MouseHelper mouseH;
	public static int width;
	public static int height;
}
