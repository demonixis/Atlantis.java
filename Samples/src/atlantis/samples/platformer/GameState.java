package atlantis.samples.platformer;

import java.util.ArrayList;

import atlantis.engine.graphics.Sprite;
import atlantis.engine.state.State;
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
		this.player.addAnimation("lef", new int[] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, 40);
		//this.player.addAnimation("jumpLeft", new int[] { 14, 13, 12 }, 40);
		//this.player.addAnimation("idle", new int[] { 45 }, 0);
		this.player.setPosition(50, 150);
		this.player.play("left");
	}
}
