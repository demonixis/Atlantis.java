package atlantis.samples.platformer;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

import atlantis.engine.Application;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.state.State;
import atlantis.framework.GameTime;
import atlantis.framework.audio.Song;
import atlantis.framework.audio.SoundEffect;
import atlantis.framework.content.ContentManager;
import atlantis.framework.graphics.SpriteFont;

public class GameState extends State {
	enum GameMode {
		Playing, Died, Lose, Win
	}
	
	private final int LevelCount = 4;
	
	private int levelId;
	private Player player;
	private Level level;
	private HashMap<String, SoundEffect> soundEffects;
	private Song music;
	private Sprite[] overlays;
	private GameMode gameMode;
	private Sprite tempSearchSprite;
	private SpriteFont scoreCounter;
	private SpriteFont timeCounter;
	private int playerScore;
	private int timeRemaining;
	private int elapsedTime;
	
	public GameState(String name, int levelStart) {
		super(name);
		
		// Overlays
		this.overlays = new Sprite[3];
		this.overlays[0] = new Sprite("overlays/you_died.png");
		this.overlays[1] = new Sprite("overlays/you_lose.png");
		this.overlays[2] = new Sprite("overlays/you_win.png");
		
		this.levelId = levelStart;
		this.level = new Level(this.levelId);
		
		// Player
		this.player = new Player();
		
		// Sound
		this.soundEffects = new HashMap<>();
		
		// Text
		this.scoreCounter = new SpriteFont(Font.SANS_SERIF, 16, Font.BOLD);
		this.timeCounter = new SpriteFont(Font.SANS_SERIF, 16, Font.BOLD);
		
		// For prevent garbage collection in loop
		tempSearchSprite = null;
	}
	
	public void initialize() {
		super.initialize();

		this.playerScore = 0;
		this.elapsedTime = 0;
		this.timeRemaining = 250;
		
		for(Sprite overlay : this.overlays) {
			overlay.setActive(false);
		}
		
		this.player.reset();
		this.gameMode = GameMode.Playing;
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
		
		this.level.loadLevel(content, this.scene);
		
		this.player.loadContent(content);
		this.player.setStartPosition(this.level.getStartPosition());
		this.scene.add(this.player);
		
		// Overlays
		for (Sprite overlay : this.overlays) {
			overlay.loadContent(content);
			overlay.setPosition(Application.width / 2 - overlay.getWidth() / 2, Application.height / 2 - overlay.getHeight() / 2);
			overlay.setActive(false);
			this.scene.add(overlay);
		}
		
		this.music = content.loadSong("Sounds/Music.ogg");
		this.music.play();
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);

		if (this.gameMode == GameMode.Playing) {
			this.elapsedTime += gameTime.getElapsedTime();
			if (this.elapsedTime >= 1000) {
				this.timeRemaining--;
				this.elapsedTime = 0;
			}
			
			for (int i = 0; i < this.level.getItemsSize(); i++) {
				tempSearchSprite = this.level.getItems().get(i);
				
				if (tempSearchSprite.isActive() && this.player.getBounds().contains(tempSearchSprite.getBounds())) {
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
						this.playerScore += ((Gem)tempSearchSprite).getPoints();
						// Add points to player
						// Play a cool sound effect
						// Add a fade animation
						// Create a real class for items and override setActive (really ? you must do that first ;))
					}
				}
			}
			
			this.player.updatePhysics(this.level.getBlocksSize(), this.level.getBlocks());
			
			if (this.player.getY() > Application.height) {
				if (!this.overlays[1].isActive()) {
					this.overlays[1].setActive(true);	
				}
				this.gameMode = GameMode.Lose;
				this.player.die(this.gameMode.name());
			}
		} 
		else {
			if (this.gameMode == GameMode.Win) {
				this.player.win();
			}
			
			if (Application.keyboard.space()) {
				this.restartGameState();
			}
		}
	}
	
	/**
	 * Restart the game state.
	 */
	private void restartGameState() {
		if (this.gameMode == GameMode.Win) {
			this.levelId++;
			this.levelId = (this.levelId >= LevelCount) ? 0 : this.levelId;
		}
		
		this.level.reload(Application.content, this.scene, levelId);
		this.player.setStartPosition(this.level.getStartPosition());
		this.scene.add(this.player);
		
		this.initialize();
	}
	
	@Override
	public void draw(GameTime gameTime) {
		super.draw(gameTime);
		
		this.spriteBatch.drawString(this.scoreCounter, "SCORE: " + this.playerScore, 10, 20, Color.YELLOW);
		this.spriteBatch.drawString(this.timeCounter, "TIME: " + this.timeRemaining, 10, 40, Color.YELLOW);
	}
}
