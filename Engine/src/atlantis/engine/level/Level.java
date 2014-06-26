package atlantis.engine.level;

import atlantis.framework.GameTime;

public abstract class Level {
	protected String name;
	
	public Level(String name) {
		this.name = name;
	}
	
	protected abstract void intialize();
	
	protected abstract void update(GameTime gameTime);
	
	protected abstract void draw(GameTime gameTime);
}
