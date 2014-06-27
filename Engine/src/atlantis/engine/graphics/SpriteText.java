// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

import atlantis.framework.GameTime;
import atlantis.framework.Vector2;
import atlantis.framework.graphics.SpriteBatch;
import atlantis.framework.graphics.SpriteFont;

public class SpriteText extends Sprite {
	protected String text;
	protected SpriteFont spriteFont;
	
	public SpriteText() {
		super();
		this.position = Vector2.Zero();
		this.text = "";
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
	public void draw(GameTime gameTime, SpriteBatch spriteBatch) {
		spriteBatch.drawString(this.spriteFont, this.text, (int)this.position.x, (int)this.position.y, this.color);
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
}
