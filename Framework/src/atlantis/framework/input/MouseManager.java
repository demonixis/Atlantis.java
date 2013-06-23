// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import atlantis.framework.Point;
import atlantis.framework.Vector2;

/**
 * A mouse controller
 * @author Yannick
 */
public class MouseManager  implements MouseListener, MouseMotionListener, MouseWheelListener {
	// Not yet used.
	protected Vector2 position;
	protected Vector2 positionOnScreen;
	protected boolean drag;
	
	protected int x;
	protected int y;
	protected int wheel;
	protected boolean [] buttons;
	
	public MouseManager() {
		this.buttons = new boolean[4];
		this.x = 0;
		this.y = 0;
		this.wheel = 0;
		
		for (int i = 0; i < 4; i++) {
			this.buttons[i] = false;
		}
	}
	
	public MouseState getState() {
		return new MouseState(this.buttons, this.x, this.y, this.wheel);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		this.buttons[mouseEvent.getButton()] = true;
		this.updateMousePosition(mouseEvent);
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		this.buttons[mouseEvent.getButton()] = false;
		this.updateMousePosition(mouseEvent);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent mouseEvent) {
		this.wheel = mouseEvent.getWheelRotation();
		
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
	}
	
	private void updateMousePosition(MouseEvent mouseEvent) {
		this.x = mouseEvent.getX();
		this.y = mouseEvent.getY();
	}
	
	@Deprecated
	public Point getPosition() {
		return new Point(this.x, this.y);
	}
	
	@Deprecated
	public boolean click(int buttonId) {
		if (buttonId < this.buttons.length) {
			return this.buttons[buttonId];
		}
		return false;
	}
}
