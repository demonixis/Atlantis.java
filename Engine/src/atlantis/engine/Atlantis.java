package atlantis.engine;

import atlantis.framework.BaseGame;
import atlantis.framework.GameComponentCollection;
import atlantis.framework.content.ContentManager;
import atlantis.framework.input.IKeyboardState;
import atlantis.framework.input.IMouseState;

/**
 * Registry objects used by the game
 * @author Yannick
 */
public class Atlantis {
	public static BaseGame game;
	public static ContentManager content;
	public static GameComponentCollection components;
	public static IKeyboardState keyboard;
	public static IMouseState mouse;
	public static int width;
	public static int height;
}
