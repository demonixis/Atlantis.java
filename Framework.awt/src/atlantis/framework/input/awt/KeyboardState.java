package atlantis.framework.input.awt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import atlantis.framework.input.IKeyboardState;

public class KeyboardState implements KeyListener, IKeyboardState {

	protected boolean [] keys;
	
	public KeyboardState() {
		this.keys = new boolean[KeyEvent.KEY_LAST];
		
		for (int i = 0; i < KeyEvent.KEY_LAST; i++) {
			this.keys[i] = false;
		}
	}
	
	public boolean isKeyDown(int key) {
		return this.keys[key] == true;
	}
	
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
