// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

import atlantis.framework.graphics.RenderTarget2D;
import atlantis.framework.platform.GameWindow;


/**
 * The game loop 
 * @author Yann
 *
 */
public class GameLoop implements Runnable {
	private Game game;
	private GameWindow gameWindow;
	private RenderTarget2D renderTarget;
	
	public GameLoop(Game game) {
		this.game = game;
		this.gameWindow = (GameWindow) game.getGameWindow();
		this.renderTarget = game.graphicsDevice.getRenderTarget();
	}
	
	@Override
	public void run() {
		while(this.game.isRunning) {
			this.game.gameTime.update();
			//for (int i = 0; i < 2; i++)
			this.game.update(this.game.gameTime);
			this.game.draw(renderTarget.getGraphics());
			this.gameWindow.getRenderer().repaint();
			
			// TODO : Use a correct value
			try {
				Thread.sleep(10);
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}