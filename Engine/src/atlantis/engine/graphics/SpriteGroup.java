// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

import java.util.ArrayList;
import java.util.List;

import atlantis.engine.Application;
import atlantis.framework.GameTime;
import atlantis.framework.content.ContentManager;
import atlantis.framework.graphics.SpriteBatch;

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
	 * Compute the bouding rectangle of the group.
	 */
	public void computeBoundingRectangle() {
		int maxWidth = 0;
		int maxHeight = 0;
		
		for (int i = 0, l = this.entities.size(); i < l; i++) {
			maxWidth = Math.max(maxWidth, this.entities.get(i).getWidth());
			maxHeight = Math.max(maxHeight, this.entities.get(i).getHeight());
		}
	}
	
	/**
	 * Initialize entities
	 */
	public void initialize() {
		for (int i = 0, l = this.entities.size(); i < l; i++) {
			this.entities.get(i).initialize();
		}
		
		this.initialized = true;
	}
	
	/**
	 * Load asset for each entity
	 */
	public void loadContent(ContentManager content) {
		for (int i = 0, l = this.entities.size(); i < l; i++) {
			this.entities.get(i).loadContent(content);
		}
		
		this.assetLoaded = true;
	}
	
	/**
	 * Update entities logic
	 */
	public void update(GameTime gameTime) {
		if (this.enabled) {
			for (int i = 0, l = this.entities.size(); i < l; i++) {
				this.entities.get(i).update(gameTime);
			}
		}
	}
	
	/**
	 * Draw entities on screen
	 */
	public void draw(GameTime gameTime, SpriteBatch spriteBatch) {
		if (this.visible) {
			for (int i = 0, l = this.entities.size(); i < l; i++) {
				this.entities.get(i).draw(gameTime, spriteBatch);
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
	public int count() {
		return this.entities.size();
	}
	
	/**
	 * Add an Entity to the collection, if assets hasn't loaded they are loaded and initialized
	 * @param entity
	 */
	public void add(Sprite entity) {
		if (this.initialized) {
			entity.initialize();
		}
		
		if (this.assetLoaded) {
			entity.loadContent(Application.content);
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
	
	/**
	 * Clear the collection
	 */
	public void clear() {
		this.entities.clear();
	}
}
