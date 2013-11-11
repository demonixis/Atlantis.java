package atlantis.samples.shooter;

import java.awt.event.KeyEvent;
import atlantis.engine.Application;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.state.State;
import atlantis.framework.GameTime;

public class MenuScreen extends State {
	Sprite background;
	
	public MenuScreen(String name) {
		super(name);
		this.background = new Sprite("menu.png");
		this.scene.add(background);
	}

	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		if (Application.keyboard.pressed(KeyEvent.VK_ENTER)) {
			this.stateManager.setActive("game");
		}
		
		if (Application.keyboard.escape()) {
			Application.game.exit();
		}
	}
}
