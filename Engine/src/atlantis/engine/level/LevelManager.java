package atlantis.engine.level;

import java.util.ArrayList;

import atlantis.framework.DrawableGameComponent;
import atlantis.framework.Game;
import atlantis.framework.GameTime;
import atlantis.framework.graphics.GraphicsDevice;

public class LevelManager extends DrawableGameComponent {
	protected GraphicsDevice graphicsDevice;
	protected ArrayList<Level> levels;
	protected Level activeLevel;
	protected boolean hasActiveLevel;
	protected boolean autoClear;
	protected String autoLoadLevel;
	protected int autoLoadLevelIndex;
	
	public LevelManager(Game game) {
		super(game);
		this.graphicsDevice = game.graphicsDevice();
		this.levels = new ArrayList<>();
		this.autoClear = true;
		this.hasActiveLevel = false;
		this.activeLevel = null;
		this.autoLoadLevel = "";
		this.autoLoadLevelIndex = -1;
	}
	
	public void initialize() {
		if (this.autoLoadLevel != "") {
			this.loadLevel(this.autoLoadLevel);
			this.autoLoadLevel = "";
		}
		else if (this.autoLoadLevelIndex > -1) {
			this.loadLevel(this.autoLoadLevelIndex);
			this.autoLoadLevelIndex = -1;
		}
		
		this.initialized = true;
	}
	
	public void update(GameTime gameTime) {
		if (this.activeLevel != null) {
			this.activeLevel.update(gameTime);
		}
	}
	
	public void draw(GameTime gameTime) {
		if (this.activeLevel != null) {
			if (this.autoClear) {
				//this.graphicsDevice.clear();
			}
			this.activeLevel.draw(gameTime);
		}
	}
	
	public void loadLevel(String name) {
		// Start a new level only when the manager is fully initialized
		if (!this.initialized) {
			this.autoLoadLevel = name;
			return;
		}
		
		this.activeLevel = this.getLevelByName(name);
		
		if (this.activeLevel != null) {
			this.activeLevel.intialize();
		}
	}
	
	public void loadLevel(int id) {
		// Same here
		if (!this.initialized) {
			this.autoLoadLevelIndex = id;
			return;
		}
	}
	
	public void add(Level level) {
		if (level == this.activeLevel) {
			this.activeLevel = null;
		}
		
		this.levels.add(level);
	}
	
	public void remove(Level level) {
		if (level == this.activeLevel) {
			this.activeLevel = null;
		}
		
		this.levels.remove(level);
	}
	
	public void remove(int index) {
		this.levels.remove(index);
	}
	
	public Level get(int index) {
		if (this.checkIndex(index)) {
	        return this.levels.get(index);
	    }
		
		return null;
	}
	
	public Level get(String name) {
		return this.getLevelByName(name);
	}
	
	private Level getLevelByName(String name) {
		int i = 0;
	    int size = this.levels.size();
	    Level level = null;

	    while (i < size && level == null) {
	        level = (this.levels.get(i).name == name) ? this.levels.get(i) : level;
	        i++;
	    }

	    return level;
	}
	
	private boolean checkIndex(int index) {
		return index > -1 && index < this.levels.size();
	}
}
