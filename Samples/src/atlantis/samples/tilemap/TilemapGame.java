package atlantis.samples.tilemap;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import atlantis.engine.graphics.Entity;
import atlantis.engine.graphics.Sprite;
import atlantis.framework.Game;
import atlantis.framework.GameTime;
import atlantis.input.KeyboardComponent;

public class TilemapGame extends Game {

	
	public TilemapGame() {
		super(1024, 600, "Atlantis Game Engine for Java - Tilemap Sample");

	}
	
	public void loadContent() {
		super.loadContent();
		
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
	
	}
	
	public void draw(Graphics graphics) {
		super.draw(graphics);

	}

	public static void main(String [] args) {
		TilemapGame game = new TilemapGame();
		game.run();
	}
}
