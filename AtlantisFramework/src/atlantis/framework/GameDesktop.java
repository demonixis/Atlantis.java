package atlantis.framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import atlantis.framework.content.ContentManager;
import atlantis.framework.input.KeyboardState;
import atlantis.framework.input.MouseState;
import atlantis.framework.platform.DesktopPlatform;
import atlantis.framework.platform.JPanelRenderer;

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