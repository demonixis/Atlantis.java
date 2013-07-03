package atlantis.samples.platformer;

import atlantis.engine.GameApplication;

public class PlatformerGame extends GameApplication {
	public PlatformerGame() {
		super(800, 480, "Atlantis :: Platformer 2D sample");
		this.content.setRootDirectory("Content/Platformer/");
	}
	
	public void initialize() {
		GameState gameState = new GameState("game");
		this.stateManager.add(gameState, true, false);
	}
	
	public static void main(String[] args) {
		PlatformerGame game = new PlatformerGame();
		game.run();
	}
}
