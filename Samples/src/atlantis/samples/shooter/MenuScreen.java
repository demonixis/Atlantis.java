package atlantis.samples.shooter;

import java.awt.event.KeyEvent;

import atlantis.engine.Engine;
import atlantis.engine.State;
import atlantis.engine.graphics.Entity;
import atlantis.framework.GameTime;

public class MenuScreen extends State {
	Entity background;
	Entity mask;
	int index;
	
	public MenuScreen(String name) {
		super(name);
		
		this.background = new Entity("menu.png");
		this.mask = new Entity("mask.png");
		this.mask.setPosition(280, 250);
		
		this.scene.add(background);
		this.scene.add(mask);
		this.index = 0;
	}

	public void update(GameTime gameTime) {
		super.update(gameTime);
		if (Engine.keyboard.isKeyDown(KeyEvent.VK_ENTER)) {
			this.stateManager.setStateActive("game", true);
		}
		else if (Engine.keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			Engine.game.exit();
		}
	}
	
	private void incrementIndex() {
		if (++this.index > 1) {
			this.index = 0;
		}
	}
	
	private void decrementIndex() {
		if (--this.index < 0) {
			this.index = 1;
		}
	}
}
