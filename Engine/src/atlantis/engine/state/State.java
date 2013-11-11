// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.state;

import atlantis.engine.Application;
import atlantis.engine.graphics.SpriteGroup;
import atlantis.framework.GameTime;
import atlantis.framework.IDrawable;
import atlantis.framework.IUpdateable;
import atlantis.framework.content.ContentManager;
import atlantis.framework.graphics.SpriteBatch;

/**
 * A class that define a game state.
 * @author Yannick
 */
public class State implements IUpdateable, IDrawable {
	protected SpriteGroup scene;
	protected StateManager stateManager;
	protected SpriteBatch spriteBatch;
	protected boolean initialized;
	protected boolean assetLoaded;
	protected boolean enabled;
	protected boolean visible;
	protected String name;
	
	private State() {
		super();
		this.scene = new SpriteGroup();
		this.stateManager = null;
		this.initialized = false;
		this.assetLoaded = false;
		this.enabled = true;
		this.visible = true;
		this.spriteBatch = new SpriteBatch(Application.game.graphicsDevice());
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
	public void draw(GameTime gameTime) {
		if (this.visible) {
			this.spriteBatch.begin();
			this.scene.draw(gameTime, this.spriteBatch);
			this.spriteBatch.end();
		}
	}

	/**
	 * Gets the scene
	 * @return
	 */
	public SpriteGroup getScene() {
		return this.scene;
	}
	
	public boolean isInitialized() {
		return initialized;
	}

	public boolean isAssetLoaded() {
		return assetLoaded;
	}

	public boolean isActive() {
		return this.enabled && this.visible;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isVisible() {
		return visible;
	}

	public String getName() {
		return this.name;
	}
	
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public void setAssetLoaded(boolean assetLoaded) {
		this.assetLoaded = assetLoaded;
	}

	/**
	 * Enable or disable an entity.
	 * If isActice is set to true the entity is not updated and not drawn
	 * @param isActive
	 */
	public void setActive(boolean active) {
		this.enabled = active;
		this.visible = active;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
