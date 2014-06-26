// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

import atlantis.framework.GameTime;
import atlantis.framework.graphics.SpriteBatch;

/**
 * Define a basic entity.
 * @author Yannick
 */
public abstract class BaseEntity {
	private static int entityCounter = 0;
	protected boolean initialized;
	protected boolean enabled;
	protected boolean visible;
	protected String name;
	
	public BaseEntity() {
		entityCounter++;
		this.name = "Entity_" + entityCounter;
		this.initialized = false;
		this.enabled = true;
		this.visible = true;
	}
	
	public void initialize() {
		this.initialized = true;
	}
	
	public void update(GameTime gameTime) {
		
	}
	
	public void draw(GameTime gameTime, SpriteBatch spriteBatch) {
		
	}
	
	public boolean isInitialized() {
		return initialized;
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
