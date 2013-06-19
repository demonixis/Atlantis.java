package atlantis.framework.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A keyboard manager
 * @author Yann
 */
public class KeyboardManager implements KeyListener {

	protected boolean [] keys;
	
	public KeyboardManager() {
		this.keys = new boolean[KeyEvent.KEY_LAST];
		
		for (int i = 0; i < KeyEvent.KEY_LAST; i++) {
			this.keys[i] = false;
		}
	}
	
	/**
	 * Gets the state of the keys.
	 * @return Return the states of the keys.
	 */
	public KeyboardState getState() {
		return new KeyboardState(this.keys);
	}
	
	@Deprecated
	public boolean isKeyDown(int key) {
		return this.keys[key] == true;
	}
	
	@Deprecated
	public boolean isKeyUp(int key) {
		return this.keys[key] == false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		this.keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		this.keys[e.getKeyCode()] = true;
	}
	
	public boolean[] getStates() {
		return this.keys;
	}
	
	@Deprecated
	public KeyboardManager clone() {
		KeyboardManager keyboardState = new KeyboardManager();
	
		for (int i = 0, l = this.keys.length; i < l; i++) {
			keyboardState.keys[i] = this.keys[i];
		}
		
		return keyboardState;
	}

}
