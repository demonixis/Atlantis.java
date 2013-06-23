// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

import atlantis.framework.graphics.JPanelRenderer;
import atlantis.framework.input.KeyboardManager;
import atlantis.framework.input.MouseManager;
import atlantis.framework.platform.DesktopPlatform;

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
		desktopPlatform.addKeyListener((KeyboardManager)this.keyboardManager);
		desktopPlatform.addMouseListener((MouseManager)this.mouseManager);
		desktopPlatform.addMouseMotionListener((MouseManager)this.mouseManager);
		desktopPlatform.addMouseWheelListener((MouseManager)this.mouseManager);
	}
}
