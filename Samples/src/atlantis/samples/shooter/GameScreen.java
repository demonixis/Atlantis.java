package atlantis.samples.shooter;

import java.awt.event.KeyEvent;

import atlantis.engine.Engine;
import atlantis.engine.State;
import atlantis.engine.Timer;
import atlantis.engine.graphics.Entity;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.graphics.SpriteGroup;
import atlantis.framework.GameTime;
import atlantis.framework.graphics.Texture2D;

public class GameScreen extends State {
	private Starfield starfield;
	private Sprite ship;
	private SpriteGroup ammos;
	private Timer shootTimer;
	
	public GameScreen(String name) {
		super(name);
		
		this.starfield = new Starfield();
		this.ship = new Sprite("ShipR.png");
		this.ship.setViewport(0, 0, Engine.width, Engine.height);
		this.ammos = new SpriteGroup();
		
		this.scene.add(this.starfield);
		this.scene.add(this.ship);
		this.scene.add(this.ammos);
		
		this.shootTimer = new Timer(1000, 0);
	}

	public void initialize() {
		this.ship.prepareAnimation(48, 48);
		this.ship.addAnimation("idle", new int[] { 0 }, 50);
		this.ship.addAnimation("move", new int[] { 1, 2 }, 50);
		this.ship.play("idle");
		this.ship.setPosition(50, Engine.height / 2 - this.ship.getHeight() / 2);
		this.ship.setInsideScreen(true);
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);

		this.shootTimer.update(gameTime);
		
		if (Engine.keyboard.isKeyDown(KeyEvent.VK_LEFT) ||
				Engine.keyboard.isKeyDown(KeyEvent.VK_RIGHT) ||
				Engine.keyboard.isKeyDown(KeyEvent.VK_UP) ||
				Engine.keyboard.isKeyDown(KeyEvent.VK_DOWN)) {
			this.ship.play("move");
		}
		else {
			this.ship.play("idle");
		}
		
		// Move the ship
		if (Engine.keyboard.isKeyDown(KeyEvent.VK_LEFT)) {
			this.ship.setX(this.ship.getX() - 1);
		}
		else if (Engine.keyboard.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.ship.setX(this.ship.getX() + 1);
		}
		
		if (Engine.keyboard.isKeyDown(KeyEvent.VK_UP)) {
			this.ship.setY(this.ship.getY() - 1);
		}
		else if (Engine.keyboard.isKeyDown(KeyEvent.VK_DOWN)) {
			this.ship.setY(this.ship.getY() + 1);
		}
		
		// Shoot a laser
		if (Engine.keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			if (!this.shootTimer.isEnabled()) {
				Laser laser = new Laser(this.ship.getX() + this.ship.getWidth() + 1, this.ship.getY() + 25);
				laser.loadContent(Engine.content);
				this.ammos.add(laser);
				this.shootTimer.start();
			}
		} 
	}
}

class Starfield extends SpriteGroup {
	Entity [] starfields;
	
	public Starfield() {
		this.starfields = new Entity[2];
		
		for (int i = 0; i < 2; i++) {
			this.starfields[i] = new Entity("starfield.png");
			this.starfields[i].setPosition(i * 800, 0);
			this.add(this.starfields[i]);
		}
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		for (Entity entity : this.starfields) {
			if (entity.getX() <= -800) {
				entity.setX(800);
			}
			entity.setX(entity.getX() - 1);
		}
	}
}

class Laser extends Entity {
	public Laser(int x, int y) {
		super("laser.png");
		this.setPosition(x, y);
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		if (this.rectangle.getRight() > Engine.width) {
			this.enabled = false;
			this.visible = false;
		}
		else {
			this.setX((int)(this.position.getX() + 3));
		}
	}
}
