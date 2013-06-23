// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * The game loop 
 * @author Yann
 *
 */
public class GameLoop implements Runnable {
	private Game game;
	
	public GameLoop(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		while(this.game.isRunning) {
			this.game.gameTime.update();
			
			for (int i = 0; i < 2; i++) {
				this.game.update(this.game.gameTime);
			}
		
			this.game.windowRenderer.repaint();
			
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