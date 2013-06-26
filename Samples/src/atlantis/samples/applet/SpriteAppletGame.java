package atlantis.samples.applet;

import atlantis.engine.GameApplet;

@SuppressWarnings("serial")
public class SpriteAppletGame extends GameApplet {
	public void init() {
		super.init();
		this.game.getContentManager().setRootDirectory("atlantis/samples/content");
		this.game.addComponent(new SpriteComponent(this.game));
	}
}
