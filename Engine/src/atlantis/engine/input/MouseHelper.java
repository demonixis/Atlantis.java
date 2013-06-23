// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.input;

import java.awt.event.MouseEvent;

import atlantis.framework.Vector2;
import atlantis.framework.input.MouseState;

public class MouseHelper {
	private MouseComponent mouseComponent;

	public MouseHelper(MouseComponent component) {
		this.mouseComponent = component;
	}

	public int getX() {
		return this.mouseComponent.getX();
	}

	public int getY() {
		return this.mouseComponent.getY();
	}

	public Vector2 getPosition() {
		return new Vector2(this.mouseComponent.getX(), this.mouseComponent.getY());
	}

	public Vector2 getPreviousPosition() {
		return new Vector2(this.mouseComponent.getPreviousMouseState().getX(), this.mouseComponent.getPreviousMouseState().getY());
	}

	public Vector2 getDelta() {
		return this.mouseComponent.getDelta();
	}

	public int getWheel() {
		return this.mouseComponent.getWheel();
	}

	public boolean isMoving() {
		return this.mouseComponent.isMoving();
	}

	public boolean Drag(int button) {
		return Click(button) && isMoving();
	}

	public boolean Drop (int button) {
		return Released(button) && !isMoving();
	}

	public boolean ClickOn(int button, boolean isPressed) {
		boolean result = false;

		switch (button)
		{
			case MouseEvent.BUTTON1: result = this.mouseComponent.clickLeft(isPressed); break;
			case MouseEvent.BUTTON2: result = this.mouseComponent.clickMiddle(isPressed); break;
			case MouseEvent.BUTTON3: result = this.mouseComponent.clickRight(isPressed); break;
		}

		return result; 
	}

	public boolean Click(int button) {
		return ClickOn(button, true);
	}

	public boolean Released(int button)	{
		return ClickOn(button, false);
	}

	public boolean JustClicked(int button) {
		return this.mouseComponent.justClicked(button);
	}

	public boolean JustReleased(int button)	{
		return this.mouseComponent.justReleased(button);
	}

	public MouseState MouseState() {
		return this.mouseComponent.getMouseState();
	}

	public MouseState LastMouseState() {
		return this.mouseComponent.getPreviousMouseState();
	}
}
