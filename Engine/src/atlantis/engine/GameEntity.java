package atlantis.engine;

import java.awt.Graphics;
import atlantis.framework.GameTime;

public class GameEntity extends BasicEntity {
	protected boolean assetLoaded;
	protected boolean visible;

	public GameEntity() {
		super();
		this.assetLoaded = false;
		this.enabled = true;
		this.visible = true;
	}

	public void onActivated() {

	}

	public void onDesactivated() {

	}

	public void initialize() {

	}

	public void loadContent() {

	}

	public void unloadContent() {

	}

	@Override
	public void update(GameTime gameTime) {
		// TODO Auto-generated method stub

	}

	public void draw(GameTime gameTime, Graphics graphics) {

	}

	public void setActive(boolean isActive) {
		this.enabled = isActive;
		this.visible = isActive;

		if (isActive) {
			onActivated();
		}
		else {
			onDesactivated();
		}
	}

	public boolean isActive() {
		return this.visible && this.enabled;
	}

	public boolean isAssetLoaded() {
		return assetLoaded;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
