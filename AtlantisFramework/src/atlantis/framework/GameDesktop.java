package atlantis.framework;

import atlantis.framework.platform.DesktopPlatform;

/**
 * The GameDesktop provide basic initialization and use a desktop window implementation (with swing) 
 * @see Game
 * @author Yannick
 */
public class GameDesktop extends Game {
	
	public GameDesktop(int width, int height, String title) {
		super(width, height, title);
		
		DesktopPlatform desktopPlatform = new DesktopPlatform(width, height, title);
		desktopPlatform.setRenderer(this.renderer);
		desktopPlatform.addKeyListener(this.keyboardState);
		desktopPlatform.addMouseListener(this.mouseState);
		desktopPlatform.addMouseMotionListener(this.mouseState);
		desktopPlatform.addMouseWheelListener(this.mouseState);
		this.window = desktopPlatform;
	}
}