package atlantis.engine.input;

import atlantis.framework.BaseGame;
import atlantis.framework.input.KeyboardState;

public class KeyboardComponent {
	protected KeyboardState keysState;
	protected KeyboardState lastKeysState;
	
	public KeyboardComponent(BaseGame game) {
		this.keysState = game.getKeyboardState().getState();
		this.lastKeysState = game.getKeyboardState().getState();
	}
}
