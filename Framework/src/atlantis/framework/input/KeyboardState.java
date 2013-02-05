package atlantis.framework.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A keyboard state manager
 * @author Yann
 */
public class KeyboardState implements KeyListener {

	protected boolean [] keys;
	
	public KeyboardState() {
		this.keys = new boolean[KeyEvent.KEY_LAST];
		
		for (int i = 0; i < KeyEvent.KEY_LAST; i++) {
			this.keys[i] = false;
		}
	}
	
	/**
	 * Determine if a key is pressed
	 * @param key
	 * @return true if the key is pressed otherwise return false
	 */
	public boolean isKeyDown(int key) {
		return this.keys[key] == true;
	}
	
	/**
	 * Determine if a key is released
	 * @param key
	 * @return true if the key is released otherewise return false
	 */
	public boolean isKeyUp(int key) {
		return this.keys[key] == true;
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
		// TODO Auto-generated method stub
		
	}

}
