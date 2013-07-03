// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.state;

import java.util.ArrayList;

import atlantis.framework.DrawableGameComponent;
import atlantis.framework.Game;
import atlantis.framework.GameTime;

/**
 * A State manager that is responsible to manage game states.
 * @author Yannick
 */
public class StateManager extends DrawableGameComponent {
	
	protected ArrayList<State> states;
	
	public StateManager(Game game) {
		super(game);
		this.states = new ArrayList<State>();
	}
	
	@Override
	public void initialize() {
		if (!this.initialized) {
			for (int i = 0, l = this.states.size(); i < l; i++) {
				this.states.get(i).initialize();
			}
			this.initialized = true;
		}
	}
	
	@Override
	public void loadContent() {
		for (int i = 0, l = this.states.size(); i < l; i++) {
			this.states.get(i).loadContent(this.game.contentManager());
		}
	}

	@Override
	public void update(GameTime gameTime) {
		for (int i = 0, l = this.states.size(); i < l; i++) {
			if (this.states.get(i).isEnabled()) {
				this.states.get(i).update(gameTime);
			}
		}
	}

	@Override
	public void draw(GameTime gameTime) {
		for (int i = 0, l = this.states.size(); i < l; i++) {
			if (this.states.get(i).isVisible()) {
				this.states.get(i).draw(gameTime);
			}
		}
	}
	
	/**
	 * Activation of a state by its name.
	 * @param name The name of the state to enable
	 * @param desactiveOtherStates Sets to true to disable other active states.
	 */
	public void setActive(String name, boolean desactiveOtherStates) {
		int i = 0;
        int size = this.states.size();

		while (i < size) {
			if (this.states.get(i).getName() == name) {
                this.states.get(i).setActive(true);
			}
			else if (desactiveOtherStates) {
				this.states.get(i).setActive(false);
			}
			i++;
		}
	}
	
	/**
	 * Activation of a state by its name.
	 * @param name The name of the state to enable
	 */
	public void setActive(String name) {
		setActive(name, true);
	}
	
	/**
	 * Activation of a state by its index.
	 * @param position The index of the state to enable.
	 * @param desactiveOtherStates Sets to true to disable other active states.
	 */
	public void setActive(int position, boolean desactiveOtherStates) {
		if (desactiveOtherStates) {
			disableStates();
		}
		
		State state = this.states.get(position);
		
		if (state != null) {
			state.setActive(true);
		}
	}
	
	/**
	 * Activation of a state by its index.
	 * @param position The index of the state to enable.
	 */
	public void setActive(int position) {
		setActive(position, true);
	}
	
	/**
	 * Disable all active states.
	 */
	public void disableStates() {
		for (State state : this.states) {
			state.setActive(false);
		}
	}
	
	/**
	 * Add a new state on the collection.
	 * @param state The state to add.
	 * @param isActive Define the activity of the new state.
	 * @param desactiveOtherStates Sets to true to disable other states.
	 */
	public void add(State state, boolean isActive, boolean desactiveOtherStates) {
		if (desactiveOtherStates) {
			disableStates();
		}
		
		state.stateManager = this;
		state.setActive(isActive);
		
		if (this.initialized) {
			state.loadContent(this.game.contentManager());
			state.initialize();
		}
		
		this.states.add(state);
	}
	
	/**
	 * Remove a state from state manager.
	 * @param state The state to remove.
	 */
	public void remove(State state) {
		this.states.remove(state);
	}
	
	/**
	 * Remove a state from state manager.
	 * @param name The name of the state to remove.
	 */
	public void remove(String name) {
		State state = this.get(name);
		
		if (state != null) {
			this.remove(state);
		}
	}
	
	/**
	 * Gets a state by its name.
	 * @param name The name of the state to find.
	 * @return Return the state with this name if exists, otherwise return null.
	 */
	public State get(String name) {
		State state = null;
		
		int i = 0;
		int index = -1;
        int size = this.states.size();

		while (i < size && index == -1) {
			if (this.states.get(i).getName() == name) {
                state = this.states.get(i);
                index = i;
			}
			i++;
		}
		
		return state;
	}
	
	/**
	 * Gets a state by its index.
	 * @param position The index of the state to find.
	 * @return Return the state at the specified index if exists, otherwise return false.
	 */
	public State get(int position) {
		return this.states.get(position);
	}
}
