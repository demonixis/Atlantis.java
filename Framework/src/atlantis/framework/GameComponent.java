package atlantis.framework;

/**
 * A GameComponent is a component that is updated on each frame.
 * It can be enabled or disabled
 * @author Yannick
 */
public class GameComponent implements IUpdateable {
	protected Game game;
	protected boolean enabled;
	protected boolean initialized;

	public GameComponent(Game game) {
		this.game = game;
		this.enabled = true;
		this.initialized = false;
	}
	
	public void initialize() {
		this.initialized = true;
	}

	@Override
	public void update(GameTime gameTime) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
