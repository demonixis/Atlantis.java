// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.Graphics;

import atlantis.framework.platform.JPanelRenderer;

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
	
	public int getWidth() {
		return this.renderer.getWidth();
	}
	
	public int getHeight() {
		return this.renderer.getHeight();
	}
}
