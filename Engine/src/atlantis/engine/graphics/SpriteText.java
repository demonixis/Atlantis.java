package atlantis.engine.graphics;

import java.awt.Font;
import java.awt.Graphics;

import atlantis.framework.Vector2;

public class SpriteText extends Entity {
	protected String text;
	protected Font spriteFont;
	protected int textSize;
	protected int textFormat;
	protected String fontName;
	
	public SpriteText() {
		this.text = "";
		this.fontName = "Arial";
		this.textFormat = Font.PLAIN;
		this.textSize = 12;
		this.spriteFont = new Font(this.fontName, this.textFormat, this.textSize);
	}
	
	public SpriteText(String text) {
		this();
		this.text = text;
	}
	
	public SpriteText(String text, Vector2 position) {
		this(text);
		this.position = position;
	}
	
	public void draw(Graphics graphics) {
		if (this.visible) {
			graphics.drawString(this.text, (int)this.position.getX(), (int)this.position.getY());
		}
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
}
