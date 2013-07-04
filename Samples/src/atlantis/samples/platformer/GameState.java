package atlantis.samples.platformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import atlantis.engine.Atlantis;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.state.State;
import atlantis.framework.GameTime;
import atlantis.framework.content.ContentManager;

public class GameState extends State {
	private static Random random = new Random();
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
		this.player.addAnimation("left", new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, 30);
		this.player.addAnimation("right", new int[] { 19, 18, 17, 16, 15, 14, 13, 12, 11, 10 }, 30);
		this.player.addAnimation("jumpLeft", new int[] { 20, 21, 22 }, 30);
		this.player.addAnimation("jumpRight", new int[] { 23, 24, 25 }, 30);
		this.player.addAnimation("idle", new int[] { 26 }, 30);
		this.player.setPosition(50, 350);
		this.player.setSize(72, 72);
		this.createLevel();
	}
	
	private void createLevel() {
		BufferedReader reader = null;
		StringBuilder jsonString = new StringBuilder();

		try {
			reader = new BufferedReader(new FileReader("Content/Platformer/levels/level1.json"));
			String line;
			while((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
			reader.close();
			JSONArray json = new JSONArray(jsonString.toString());
			JSONArray row = null;
			Sprite tile;
			
			for (int y = 0, ly = json.length(); y < ly; y++) {
				row = json.getJSONArray(y);
				
				for (int x = 0, lx = row.length(); x < lx; x++) {
					int id = row.getInt(x);
					
					if (id == 1) {
						player.setPosition(x * 24, y * 32 - player.getHeight() / 2);
					}
					
					else if (id == 2 || id == 5 || id == 9) {
						String asset = "img/Tiles/Gem.png";
						asset = (id == 2) ? "img/Tiles/Exit.png" : asset;
						asset = (id == 5) ? getRandomBlockName() : asset;
						asset = (id == 6) ? "img/Tiles/Platform.png" : asset;
						tile = new Sprite(asset);
						tile.loadContent(Atlantis.content);
						tile.setPosition(x * 40, y * 32);
						this.scene.add(tile);
						//tiles.add(tile);
					}
				}
			}
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getRandomBlockName() {
		return ("img/Tiles/BlockA" + random.nextInt(6) + ".png").toString();
	}
	
	public void update(GameTime gameTime) {
		this.player.update(gameTime);
		if (Atlantis.keyboard.left()) {
			this.player.play("left");
			this.player.setPosition(this.player.getX() - 2, this.player.getY());
		}
		else if (Atlantis.keyboard.right()) {
			this.player.play("right");
			this.player.setPosition(this.player.getX() + 2, this.player.getY());
		}
		else {
			this.player.play("idle");
		}
		
		if (Atlantis.keyboard.space()) {
			this.player.play("jumpLeft");
			//this.player.setPosition(this.player.getX() + 2, this.player.getY());
		}
	}
}
