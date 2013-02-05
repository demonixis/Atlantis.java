package atlantis.samples.shooter;

import java.awt.event.KeyEvent;

import atlantis.engine.Atlantis;
import atlantis.engine.State;
import atlantis.engine.graphics.Entity;
import atlantis.framework.GameTime;

public class MenuScreen extends State {
	Entity background;
	
	public MenuScreen(String name) {
		super(name);
		this.background = new Entity("menu.png");
		this.scene.add(background);
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
