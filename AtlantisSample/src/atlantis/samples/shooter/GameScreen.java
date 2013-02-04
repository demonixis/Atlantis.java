package atlantis.samples.shooter;

import java.awt.event.KeyEvent;

import atlantis.engine.Engine;
import atlantis.engine.State;
import atlantis.engine.graphics.Entity;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.graphics.SpriteGroup;
import atlantis.framework.GameTime;
import atlantis.framework.graphics.Texture2D;

public class GameScreen extends State {
	Starfield starfield;
	Sprite ship;
	SpriteGroup ammos;
	Texture2D ammo;
	
	public GameScreen(String name) {
		super(name);
		
		this.starfield = new Starfield();
		this.ship = new Sprite("ShipR.png");
		this.ship.setViewport(0, 0, Engine.width, Engine.height);
		this.ammos = new SpriteGroup();
		//this.ammo = new Texture2D(Engine.content.getRootDirectory() + "/ammo.png");
		
		this.scene.add(this.starfield);
		this.scene.add(this.ship);
	}

	public void initialize() {
		this.ship.setPosition(50, Engine.height / 2 - this.ship.getHeight() / 2);
		this.ship.setInsideScreen(true);
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
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
