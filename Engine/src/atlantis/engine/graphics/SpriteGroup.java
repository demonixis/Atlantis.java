package atlantis.engine.graphics;

import java.awt.Graphics;
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
	
	public void add(Entity entity) {
		this.entities.add(entity);
	}
	
	public void remove(Entity entity) {
		this.entities.remove(entity);
	}
	
	public Entity get(int position) {
		return this.entities.get(position);
	}
}
