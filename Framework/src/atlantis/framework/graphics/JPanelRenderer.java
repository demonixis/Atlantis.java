// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import atlantis.framework.IDrawable;

/**
 * A JPanel renderer for drawing image, sprite, texte.
 * @author Yannick
 */
public class JPanelRenderer extends JPanel {
	private static final long serialVersionUID = 9202978237731998998L;
	protected Color clearColor;
	private ArrayList<IDrawable> drawableCollection;
	
	public JPanelRenderer() {
		this.clearColor = Color.black;
		this.setDoubleBuffered(true);
		this.drawableCollection = new ArrayList<IDrawable>();
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		// Clear the screen
		graphics.setColor(clearColor);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw all drawable objects
		for (IDrawable drawable : this.drawableCollection) {
			drawable.draw(graphics);
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
	 * Add a drawable object to the collection of drawable objects.
	 * @param drawable The drawable object to add.
	 */
	public void addDrawable(IDrawable drawable) {
		this.drawableCollection.add(drawable);
	}
	
	/**
	 * Remove a drawable object from the collection of drawable objects.
	 * @param drawable The drawable object to remove.
	 */
	public void removeDrawable(IDrawable drawable) {
		this.drawableCollection.remove(drawable);
	}
	
	/**
	 * Gets the a drawable object on the collection of drawable objects.
	 * @param position The position of the drawable object in the collection;
	 * @return Return the object if exists, otherwise return null.
	 */
	public IDrawable getDrawable(int position) {
		return this.drawableCollection.get(position);
	}
}
