// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JApplet;
import atlantis.framework.Game;
import atlantis.framework.GameTime;
import atlantis.framework.IDrawable;
import atlantis.framework.IUpdateable;
import atlantis.framework.graphics.JPanelRenderer;
import atlantis.framework.platform.IGamePlatform;

/**
 * Provide basic initialization of an applet
 * @see Game
 * @author Yannick
 */
public class GameApplet extends JApplet implements IGamePlatform, IUpdateable, IDrawable {
	private static final long serialVersionUID = 5413818823844604622L;
	protected JPanelRenderer renderer;
	protected Game game;
	
	public void init() {
		Dimension dim = this.getSize();
		this.setSize(dim.width, dim.height);
		this.setVisible(true);
		this.game = new Game(dim.width, dim.height, "Atlantis Applet");
		this.game.setWindow(this);
		this.game.getContentManager().setLoadType(0);
		this.game.getRenderer().addDrawable(this);
		
		Atlantis.game = this.game;
		Atlantis.content = this.game.getContentManager();
		Atlantis.components = this.game.getComponents();
		Atlantis.keyboard = this.game.getKeyboardManager();
		Atlantis.mouse = this.game.getMouseState();
		Atlantis.width = this.getWidth();
		Atlantis.height = this.getHeight();
	}
	
	public void start() {
		this.game.run();
	}
	
	public void setRenderer(JPanelRenderer renderer) {
		this.renderer = renderer;
		this.setContentPane(this.renderer);
	}

	@Override
	public void exit() {

	}

	@Override
	public void toggleFullscreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameTime gameTime) {
		this.game.update(gameTime);
	}

	@Override
	public void draw(Graphics graphics) {
		this.game.draw(graphics);
	}
}