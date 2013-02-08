package atlantis.input;

import atlantis.framework.BaseGame;
import atlantis.framework.GameComponent;
import atlantis.framework.GameTime;
import atlantis.framework.input.KeyboardState;

public class KeyboardComponent extends GameComponent {
	protected boolean [] keysState;
	protected boolean [] lastKeysState;
	
	public KeyboardComponent(BaseGame game) {
		super(game);
		
		this.keysState = game.getKeyboardState().getStates();
		this.lastKeysState = this.keysState;
	}
	
	@Override
	public void update(GameTime gameTime) {
		for (int i = 0, l = this.keysState.length; i < l; i++) {
			this.lastKeysState[i] = this.keysState[i];
		}
		
		this.keysState = this.game.getKeyboardState().getStates();
	}
	
	/**
	 * Determines whether the specified key is pressed.
	 * @param key
	 * @return true if pressed otherwise return false
	 */
	public boolean pressed(int key) {
		return this.keysState[key] == true;
	}
	
	/**
	 * Determines whether the specified key is released.
	 * @param key
	 * @return true if released otherwise return false
	 */
	public boolean released(int key) {
		return this.keysState[key] == false;
	}
	
	/**
	 * Determines whether the specified key is just pressed.
	 * A just pressed key is a key that has been pressed and released
	 * @param key
	 * @return true if just pressed otherwise return false
	 */
	public boolean justPressed(int key) {
		return (this.keysState[key] == false) && (this.lastKeysState[key] == true);
	}
	
	/**
	 * Determines whether the specified key is just released.
	 * @param key
	 * @return true if just released otherwise return false
	 */
	public boolean justReleased(int key) {
		return (this.keysState[key] == true) && (this.lastKeysState[key] == false);
	}
}
