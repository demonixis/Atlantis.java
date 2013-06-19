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
}
