// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.Font;

/**
 * Define a drawable text.
 * @author Yannick
 */
public class SpriteFont {
	protected Font font;
	protected Font tempOldFont;
	protected int textSize;
	protected int textFormat;
	protected String fontName;
	
	/**
	 * Create a spriteFont
	 */
	public SpriteFont() {
		this("Arial", 12, Font.PLAIN);
	}
	
	public SpriteFont(int textSize) {
		this("Arial", textSize, Font.PLAIN);
	}
	
	public SpriteFont(String fontName, int textSize) {
		this(fontName, textSize, Font.PLAIN);
	}
	
	public SpriteFont(String fontName, int textSize, int textFormat) {
		this.fontName = fontName;
		this.textFormat = textFormat;
		this.textSize = textSize;
		this.font = new Font(this.fontName, this.textFormat, this.textSize);
		this.tempOldFont = null;
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
