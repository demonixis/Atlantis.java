// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import atlantis.framework.Vector2;

/**
 * Define a drawable text.
 * @author Yannick
 */
public class SpriteFont {
	protected String text;
	protected Vector2 scale;
	protected Vector2 position;
	
	/**
	 * Create a spriteFont with a text
	 * @param text Desired text.
	 */
	public SpriteFont(String text) {
		this.text = text;
		this.position = Vector2.Zero();
		this.scale = Vector2.One();
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public float getX() {
		return this.position.x;
	}
	
	public void setX(float x) {
		this.position.x = x;
	}
	
	public float getY() {
		return this.position.y;
	}
	
	public void setY(float y) {
		this.position.y = y;
	}
	
	public Vector2 getPosition() {
		return this.position;
	}
	
	public void setPosition(float x, float y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public Vector2 getScale() {
		return this.scale;
	}
	
	public void setScale(Vector2 scale) {
		this.scale = scale;
	}
	
	public void setScale(float scale) {
		this.scale.x = scale;
		this.scale.y = scale;
	}
}
