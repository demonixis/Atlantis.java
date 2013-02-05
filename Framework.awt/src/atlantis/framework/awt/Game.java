package atlantis.framework.awt;

import java.awt.Graphics;

import atlantis.framework.BaseGame;
import atlantis.framework.content.ContentManager;
import atlantis.framework.input.IKeyboardState;
import atlantis.framework.input.IMouseState;
import atlantis.framework.input.awt.KeyboardState;
import atlantis.framework.input.awt.MouseState;
import atlantis.framework.platform.DesktopPlatform;
import atlantis.framework.platform.IGamePlatform;
import atlantis.framework.platform.JPanelRenderer;

/**
 * The Game class provide basic initialization and game logic. 
 * It implements GameState pattern.
 * @author Yannick
 *
 */
public class Game extends BaseGame {
	
	public Game(int width, int height, String title) {
		super(width, height, title);
		this.keyboardState = new KeyboardState();
		this.mouseState = new MouseState();
		
		// The renderer
		this.renderer = new JPanelRenderer();
		this.renderer.addDrawable(this.components);
		this.renderer.addDrawable(this);
		
		DesktopPlatform desktopPlatform = new DesktopPlatform(width, height, title);
		desktopPlatform.setRenderer(this.renderer);
		desktopPlatform.addKeyListener((KeyboardState)this.keyboardState);
		desktopPlatform.addMouseListener((MouseState)this.mouseState);
		desktopPlatform.addMouseMotionListener((MouseState)this.mouseState);
		desktopPlatform.addMouseWheelListener((MouseState)this.mouseState);
	}
}
