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
	protected Font spriteFont;
	protected int textSize;
	protected int textFormat;
	protected String fontName;
	
	public SpriteText() {
		this.position = Vector2.Zero();
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
	
	@Override
	public void loadContent(ContentManager content) {
		
	}
	
	@Override
	public void update(GameTime gameTime) {
		
	}
	
	@Override
	public void draw(Graphics graphics) {
		if (this.visible) {
			graphics.drawString(this.text, (int)this.position.x, (int)this.position.y);
		}
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
}
