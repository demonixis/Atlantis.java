package atlantis.samples.shooter;

import java.awt.event.KeyEvent;

import atlantis.engine.Atlantis;
import atlantis.engine.Timer;
import atlantis.engine.graphics.Entity;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.graphics.SpriteGroup;
import atlantis.engine.state.BaseState;
import atlantis.framework.GameTime;
import atlantis.framework.content.ContentManager;

public class GameScreen extends BaseState {
	private Starfield starfield;
	private Sprite ship;
	private SpriteGroup laserGroup;
	private SpriteGroup alienGroup;
	private Timer shootTimer;
	private Timer spawnTimer;
	
	public GameScreen(String name) {
		super(name);
		
		this.starfield = new Starfield();
		this.ship = new Sprite("ShipR.png");
		this.ship.setViewport(0, 0, Atlantis.width, Atlantis.height);
		this.laserGroup = new SpriteGroup();
		this.alienGroup = new SpriteGroup();
		
		this.scene.add(this.starfield);
		this.scene.add(this.ship);
		this.scene.add(this.laserGroup);
		this.scene.add(this.alienGroup);
		
		this.shootTimer = new Timer(700, 0);
		// Spawn a new alien 
		this.spawnTimer = new Timer(3500, 0); 
	}

	public void initialize() {
		this.ship.prepareAnimation(48, 48);
		this.ship.addAnimation("idle", new int[] { 0 }, 50);
		this.ship.addAnimation("move", new int[] { 1, 2 }, 50);
		this.ship.play("idle");
		this.ship.setPosition(50, Atlantis.height / 2 - this.ship.getHeight() / 2);
		this.ship.setInsideScreen(true);
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);

		this.shootTimer.update(gameTime);
		this.spawnTimer.update(gameTime);
		
		// Spawn a new alien
		if (!this.spawnTimer.isEnabled()) {
			Alien alien = new Alien(Atlantis.width + 50, (int)(Math.random() * Atlantis.height));
			alien.loadContent(Atlantis.content);
			this.alienGroup.add(alien);
			this.spawnTimer.start();
		}
		
		if (Atlantis.keyboard.isKeyDown(KeyEvent.VK_LEFT) ||
				Atlantis.keyboard.isKeyDown(KeyEvent.VK_RIGHT) ||
				Atlantis.keyboard.isKeyDown(KeyEvent.VK_UP) ||
				Atlantis.keyboard.isKeyDown(KeyEvent.VK_DOWN)) {
			this.ship.play("move");
		}
		else {
			this.ship.play("idle");
		}
		
		// Move the ship
		if (Atlantis.keyboard.isKeyDown(KeyEvent.VK_LEFT)) {
			this.ship.setX(this.ship.getX() - 2);
		}
		else if (Atlantis.keyboard.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.ship.setX(this.ship.getX() + 2);
		}
		
		if (Atlantis.keyboard.isKeyDown(KeyEvent.VK_UP)) {
			this.ship.setY(this.ship.getY() - 2);
		}
		else if (Atlantis.keyboard.isKeyDown(KeyEvent.VK_DOWN)) {
			this.ship.setY(this.ship.getY() + 2);
		}
		
		if (Atlantis.keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			Atlantis.game.exit();
		}
		
		// Shoot a laser
		if (Atlantis.keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			if (!this.shootTimer.isEnabled()) {
				Laser laser = new Laser(this.ship.getX() + this.ship.getWidth() + 1, this.ship.getY() + 25);
				laser.loadContent(Atlantis.content);
				this.laserGroup.add(laser);
				this.shootTimer.start();
			}
		} 
		
		// Test if a laser touching an alien
		if (this.alienGroup.getCount() > 0) {
			for (Entity alien : this.alienGroup.getEntities()) {
				if (!alien.isActive()) {
					continue;
				}
				
				for (Entity laser : this.laserGroup.getEntities()) {
					// Laser with alien
					if (laser.getRectangle().intersects(alien.getRectangle())) {
						laser.setActive(false);
						alien.setActive(false);
					}
				}
				
				// Player with alien
				if (this.ship.getRectangle().intersects(alien.getRectangle())) {
					alien.setActive(false);
					this.stateManager.setStateActive("menu", true);
					System.out.println("\nShip: " + this.ship.getRectangle().toString() + "\nAlien: " + alien.getRectangle().toString());
				}
			}
		}
	}
}

/**
 * The star field
 * @author yannick
 *
 */
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

/**
 * A laser 
 * @author yannick
 *
 */
class Laser extends Entity {
	public Laser(int x, int y) {
		super("laser.png");
		this.setPosition(x, y);
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		if (this.rectangle.getRight() > Atlantis.width) {
			this.setActive(false);
		}
		else {
			this.setX((int)(this.position.x + 3));
		}
	}
}

/**
 * An alien
 * @author yannick
 *
 */
class Alien extends Sprite {
	public Alien(int x, int y) {
		super("alien.png");
		this.setPosition(x, y);
	}
	
	public void loadContent(ContentManager content) {
		super.loadContent(content);
		
		this.prepareAnimation(32, 32);
		this.addAnimation("normal", new int[] {0,  1, 2 }, 50);
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		if (this.rectangle.getRight() < 0) {
			this.setActive(false);
		}
		else {
			this.play("normal");
			this.setX((int)(this.position.x - 1));
		}
	}
}