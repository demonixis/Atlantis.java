package atlantis.framework.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import atlantis.framework.Point;

/**
 * A mouse state controller
 * @author Yann
 */
public class MouseState  implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	protected int x;
	protected int y;
	protected int wheel;
	protected boolean [] buttons;
	protected boolean drag;
	protected boolean justClicked;
	
	public MouseState() {
		this.buttons = new boolean[4];
		this.x = 0;
		this.y = 0;
		this.wheel = 0;
		this.drag = false;
		this.justClicked = false;
		
		for (int i = 0; i < 4; i++) {
			this.buttons[i] = false;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		this.justClicked = true;
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		this.justClicked = false;
		
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		this.justClicked = false;
		
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		this.justClicked = false;
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
		this.justClicked = false;
		
	}
	
	private void updateMousePosition(MouseEvent mouseEvent) {
		this.x = mouseEvent.getX();
		this.y = mouseEvent.getY();
	}
	
	/**
	 * Get the current position of the mouse
	 * @return position of the mouse
	 */
	public Point getPosition() {
		return new Point(this.x, this.y);
	}
	
	/**
	 * Gets the state of a mouse button
	 * @param buttonId
	 * @return true if clicked otherwise return false
	 */
	public boolean click(int buttonId) {
		if (buttonId < this.buttons.length) {
			return this.buttons[buttonId];
		}
		return false;
	}
}
