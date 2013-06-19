package atlantis.framework.input;

/**
 * A keyboard state define the state of the keys at a given time.
 * @author Yannick
 */
public class KeyboardState {
	private boolean[] keys;
	
	public KeyboardState(boolean[] keys) {
		this.keys = keys;
	}
	
	/**
	 * Determine if the key is pressed.
	 * @param button The button to test.
	 * @return Return true if the key is pressed.
	 */
	public boolean isKeyDown(int key) {
		return this.keys[key] == true;
	}
	
	/**
	 * Determine if the key is pressed.
	 * @param button The button to test.
	 * @return Return true if the key is released.
	 */
	public boolean isKeyUp(int key) {
		return this.keys[key] == false;
	}
}
