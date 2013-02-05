package atlantis.engine;

import java.awt.Graphics;

import atlantis.engine.graphics.SpriteGroup;
import atlantis.framework.GameTime;
import atlantis.framework.IDrawable;
import atlantis.framework.IUpdateable;
import atlantis.framework.content.ContentManager;

public class State implements IUpdateable, IDrawable {
	protected String name;
	protected boolean active;
	protected boolean initialized;
	protected SpriteGroup scene;
	protected StateManager stateManager;
	
	public State(String name) {
		this.name = name;
		this.active = true;
		this.initialized = false;
		this.scene = new SpriteGroup();
		this.stateManager = null;
	}
	
	public void initialize() {
		
	}
	
	public void loadContent(ContentManager content) {
		this.scene.loadContent(content);
		this.scene.initialize();
		this.initialized = true;
	}
	
	@Override
	public void draw(Graphics graphics) {
		this.scene.draw(graphics);
	}

	@Override
	public void update(GameTime gameTime) {
		this.scene.update(gameTime);
	}

	public SpriteGroup getScene() {
		return this.scene;
	}
}
