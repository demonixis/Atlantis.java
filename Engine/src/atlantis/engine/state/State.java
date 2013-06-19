package atlantis.engine.state;

import java.awt.Graphics;

import atlantis.engine.graphics.BaseEntity;
import atlantis.engine.graphics.SpriteGroup;
import atlantis.framework.GameTime;
import atlantis.framework.content.ContentManager;

/**
 * A class that define a game state.
 * @author Yannick
 */
public class State extends BaseEntity {
	protected SpriteGroup scene;
	protected StateManager stateManager;
	
	public State() {
		super();
		this.scene = new SpriteGroup();
		this.stateManager = null;
	}
	
	public State(String name) {
		this();
		this.name = name;
	}
	
	public void initialize() {
		this.scene.initialize();
		this.initialized = true;
	}
	
	public void loadContent(ContentManager content) {
		this.scene.loadContent(content);
		this.assetLoaded = true;
	}
	
	@Override
	public void update(GameTime gameTime) {
		if (this.enabled) {
			this.scene.update(gameTime);
		}
	}
	
	@Override
	public void draw(Graphics graphics) {
		if (this.visible) {
			this.scene.draw(graphics);
		}
	}

	/**
	 * Gets the scene
	 * @return
	 */
	public SpriteGroup getScene() {
		return this.scene;
	}
}
