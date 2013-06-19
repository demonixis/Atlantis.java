package atlantis.framework.input;

import atlantis.framework.Vector2;

/**
 * A mouse state define the state of the position and buttons at a given time.
 * @author Yannick
 *
 */
public class MouseState {
	private Vector2 position;
	private boolean[] buttons;
	private int wheel;
	
	public MouseState(boolean[] buttons, int x, int y, int wheel) {
		this.buttons = buttons;
		this.position = new Vector2(x, y);
		this.wheel = wheel;
	}
	
	/**
	 * Determine if the button is pressed.
	 * @param button The button to test.
	 * @return Return true if the button is pressed.
	 */
	public boolean isButtonDown(int button) {
		return this.buttons[button] == true;
	}
	
	/**
	 * Determine if the button is release.
	 * @param button The button to test.
	 * @return Return true if the button is released.
	 */
	public boolean isButtonUp(int button) {
		return this.buttons[button] == false;
	}
	
	/**
	 * Gets the wheel value of the middle button.
	 * @return Return the value of the middle button.
	 */
	public int getWheel() {
		return this.wheel;
	}
	
	/**
	 * Gets the X coordinate of the mouse on screen.
	 * @return Return the X coordinate of the mouse.
	 */
	public int getX() {
		return (int)this.position.x;
	}
	
	/**
	 * Gets the Y coordinate of the mouse on screen.
	 * @return Return the Y coordinate of the mouse.
	 */
	public int getY() {
		return (int)this.position.y;
	}
	
	public Vector2 getPosition() {
		return this.position;
	}
}
