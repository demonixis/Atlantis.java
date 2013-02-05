package atlantis.samples;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import atlantis.engine.graphics.Entity;
import atlantis.engine.graphics.Sprite;
import atlantis.framework.GameDesktop;
import atlantis.framework.GameTime;

public class SpriteGame extends GameDesktop {
	private Entity background;
	private Entity tree;
	private Entity tree2;
	private Sprite femaleSprite;
	
	public SpriteGame() {
		super(1024, 600, "Atlantis Game Engine for Java - Sprite Sample");
		this.background = new Entity("background.png");
		this.tree = new Entity("Tree.png");
		this.tree2 = new Entity("Tree2.png");
		this.femaleSprite = new Sprite("BRivera-femaleelfwalk.png");
		this.femaleSprite.setViewport(0, 0, this.width, this.height);
		this.femaleSprite.setInsideScreen(true);
	}
	
	public void loadContent() {
		super.loadContent();
		
		this.background.loadContent(this.content);
		this.background.setSize(this.width, this.height);
		
		this.tree.loadContent(content);
		this.tree.setPosition(150, 150);
		this.tree2.loadContent(content);
		this.tree2.setSize(128, 128);
		this.tree2.setPosition(this.width - 250, this.height - 250);
		
		this.femaleSprite.loadContent(this.content);
		this.femaleSprite.prepareAnimation(64, 64);
		this.femaleSprite.addAnimation("up", new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, 75);
		this.femaleSprite.addAnimation("left", new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17 }, 75);
		this.femaleSprite.addAnimation("down", new int[] { 18, 19, 20, 21, 22, 23, 24, 25, 26 }, 75);
		this.femaleSprite.addAnimation("right", new int[] { 27, 28, 29, 30, 31, 32, 33, 34, 35 }, 75);
	    
	    this.femaleSprite.setSize(72, 72);
	    this.femaleSprite.setPosition(
	    		this.width / 2 - this.femaleSprite.getWidth() / 2,
	    		this.height / 2 - this.femaleSprite.getHeight() / 2);
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		this.femaleSprite.update(gameTime);
		
		if (this.keyboardState.isKeyDown(KeyEvent.VK_UP)) {
			this.femaleSprite.play("up");
			this.femaleSprite.setY(this.femaleSprite.getY() - 1);
		}
		else if (this.keyboardState.isKeyDown(KeyEvent.VK_DOWN)) {
			this.femaleSprite.play("down");
			this.femaleSprite.setY(this.femaleSprite.getY() + 1);
		}
		
		if (this.keyboardState.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.femaleSprite.play("right");
			this.femaleSprite.setX(this.femaleSprite.getX() + 1);
		}
		else if (this.keyboardState.isKeyDown(KeyEvent.VK_LEFT)) {
			this.femaleSprite.play("left");
			this.femaleSprite.setX(this.femaleSprite.getX() - 1);
		}
		
		if (this.keyboardState.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.exit();
		}
	}
	
	public void draw(Graphics graphics) {
		super.draw(graphics);
		this.background.draw(graphics);
		this.tree.draw(graphics);
		this.tree2.draw(graphics);
		this.femaleSprite.draw(graphics);
	}

	public static void main(String [] args) {
		SpriteGame game = new SpriteGame();
		game.run();
	}
}
