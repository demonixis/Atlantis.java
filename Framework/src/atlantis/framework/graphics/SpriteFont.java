package atlantis.framework.graphics;

import atlantis.framework.Vector2;

/**
 * Define a drawable text.
 * @author Yannick
 */
public class SpriteFont {
	protected String text;
	protected Vector2 scale;
	
	/**
	 * Create a spriteFont with a text
	 * @param text Desired text.
	 */
	public SpriteFont(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
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
