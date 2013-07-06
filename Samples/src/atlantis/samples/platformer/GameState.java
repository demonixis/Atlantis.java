package atlantis.samples.platformer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.newdawn.easyogg.OggClip;

import atlantis.engine.Atlantis;
import atlantis.engine.Timer;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.state.State;
import atlantis.framework.GameTime;
import atlantis.framework.Vector2;
import atlantis.framework.audio.SoundEffect;
import atlantis.framework.content.ContentManager;

enum MovementState {
	JumpingUp, JumpingDown, Walking	
}

enum GameMode {
	Playing, Died, Lose, Win
}

public class GameState extends State {
	private static Random random = new Random();
	
	private Sprite player;
	private float playerGravity;
	private Sprite [] layers;
	private ArrayList<Sprite> tiles;
	private ArrayList<Sprite> items;
	private HashMap<String, SoundEffect> soundEffects;
	private OggClip music;
	private Sprite[] overlays;
	private GameMode gameMode;
	
	private MovementState movementState;
	private int jumpHeight;
	private Vector2 initialJumpPosition;
	private float jumpSpeed;
	
	private Timer restartTimer;
	
	private Sprite tempSearchSprite;
	private int tilesSize;
	private int itemsSize;
	
	public GameState(String name) {
		super(name);
		this.layers = new Sprite[3];
		
		// Overlays
		this.overlays = new Sprite[3];
		this.overlays[0] = new Sprite("overlays/you_died.png");
		this.overlays[1] = new Sprite("overlays/you_lose.png");
		this.overlays[2] = new Sprite("overlays/you_win.png");
		
		this.player = new Sprite("img/Player.png");
		this.playerGravity = 9f;
		
		// Tiles and items for easily search
		this.tiles = new ArrayList<Sprite>();
		this.tilesSize = 0;
		this.items = new ArrayList<Sprite>();
		this.itemsSize = 0;
		
		// Sounds
		this.soundEffects = new HashMap<>(7);
		try {
			music = new OggClip(new FileInputStream("Content/Platformer/Sounds/Music.ogg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.gameMode = GameMode.Playing;
		this.restartTimer = new Timer(3500);
		
		// Jumping
		this.movementState = MovementState.Walking;
		this.jumpHeight = 125;
		this.jumpSpeed = 8.5f;
		this.initialJumpPosition = Vector2.Zero();
		
		// For prevent garbage collection in loop
		tempSearchSprite = null;
	}
	
	public void loadContent(ContentManager content) {
		super.loadContent(content);
		
		String[] soundNames = {
			"ExitReached", "GemCollected", "MonsterKilled",
			"PlayerFall", "PlayerJump", "PlayerKilled",
			"Powerup"
		};
		
		for (String name : soundNames) {
			this.soundEffects.put(name, content.loadSound("Sounds/" + name + ".wav"));
		}
		
		// Initialize the Player
		this.player.loadContent(content);
		this.player.prepareAnimation(64, 64);
		this.player.addAnimation("idle", new int[] { 0 }, 0);
		this.player.addAnimation("left", new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 30);
		this.player.addAnimation("right", new int[] { 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 }, 30);
		this.player.addAnimation("jumpLeft", new int[] { 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32 }, 30);
		this.player.addAnimation("jumpRight", new int[] { 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43 }, 30);
		this.player.addAnimation("dieLeft", new int[] { 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54 }, 10);
		this.player.addAnimation("dieRight", new int[] { 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65 }, 10);
		this.player.addAnimation("winLeft", new int[] { 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76 }, 10);
		this.player.addAnimation("winRight", new int[] { 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87 }, 10);
		//this.player.setSize(72, 72);
		
		// Create the level
		this.createLevel(content, 3);
		
		// Overlays
		for (Sprite overlay : this.overlays) {
			overlay.loadContent(content);
			overlay.setPosition(Atlantis.width / 2 - overlay.getWidth() / 2, Atlantis.height / 2 - overlay.getHeight() / 2);
			overlay.setActive(false);
			this.scene.add(overlay);
		}
		
		this.music.play();
	}
	
	private void createLevel(ContentManager content, int levelId) {
		BufferedReader reader = null;
		StringBuilder jsonString = new StringBuilder();

		try {
			reader = new BufferedReader(new FileReader("Content/Platformer/levels/level_" + levelId + ".json"));
			String line;
			while((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
			reader.close();
			
			JSONObject json = new JSONObject(jsonString.toString());
			
			// 1 - Create and add background and layers
			int layerId = json.getInt("layerId");
			this.layers[0] = new Sprite("img/Backgrounds/Layer0_" + layerId + ".png");
			this.layers[1] = new Sprite("img/Backgrounds/Layer1_" + layerId + ".png");
			this.layers[2] = new Sprite("img/Backgrounds/Layer2_" + layerId + ".png");
		
			for (Sprite layer : this.layers) {
				layer.loadContent(content);
				this.scene.add(layer);
			}
			
			JSONArray jsonLevel = json.getJSONArray("level");
			JSONArray row = null;
			Sprite tile = null;
			String assetName = "";
			
			for (int y = 0, ly = jsonLevel.length(); y < ly; y++) {
				row = jsonLevel.getJSONArray(y);
				
				for (int x = 0, lx = row.length(); x < lx; x++) {
					int id = row.getInt(x);
					assetName = "";
					
					switch (id) {
						case 1:
							this.player.setPosition(x * 24, y * 32 - player.getHeight() / 2);
							this.scene.add(this.player);
							break;
						case 2:
						case 3:
						case 4:
						case 5:
						case 6: {
							assetName = getAssetName(id);
							tile = new Sprite("img/Tiles/" + assetName + ".png");
							tile.loadContent(Atlantis.content);
							tile.setPosition(x * 40, y * 32);
							this.scene.add(tile);
							
							if (id == 2 || id == 5 || id == 6) {
								switch (id) {
									case 2: tile.setName("exit"); break;
									case 5: tile.setName("gem"); break;
									case 6: tile.setName("gem2"); break;
								}
								this.items.add(tile);
							}
							else if (id == 3 || id == 4) {
								this.tiles.add(tile);
							}
							
							break;
						}
						case 7:
						case 8:
						case 9:
						case 10:
							break;
					}
				}
			}
			
			this.tilesSize = this.tiles.size();
			this.itemsSize = this.items.size();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getAssetName(int id) {
		String assetName = "";
		switch (id) {
			case 2: assetName = "Exit"; break;
			case 3: assetName = getRandomBlockName(); break;
			case 4: assetName = "Platform"; break;
			case 5: assetName = "Gem"; break;
			case 6: assetName = "YellowGem"; break;
		}
		return assetName;
	}
	
	private String getRandomBlockName() {
		return ("BlockA" + random.nextInt(6)).toString();
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		if (this.gameMode == GameMode.Playing) {
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
				if (this.player.getDirection().x < 0) {
					this.player.play("jumpLeft");
				}
				else {
					this.player.play("jumpRight");
				}
				jump();
			}
			
			if (this.movementState != MovementState.Walking) {
				if (this.movementState == MovementState.JumpingUp) {
					this.player.setPosition(this.player.getX(), (int) (this.player.getY() - this.jumpSpeed));
					if (this.player.getY() < (this.initialJumpPosition.y - this.jumpHeight)) {
						this.movementState = MovementState.JumpingDown;
					}
				}
				
				if (movementState == MovementState.JumpingDown) {
					this.player.setPosition(this.player.getX(), (int) (this.player.getY() + this.jumpSpeed));
				}
				
				if (this.player.getY() >= this.initialJumpPosition.y) {
					this.movementState = MovementState.Walking;
					this.player.setY((int) this.initialJumpPosition.y);
				}
			}
			
			for (int i = 0; i < itemsSize; i++) {
				tempSearchSprite = this.items.get(i);
				
				if (tempSearchSprite.isActive() && this.player.getRectangle().contains(tempSearchSprite.getRectangle())) {
					if (tempSearchSprite.getName() == "exit") {
						if (!this.overlays[2].isActive()) {
							this.overlays[2].setActive(true);
							this.gameMode = GameMode.Win;
							this.soundEffects.get("ExitReached").play();
						}
					}
					else {
						tempSearchSprite.setActive(false);
						this.soundEffects.get("GemCollected").play();
						// Add points to player
						// Play a cool sound effect
						// Add a fade animation
						// Create a real class for items and override setActive (really ? you must do that first ;))
					}
				}
			}
			
			if (this.movementState != MovementState.JumpingUp) {
				this.player.setY((int) (this.player.getY() + this.playerGravity));
				int i = 0;
				boolean collide = false;
				while(i < tilesSize && collide == false) {
					if (this.player.getRectangle().contains(this.tiles.get(i).getRectangle())) {
						collide = true;
						this.player.setY(this.tiles.get(i).getY() - this.player.getHeight());
						this.movementState = MovementState.Walking;
					}
					i++;
				}
			}
			
			if (this.player.getY() > Atlantis.height) {
				if (!this.overlays[1].isActive()) {
					this.overlays[1].setActive(true);
					this.soundEffects.get("PlayerFall").play();
				}
				this.gameMode = GameMode.Lose;
			}
		} 
		else if (this.gameMode == GameMode.Win) {
			this.player.play("winLeft");
			// Skip to next level or back menu
		}
		else if (this.gameMode == GameMode.Lose) {
			// Restart the level after 5 seconds
		}
	}
	
	private void jump() {
		if (this.movementState == MovementState.Walking) {
			this.soundEffects.get("PlayerJump").play();
			this.movementState = MovementState.JumpingUp;
			this.initialJumpPosition = new Vector2(this.player.getX(), this.player.getY());
		}
	}
}
