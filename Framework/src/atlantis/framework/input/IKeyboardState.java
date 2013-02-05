package atlantis.framework.input;

/**
 * An interface for keyboard input
 * @author yannick
 */
public interface IKeyboardState {
	
	public boolean isKeyDown(int key);
	
	public boolean isKeyUp(int key);
}
