package atlantis.engine.graphics;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import atlantis.framework.GameTime;
import atlantis.framework.content.ContentManager;

public class SpriteGroup extends Entity {
	protected ArrayList<Entity> entities;
	
	public SpriteGroup() {
		this.entities = new ArrayList<Entity>();
	}
	
	public void initialize() {
		for (Entity entity : this.entities) {
			entity.initialize();
		}
	}
	
	public void loadContent(ContentManager content) {
		for (Entity entity : this.entities) {
			entity.loadContent(content);
		}
	}
	
	public void update(GameTime gameTime) {
		if (this.enabled) {
			for (Entity entity : this.entities) {
				entity.update(gameTime);
			}
		}
	}
	
	public void draw(Graphics graphics) {
		if (this.visible) {
			for (Entity entity : this.entities) {
				entity.draw(graphics);
			}
		}
	}
	
	public List<Entity> getEntities() {
		return this.entities;
	}
	
	public int getCount() {
		return this.entities.size();
	}
	
	/**
	 * Add an Entity to the collection
	 * @param entity
	 */
	public void add(Entity entity) {
		this.entities.add(entity);
	}
	
	/**
	 * Remove an Entity from the collection
	 * @param entity
	 */
	public void remove(Entity entity) {
		this.entities.remove(entity);
	}
	
	/**
	 * Get an Entity from the colletion
	 * @param position
	 * @return
	 */
	public Entity get(int position) {
		return this.entities.get(position);
	}
}
