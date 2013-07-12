package atlantis.samples.platformer;

import atlantis.engine.Atlantis;
import atlantis.engine.GameApplication;
import atlantis.framework.GameTime;

public class PlatformerGame extends GameApplication {
	public PlatformerGame() {
		super(800, 520, "Atlantis :: Platformer 2D sample");
		this.content.setRootDirectory("Content/Platformer/");
	}
	
	public void initialize() {
		super.initialize();
		GameState gameState = new GameState("game", 3);
		this.stateManager.add(gameState, true, false);
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		if (Atlantis.keyboard.escape()) {
			this.exit();
		}
	}
	
	public static void main(String[] args) {
		PlatformerGame game = new PlatformerGame();
		game.run();
	}
}
