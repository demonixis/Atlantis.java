package atlantis.framework.graphics;

import java.awt.Graphics;

/**
 * The graphics device.
 * @author Yann
 */
public final class GraphicsDevice {
	private JPanelRenderer renderer;
	
	public GraphicsDevice(JPanelRenderer renderer) {
		this.renderer = renderer;
	}
	
	public Graphics getGraphics() {
		return this.renderer.getGraphics();
	}
}
