package atlantis.samples.shooter;

import java.awt.event.KeyEvent;

import atlantis.engine.Atlantis;
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
		
		if (Atlantis.keyboard.isKeyDown(KeyEvent.VK_ENTER)) {
			this.stateManager.setStateActive("game", true);
		}
		else if (Atlantis.keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			Atlantis.game.exit();
		}
	}
}
