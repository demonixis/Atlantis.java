// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.input;

import java.awt.event.MouseEvent;

import atlantis.framework.Game;
import atlantis.framework.GameComponent;
import atlantis.framework.GameTime;
import atlantis.framework.Vector2;
import atlantis.framework.input.MouseState;

public class MouseComponent extends GameComponent {
	private MouseState mouseState;
	private MouseState previousMouseState;
	private Vector2 delta;

	public MouseComponent(Game game) {
		super(game);
		this.mouseState = game.getMouseManager().getState();
		this.previousMouseState = this.mouseState;
		this.delta = new Vector2();
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		// Update states
		this.previousMouseState = this.mouseState;
		this.mouseState = this.game.getMouseManager().getState();

		// Calculate the delta
		this.delta.x = this.mouseState.getX() - this.previousMouseState.getX();
		this.delta.y = this.mouseState.getY() - this.previousMouseState.getY();
	}
	
	public int getX() {
		return this.mouseState.getX();
	}

	public int getY() {
		return this.mouseState.getY();
	}

	public int getWheel() {
		return this.mouseState.getWheel();
	}

	public boolean isMoving() {
		return (this.mouseState.getX() != this.previousMouseState.getX()) || (this.mouseState.getY() != this.previousMouseState.getY());
	}

	public Vector2 getDelta() {
		return this.delta;
	}
	
	public MouseState getMouseState() {
		return this.mouseState;
	}

	public MouseState getPreviousMouseState() {
		return this.previousMouseState;
	}

	public boolean clickLeft(boolean isButtonDown) {
		if (!isButtonDown) {
			return this.mouseState.isButtonUp(MouseEvent.BUTTON1);
		}
		return this.mouseState.isButtonDown(MouseEvent.BUTTON1);
	}

	public boolean clickRight(boolean isButtonDown) {
		if (!isButtonDown) {
			return this.mouseState.isButtonUp(MouseEvent.BUTTON3);
		}
		return this.mouseState.isButtonDown(MouseEvent.BUTTON3);
	}

	public boolean clickMiddle(boolean isButtonDown) {
		if (!isButtonDown) {
			return this.mouseState.isButtonUp(MouseEvent.BUTTON2);
		}
		return this.mouseState.isButtonDown(MouseEvent.BUTTON2);
	}

	public boolean justClicked(int button) {
		return this.mouseState.isButtonDown(button) && this.previousMouseState.isButtonUp(button);
	}

	public boolean justReleased(int button)	{
		return this.mouseState.isButtonUp(button) && this.previousMouseState.isButtonDown(button);
	}
	
	public Vector2 getPosition() {
		return new Vector2(this.getX(), this.getY());
	}

	public Vector2 getPreviousPosition() {
		return new Vector2(this.getPreviousMouseState().getX(), this.getPreviousMouseState().getY());
	}
	
	public boolean click(int button) {
		return clickOn(button, true);
	}

	public boolean released(int button)	{
		return clickOn(button, false);
	}
	
	public boolean drag(int button) {
		return click(button) && isMoving();
	}

	public boolean drop (int button) {
		return released(button) && !isMoving();
	}

	public boolean clickOn(int button, boolean isPressed) {
		boolean result = false;

		switch (button)
		{
			case MouseEvent.BUTTON1: result = this.clickLeft(isPressed); break;
			case MouseEvent.BUTTON2: result = this.clickMiddle(isPressed); break;
			case MouseEvent.BUTTON3: result = this.clickRight(isPressed); break;
		}

		return result; 
	}
}
