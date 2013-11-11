// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.platform;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import atlantis.framework.graphics.RenderTarget2D;

/**
 * A JPanel renderer for drawing image, sprite, text.
 * @author Yannick
 */
public class JPanelRenderer extends JPanel implements IWindowRenderer {
	private static final long serialVersionUID = 9202978237731998998L;
	protected Color clearColor;
	private ArrayList<RenderTarget2D> renderTargets;
	private int renderTargetCount;
	
	public JPanelRenderer() {
		this.clearColor = Color.black;
		this.setDoubleBuffered(true);
		this.renderTargets = new ArrayList<RenderTarget2D>();
		this.renderTargetCount = 0;
	}
	
	public JPanelRenderer(int width, int height) {
		this();
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		// Clear the screen
		graphics.setColor(clearColor);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw all render target objects
		for (int i = 0; i < this.renderTargetCount; i++) {
			this.renderTargets.get(i).draw(graphics);
		}
	}
	
	/**
	 * Sets the color to use to clear the screen between each draw
	 * @param color The color to use to clear the screen.
	 */
	public void setClearColor(Color color) {
		this.clearColor = color;
	}
	
	/**
	 * Add a render target object to the collection of renderTarget objects.
	 * @param drawable The render target object to add.
	 */
	public void addRenderTarget(RenderTarget2D renderTarget) {
		this.renderTargets.add(renderTarget);
		this.renderTargetCount++;
	}
}
