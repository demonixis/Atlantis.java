package atlantis.framework;

import atlantis.framework.input.KeyboardState;
import atlantis.framework.input.MouseState;
import atlantis.framework.platform.DesktopPlatform;
import atlantis.framework.platform.JPanelRenderer;

/**
 * The Game class provide basic initialization and game logic. 
 * It implements GameState pattern.
 * @author Yannick
 */
public class Game extends BaseGame {
	
	public Game(int width, int height, String title) {
		super(width, height, title);
		
		// The renderer
		this.renderer = new JPanelRenderer();
		this.renderer.addDrawable(this.components);
		this.renderer.addDrawable(this);
		
		// The window container
		DesktopPlatform desktopPlatform = new DesktopPlatform(width, height, title);
		desktopPlatform.setRenderer(this.renderer);
		desktopPlatform.addKeyListener((KeyboardState)this.keyboardState);
		desktopPlatform.addMouseListener((MouseState)this.mouseState);
		desktopPlatform.addMouseMotionListener((MouseState)this.mouseState);
		desktopPlatform.addMouseWheelListener((MouseState)this.mouseState);
	}
}
