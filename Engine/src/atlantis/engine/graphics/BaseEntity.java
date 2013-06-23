// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

import atlantis.framework.IDrawable;
import atlantis.framework.IUpdateable;

/**
 * Define a basic drawable entity.
 * @author Yannick
 */
public abstract class BaseEntity implements IUpdateable, IDrawable {
	private static int entityCounter = 0;
	protected boolean initialized;
	protected boolean assetLoaded;
	protected boolean enabled;
	protected boolean visible;
	protected String name;
	
	public BaseEntity() {
		entityCounter++;
		this.name = "Entity_" + entityCounter;
		this.initialized = false;
		this.assetLoaded = false;
		this.enabled = true;
		this.visible = true;
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
	 * Active or desactive an entity.
	 * If isActice is set to true the entity is not udpated and not drawed
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
