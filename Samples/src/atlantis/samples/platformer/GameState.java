package atlantis.samples.platformer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.easyogg.OggClip;

import atlantis.engine.Atlantis;
import atlantis.engine.ITimerListener;
import atlantis.engine.Timer;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.state.State;
import atlantis.framework.GameTime;
import atlantis.framework.audio.SoundEffect;
import atlantis.framework.content.ContentManager;


enum GameMode {
	Playing, Died, Lose, Win
}

public class GameState extends State implements ITimerListener {
	private Player player;
	private Level level;
	private HashMap<String, SoundEffect> soundEffects;
	private OggClip music;
	private Sprite[] overlays;
	private GameMode gameMode;
	private Timer restartTimer;
	private Sprite tempSearchSprite;

	
	public GameState(String name) {
		super(name);
		
		// Overlays
		this.overlays = new Sprite[3];
		this.overlays[0] = new Sprite("overlays/you_died.png");
		this.overlays[1] = new Sprite("overlays/you_lose.png");
		this.overlays[2] = new Sprite("overlays/you_win.png");
		
		this.level = new Level(3);
		
		// Player
		this.player = new Player();
		
		// Sounds
		this.soundEffects = new HashMap<>(7);
		try {
			music = new OggClip(new FileInputStream("Content/Platformer/Sounds/Music.ogg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.gameMode = GameMode.Playing;
		this.restartTimer = new Timer(3500);
		this.restartTimer.addTimerCompletedListener(this);
		
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
		
		this.level.loadLevel(content, this.scene);
		
		this.player.loadContent(content);
		this.player.setStartPosition(this.level.getStartPosition());
		this.scene.add(this.player);
		
		// Overlays
		for (Sprite overlay : this.overlays) {
			overlay.loadContent(content);
			overlay.setPosition(Atlantis.width / 2 - overlay.getWidth() / 2, Atlantis.height / 2 - overlay.getHeight() / 2);
			overlay.setActive(false);
			this.scene.add(overlay);
		}
		
		this.music.play();
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		this.restartTimer.update(gameTime);
		
		if (this.gameMode == GameMode.Playing) {
			for (int i = 0; i < this.level.getItemsSize(); i++) {
				tempSearchSprite = this.level.getItems().get(i);
				
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
			
			this.player.updatePhysics(this.level.getBlocksSize(), this.level.getBlocks());
			
			if (this.player.getY() > Atlantis.height) {
				if (!this.overlays[1].isActive()) {
					this.overlays[1].setActive(true);
					this.soundEffects.get("PlayerFall").play();
				}
				this.gameMode = GameMode.Lose;
			}
		} 
		else if (this.gameMode == GameMode.Win) {
			this.player.win();
			
			if (Atlantis.keyboard.space()) {
				
			}
		}
		else if (this.gameMode == GameMode.Lose || this.gameMode == GameMode.Died) {
			this.restartTimer.start();
		}
	}

	@Override
	public void onCompleted() {
		
	}

	@Override
	public void onRestart() {

	}
}
