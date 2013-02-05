package atlantis.engine;

import java.awt.Graphics;
import java.util.ArrayList;

import atlantis.framework.DrawableGameComponent;
import atlantis.framework.BaseGame;
import atlantis.framework.GameTime;

public class StateManager extends DrawableGameComponent {

	protected ArrayList<State> states;
	
	public StateManager(BaseGame game) {
		super(game);
		this.states = new ArrayList<State>();
	}
	
	public void loadContent() {
		for (State state : this.states) {
			state.loadContent(this.game.getContentManager());
			state.initialize();
			this.initialized = true;
		}
	}

	@Override
	public void update(GameTime gameTime) {
		for (State state : this.states) {
			if (state.active) {
				state.update(gameTime);
			}
		}
	}

	@Override
	public void draw(Graphics graphics) {
		for (State state : this.states) {
			if (state.active) {
				state.draw(graphics);
			}
		}
	}
	
	public void setStateActive(String name, boolean desactiveOtherStates) {
		if (desactiveOtherStates) {
			this.disableStates();
		}
		
		int i = 0;
		int index = -1;
        int size = this.states.size();

		while (i < size && index == -1) {
			if (this.states.get(i).name == name) {
                this.states.get(i).active = true;
                index = i;
			}
			i++;
		}
	}
	
	public void setStateActive(int position, boolean desactiveOtherStates) {
		if (desactiveOtherStates) {
			disableStates();
		}
		
		State state = this.states.get(position);
		
		if (state != null) {
			state.active = true;
		}
	}
	
	public void disableStates() {
		for (State state : this.states) {
			state.active = false;
		}
	}
	
	public void switchState(State newState) {
		this.states.clear();
		this.states.add(newState);
	}
	
	public void add(State state, boolean isActive, boolean desactiveOtherStates) {
		if (desactiveOtherStates) {
			disableStates();
		}
		
		state.stateManager = this;
		state.active = isActive;
		
		if (this.initialized) {
			state.loadContent(this.game.getContentManager());
			state.initialize();
		}
		
		this.states.add(state);
	}
	
	public void remove(State state) {
		this.states.remove(state);
	}
	
	public void remove(String name) {
		State state = this.get(name);
		
		if (state != null) {
			this.remove(state);
		}
	}
	
	public State get(String name) {
		State state = null;
		
		int i = 0;
		int index = -1;
        int size = this.states.size();

		while (i < size && index == -1) {
			if (this.states.get(i).name == name) {
                state = this.states.get(i);
                index = i;
			}
			i++;
		}
		
		return state;
	}
	
	public State get(int position) {
		return this.states.get(position);
	}
}
