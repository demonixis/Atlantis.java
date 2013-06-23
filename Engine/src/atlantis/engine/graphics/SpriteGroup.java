// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import atlantis.engine.Atlantis;
import atlantis.framework.GameTime;
import atlantis.framework.content.ContentManager;

/**
 * An Entity collection which will update and draw its children.
 * @author Yannick
 */
public class SpriteGroup extends Sprite {
	protected ArrayList<Sprite> entities;
	
	public SpriteGroup() {
		this.entities = new ArrayList<Sprite>();
	}
	
	/**
	 * Initialize entities
	 */
	public void initialize() {
		for (Sprite entity : this.entities) {
			entity.initialize();
		}
		
		this.initialized = true;
	}
	
	/**
	 * Load asset for each entity
	 */
	public void loadContent(ContentManager content) {
		for (Sprite entity : this.entities) {
			entity.loadContent(content);
		}
		
		this.assetLoaded = true;
	}
	
	/**
	 * Update entities logic
	 */
	public void update(GameTime gameTime) {
		if (this.enabled) {
			for (Sprite entity : this.entities) {
				entity.update(gameTime);
			}
		}
	}
	
	/**
	 * Draw entities on screen
	 */
	public void draw(Graphics graphics) {
		if (this.visible) {
			for (Sprite entity : this.entities) {
				entity.draw(graphics);
			}
		}
	}
	
	/**
	 * Gets entities
	 * @return
	 */
	public List<Sprite> getEntities() {
		return this.entities;
	}
	
	/**
	 * Count the number of entity contains in the collection
	 * @return
	 */
	public int getCount() {
		return this.entities.size();
	}
	
	/**
	 * Add an Entity to the collection, if assets hasn't loaded they are loaded and initialized
	 * @param entity
	 */
	public void add(Sprite entity) {
		if (this.assetLoaded && !entity.assetLoaded) {
			entity.loadContent(Atlantis.content);
		}
		
		if (this.initialized) {
			entity.initialize();
		}
		
		this.entities.add(entity);
	}
	
	/**
	 * Remove an Entity from the collection
	 * @param entity
	 */
	public boolean remove(Sprite entity) {
		return this.entities.remove(entity);
	}
	
	/**
	 * Get an Entity from the collection
	 * @param position
	 * @return
	 */
	public Sprite get(int position) {
		return this.entities.get(position);
	}
}
