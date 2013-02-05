package atlantis.engine;

import atlantis.framework.Game;
import atlantis.framework.GameComponentCollection;
import atlantis.framework.content.ContentManager;
import atlantis.framework.input.KeyboardState;
import atlantis.framework.input.MouseState;

/**
 * Registry objects used by the game
 * @author Yannick
 */
public class Engine {
	public static Game game;
	public static ContentManager content;
	public static GameComponentCollection components;
	public static KeyboardState keyboard;
	public static MouseState mouse;
	public static int width;
	public static int height;
}
