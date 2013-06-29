// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.Graphics;

/**
 * The graphics device is response to create the main render target and must manage all draw call.
 * @author Yannick
 */
public final class GraphicsDevice {
	protected RenderTarget2D mainRenderTarget;
	protected int width;
	protected int height;
	
	public GraphicsDevice(int width, int height) {
		this.mainRenderTarget = new RenderTarget2D(width, height);
		this.width = width;
		this.height = height;
	}
	
	public Graphics getGraphics() {
		return this.mainRenderTarget.getGraphics();
	}
	
	public RenderTarget2D getRenderTarget() {
		return this.mainRenderTarget;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
