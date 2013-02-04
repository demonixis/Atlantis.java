package atlantis.samples.shooter;

import java.awt.event.KeyEvent;

import atlantis.engine.AtlantisEngine;
import atlantis.engine.Engine;
import atlantis.framework.GameTime;

public class ShooterGame extends AtlantisEngine {
	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	
	public ShooterGame() {
		super(800, 600, "Atlantis Engine - Sample : Shooter Game");
		
		this.gameScreen = new GameScreen("game");
		this.menuScreen = new MenuScreen("menu");
		this.content.setRootDirectory("Content/Shooter");
		
		this.stateManager.add(menuScreen, true, false);
		this.stateManager.add(gameScreen, false, false);
	}

	public static void main(String [] args) {
		ShooterGame game = new ShooterGame();
		game.run();
	}
}
