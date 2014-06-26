package atlantis.engine.level;

import java.util.ArrayList;

import atlantis.framework.DrawableGameComponent;
import atlantis.framework.Game;
import atlantis.framework.GameComponent;
import atlantis.framework.GameTime;
import atlantis.framework.graphics.GraphicsDevice;

public class LevelManager extends DrawableGameComponent {
	protected GraphicsDevice graphicsDevice;
	protected ArrayList<Level> levels;
	protected Level activeLevel;
	protected boolean hasActiveLevel;
	protected boolean autoClear;
	protected String autoLoadLevel;
	
	public LevelManager(Game game) {
		super(game);
		this.graphicsDevice = game.graphicsDevice();
		this.levels = new ArrayList<>();
		this.autoClear = true;
		this.hasActiveLevel = false;
		this.activeLevel = null;
		
	}
	
	public void initialize() {
		
	}
	
	public void update(GameTime gameTime) {
		
	}
	
	public void draw(GameTime gameTime) {
		
	}
	
	public void loadLevel(String name) {
		
	}
	
	public void loadLevel(int id) {
		
	}
	
	public void add(Level level) {
		
	}
	
	public void remove(Level level) {
		
	}
	
	public void remove(int index) {
		
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
