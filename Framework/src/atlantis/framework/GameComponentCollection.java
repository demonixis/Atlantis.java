package atlantis.framework;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * A GameComponentCollection is a container of GameComponent (and DrawableGameComponent)
 * that is updated and drawn on each frame
 * @author Yannick
 */
public class GameComponentCollection implements IUpdateable, IDrawable {
	protected ArrayList<GameComponent> components;
	protected ArrayList<DrawableGameComponent> drawableGameComponents;
	
	public GameComponentCollection() {
		this.components = new ArrayList<GameComponent>();
		this.drawableGameComponents = new ArrayList<DrawableGameComponent>();
	}
	
	/**
	 * Add a new component on the collection
	 * @param component
	 */
	public void add(GameComponent component) {
		components.add(component);
		
		if (component instanceof DrawableGameComponent) {
			drawableGameComponents.add((DrawableGameComponent) component);
		}
	}
	
	/**
	 * Remove a component from the collection
	 * @param component
	 */
	public void remove(GameComponent component) {
		components.remove(component);
	}
	
	/**
	 * Get a component from the collection with its index
	 * @param index of component in the collection
	 * @return the component
	 */
	public GameComponent get(int index) {
		return components.get(index);
	}
	
	/**
	 * Initialize all components
	 */
	public void initialize() { }
	
	/**
	 * Load assets on all DrawableGameComponent
	 */
	public void loadContent() {
		for (int i = 0; i < drawableGameComponents.size(); i++) {
			drawableGameComponents.get(i).loadContent();
		}
	}
	
	/**
	 * Unload assets on all DrawableGameComponent
	 */
	public void unloadContent() {
		for (int i = 0; i < drawableGameComponents.size(); i++) {
			drawableGameComponents.get(i).unloadContent();
		}
	}
	
	/**
	 * Update all components if they are enabled
	 * @param gameTime
	 */
	public void update(GameTime gameTime) {
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).isEnabled()) {
				components.get(i).update(gameTime);
			}
		}
	}
	
	/**
	 * Draw all DrawableGameComponent if they are visible
	 */
	public void draw(Graphics graphics) {
		for (int i = 0; i < drawableGameComponents.size(); i++) {
			if (drawableGameComponents.get(i).isVisible()) {
				drawableGameComponents.get(i).draw(graphics);
			}
		}
	}
}
