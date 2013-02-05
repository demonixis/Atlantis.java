package atlantis.framework.input;

import atlantis.framework.Point;

/**
 * An interface for mouse input
 * @author yannick
 *
 */
public interface IMouseState {
	
	public Point getPosition();
	
	public boolean click(int buttonId);
}
