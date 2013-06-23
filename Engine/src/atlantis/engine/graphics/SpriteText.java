// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

import java.awt.Font;
import java.awt.Graphics;

import atlantis.framework.GameTime;
import atlantis.framework.Vector2;
import atlantis.framework.content.ContentManager;

public class SpriteText extends Sprite {
	protected Vector2 position;
	protected String text;
	protected Font font;
	protected Font tempOldFont;
	protected int textSize;
	protected int textFormat;
	protected String fontName;
	
	public SpriteText() {
		this.position = Vector2.Zero();
		this.text = "";
		this.fontName = "Arial";
		this.textFormat = Font.PLAIN;
		this.textSize = 12;
		this.font = new Font(this.fontName, this.textFormat, this.textSize);
		this.tempOldFont = null;
	}
	
	public SpriteText(String text) {
		this();
		this.text = text;
	}
	
	public SpriteText(String text, Vector2 position) {
		this(text);
		this.position = position;
	}
	
	@Override
	public void loadContent(ContentManager content) {
		
	}
	
	@Override
	public void update(GameTime gameTime) {
		
	}
	
	@Override
	public void draw(Graphics graphics) {
		if (this.visible) {
			this.tempOldFont = graphics.getFont();
			graphics.setFont(this.font);
			graphics.drawString(this.text, (int)this.position.x, (int)this.position.y);
			graphics.setFont(this.tempOldFont);
		}
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Gets the text
	 * @return Return the text that is drawn
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * Sets a font
	 * @param font A font to use.
	 */
	public void setFont(Font font) {
		this.font = font;
	}
	
	/**
	 * Change the font used to rendering the text.
	 * @param fontName The font name to use.
	 * @param textFormat The format @see Font class
	 * @param textSize The size of the text.
	 */
	public void setFont(String fontName, int textFormat, int textSize) {
		this.fontName = fontName;
		this.textFormat = textFormat;
		this.textSize = textSize;
		this.createFont();
	}
	
	/**
	 * Create a font with local values.
	 */
	private void createFont() {
		this.font = new Font(this.fontName, this.textFormat, this.textSize);
	}
}
