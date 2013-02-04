package atlantis.framework;

import java.awt.Dimension;

import javax.swing.JApplet;

import atlantis.framework.platform.IGamePlatform;
import atlantis.framework.platform.JPanelRenderer;

/**
 * Provide basic initialization of an applet
 * @see Game
 * @author Yannick
 *
 */
public class GameApplet extends JApplet implements IGamePlatform {
	protected JPanelRenderer renderer;
	protected Game game;
	
	public void init() {
		Dimension dim = this.getSize();
		this.setSize(dim.width, dim.height);
		this.setVisible(true);
		this.game = new Game(dim.width, dim.height, "Atlantis Applet");
		this.game.window = this;
		this.setRenderer(this.game.renderer);
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
}