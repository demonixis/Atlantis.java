package atlantis.samples.platformer;

import java.util.ArrayList;

import atlantis.engine.Atlantis;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.state.State;
import atlantis.framework.GameTime;
import atlantis.framework.content.ContentManager;

public class GameState extends State {
	private Sprite background;
	private Sprite layer;
	private Sprite subLayer;
	private Sprite player;
	private ArrayList<Sprite> tiles;
	
	public GameState(String name) {
		super(name);
		this.background = new Sprite("img/Backgrounds/Layer0_0.png");
		this.scene.add(this.background);
		
		this.layer = new Sprite("img/Backgrounds/Layer1_0.png");
		this.scene.add(this.layer);
		
		this.subLayer = new Sprite("img/Backgrounds/Layer2_0.png");
		this.scene.add(this.subLayer);
		
		this.player = new Sprite("img/Player.png");
		this.scene.add(this.player);
		
		this.tiles = new ArrayList();
	}
	
	public void loadContent(ContentManager content) {
		this.background.loadContent(content);
		this.layer.loadContent(content);
		this.player.loadContent(content);
		this.player.prepareAnimation(64, 64);
		//this.player.addAnimation("lef", new int[] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, 40);
		//this.player.addAnimation("right", new int[] { 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12 }, 40);
		this.player.addAnimation("left", new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 }, 60);
		this.player.addAnimation("right", new int[] { 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 }, 60);
		this.player.setPosition(50, 150);
		this.player.play("right");
	}
	
	public void update(GameTime gameTime) {
		if (Atlantis.keyboard.left()) {
			this.player.play("left");
			this.player.setPosition(this.player.getX() - 2, this.player.getY());
		}
		else if (Atlantis.keyboard.right()) {
			this.player.play("right");
			this.player.setPosition(this.player.getX() + 2, this.player.getY());
		}
	}
}
