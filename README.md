AtlantisGameEngine4Java
=======================

### Summary
Atlantis Engine is a work in progress Java 2D Game Engine. It is mainly inspired by XNA and Flixel.
This is a port of AtantisEngine.js

### Features
* Content manager 
* Game component system
* Input management (Keyboard, Mouse)
* Sprite with easy animation and a bit of physics
* SpriteGroup
* State and state manager

### The next
* Basic and isometric tilemap
* Scene camera
* More utilities (Inerpolator, etc.)

### Example

```java
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import atlantis.engine.graphics.Entity;
import atlantis.engine.graphics.Sprite;
import atlantis.framework.Game;
import atlantis.framework.GameDesktop;
import atlantis.framework.GameTime;

public class SpriteGame extends GameDesktop {
	private Entity background;
	private Sprite sprite;
	
	public SpriteGame() {
		super(1024, 600, "Atlantis Game Engine for Java - Sprite Sample");
		this.background = new Entity("background.png");
		this.sprite = new Sprite("BRivera-femaleelfwalk.png");
		this.sprite.setViewport(0, 0, this.width, this.height);
		this.sprite.setInsideScreen(true);
	}
	
	public void loadContent() {
		super.loadContent();
		
		this.background.loadContent(this.content);
		this.background.setSize(this.width, this.height);
		
		// Add animation to the sprite
		this.sprite.loadContent(this.content);
		this.sprite.prepareAnimation(64, 64);
		this.sprite.addAnimation("up", new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, 75);
		this.sprite.addAnimation("left", new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17 }, 75);
		this.sprite.addAnimation("down", new int[] { 18, 19, 20, 21, 22, 23, 24, 25, 26 }, 75);
		this.sprite.addAnimation("right", new int[] { 27, 28, 29, 30, 31, 32, 33, 34, 35 }, 75);
	    
	    this.sprite.setSize(72, 72);
	    this.sprite.setPosition(
	    		this.width / 2 - this.sprite.getWidth() / 2,
	    		this.height / 2 - this.sprite.getHeight() / 2);
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		this.sprite.update(gameTime);
		
		if (this.keyboardState.isKeyDown(KeyEvent.VK_UP)) {
			this.sprite.play("up");
			this.sprite.setY(this.sprite.getY() - 1);
		}
		else if (this.keyboardState.isKeyDown(KeyEvent.VK_DOWN)) {
			this.sprite.play("down");
			this.sprite.setY(this.sprite.getY() + 1);
		}
		
		if (this.keyboardState.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.sprite.play("right");
			this.sprite.setX(this.sprite.getX() + 1);
		}
		else if (this.keyboardState.isKeyDown(KeyEvent.VK_LEFT)) {
			this.sprite.play("left");
			this.sprite.setX(this.sprite.getX() - 1);
		}
		
		if (this.keyboardState.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.exit();
		}
	}
	
	public void draw(Graphics graphics) {
		super.draw(graphics);
		this.background.draw(graphics);
		this.sprite.draw(graphics);
	}

	public static void main(String [] args) {
		SpriteGame game = new SpriteGame();
		game.run();
	}
}
```