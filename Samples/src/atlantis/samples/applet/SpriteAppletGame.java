package atlantis.samples.applet;

import atlantis.engine.GameApplet;

@SuppressWarnings("serial")
public class SpriteAppletGame extends GameApplet {
	public void init() {
		super.init();
		this.game.contentManager().setRootDirectory("atlantis/samples/content");
		this.game.components().add(new SpriteComponent(this.game));
	}
}
