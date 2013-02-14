package atlantis.samples.tilemap;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import atlantis.engine.graphics.Entity;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.graphics.tilemap.Layer;
import atlantis.framework.Game;
import atlantis.framework.GameTime;
import atlantis.framework.graphics.Texture2D;
import atlantis.input.KeyboardComponent;

public class TilemapGame extends Game {
	private Layer layer;
	
	public TilemapGame() {
		super(1024, 600, "Atlantis Game Engine for Java - Tilemap Sample");
		
		int [][] tiles = {
			{0, 0, 0, 0, 0, 0},
			{0, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 0},
			{0, 0, 0, 0, 0, 0},
		};
		
		Texture2D tileset = this.getContentManager().loadTexture("tileset.png");
		
		layer = new Layer(tileset, tiles);
		
	}
	
	public void loadContent() {
		super.loadContent();
		
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
	
	}
	
	public void draw(Graphics graphics) {
		super.draw(graphics);
		layer.draw(graphics);
	}

	public static void main(String [] args) {
		TilemapGame game = new TilemapGame();
		game.run();
	}
}
