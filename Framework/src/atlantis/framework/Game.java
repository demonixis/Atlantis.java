package atlantis.framework;

import atlantis.framework.input.KeyboardManager;
import atlantis.framework.input.MouseManager;
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
		desktopPlatform.addKeyListener((KeyboardManager)this.keyboardState);
		desktopPlatform.addMouseListener((MouseManager)this.mouseState);
		desktopPlatform.addMouseMotionListener((MouseManager)this.mouseState);
		desktopPlatform.addMouseWheelListener((MouseManager)this.mouseState);
	}
}
