// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

import java.util.ArrayList;

/**
 * A GameComponentCollection is a container of GameComponent (and DrawableGameComponent)
 * that is updated and drawn on each frame
 * @author Yannick
 */
public class GameComponentCollection implements IUpdateable, IDrawable {
	protected ArrayList<GameComponent> components;
	protected ArrayList<DrawableGameComponent> drawableGameComponents;
	protected boolean initialized;
	
	
	public GameComponentCollection() {
		this.components = new ArrayList<GameComponent>();
		this.drawableGameComponents = new ArrayList<DrawableGameComponent>();
		this.initialized = false;
	}
	
	/**
	 * Initialize all components
	 */
	public void initialize() { 
		if (this.components.size() > 0) {
			for (GameComponent component : this.components) {
				component.initialize();
			}
		}
	}
	
	/**
	 * Load assets on all DrawableGameComponent
	 */
	public void loadContent() {
		if (this.drawableGameComponents.size() > 0) {
			for (DrawableGameComponent component : this.drawableGameComponents) {
				component.loadContent();
			}
		}
		this.initialized = true;
	}
	
	/**
	 * Unload assets on all DrawableGameComponent
	 */
	public void unloadContent() {
		if (this.initialized && this.drawableGameComponents.size() > 0) {
			for (DrawableGameComponent component : this.drawableGameComponents) {
				component.unloadContent();
			}
		}
		this.initialized = false;
	}
	
	/**
	 * Update all components if they are enabled
	 * @param gameTime
	 */
	public void update(GameTime gameTime) {
		if (this.components.size() > 0) {
			for (GameComponent component : this.components) {
				if (component.isEnabled()) {
					component.update(gameTime);
				}
			}
		}
	}
	
	/**
	 * Draw all DrawableGameComponent if they are visibles
	 */
	public void draw(GameTime gameTime) {
		if (this.drawableGameComponents.size() > 0) {
			for (DrawableGameComponent component : this.drawableGameComponents) {
				if (component.isVisible()) {
					component.draw(gameTime);
				}
			}
		}
	}
	
	/**
	 * Add a new component on the collection
	 * @param component
	 */
	public void add(GameComponent component) {
		components.add(component);
		
		if (component instanceof DrawableGameComponent) {
			DrawableGameComponent drawableGameComponent = (DrawableGameComponent) component;
			
			if (this.initialized) {
				drawableGameComponent.loadContent();
				drawableGameComponent.initialize();
			}
			
			drawableGameComponents.add(drawableGameComponent);
		}
	}
	
	/**
	 * Remove a component from the collection
	 * @param component
	 */
	public boolean remove(GameComponent component) {
		if (component instanceof GameComponent) {
			return components.remove(component);
		}
		else if (component instanceof DrawableGameComponent) {
			boolean ret1 = this.components.remove(component);
			boolean ret2 = this.drawableGameComponents.remove(component);
			return ret1 && ret2;
		}
		
		return false;
	}
	
	/**
	 * Get a component from the collection with its index
	 * @param index of component in the collection
	 * @return the component
	 */
	public <T> GameComponent get(int index) {
		if (index >= 0) {
			@SuppressWarnings("unchecked")
			T type = (T)new Object();
			
			if (type.getClass().getName().equals(GameComponent.class.getName())) {
				return this.components.get(index);
			}
			else if (type.getClass().getName().equals(DrawableGameComponent.class.getName())) {
				return (GameComponent)this.drawableGameComponents.get(index);
			}
		}
		
		return null;
	}
}
