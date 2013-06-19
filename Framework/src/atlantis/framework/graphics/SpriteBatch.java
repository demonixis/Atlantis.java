package atlantis.framework.graphics;

import java.awt.Graphics;
import atlantis.framework.Rectangle;
import atlantis.framework.Vector2;

/**
 * A sprite batch is responsible to draw image and text on screen.
 * @author Yannick
 */
public class SpriteBatch {
	protected Graphics graphics;
	
	/**
	 * Create a spriteBatch.
	 * @param graphics A graphics context.
	 */
	public SpriteBatch(Graphics graphics) {
		this.graphics = graphics;
	}
	
	/**
	 * Draw a texture2D to screen.
	 * @param texture The texture2D to use.
	 * @param position The position of the texture on screen.
	 */
	public void draw(Texture2D texture, Vector2 position) {
		graphics.drawImage(texture.getTexture(), (int)position.x, (int)position.y, texture.getWidth(), texture.getHeight(), null);
	}
	
	/**
	 * Draw a texture2D to screen.
	 * @param texture The texture2D to use.
	 * @param destRectangle The destination rectangle.
	 * @param sourceRectangle The source rectangle.
	 */
	public void draw(Texture2D texture, Rectangle destRectangle, Rectangle sourceRectangle) {
		 graphics.drawImage(texture.getTexture(), destRectangle.x, destRectangle.y, destRectangle.getRight(), destRectangle.getBottom(), sourceRectangle.x, sourceRectangle.y, sourceRectangle.getRight(), sourceRectangle.getBottom(), null);
	}
	
	/**
	 * Draw a string on screen.
	 * @param spriteFont A spriteFont
	 * @param position The position of the text.
	 */
	public void drawString(SpriteFont spriteFont, Vector2 position) {
		this.graphics.drawString(spriteFont.getText(), (int)position.x, (int)position.y);
	}
}
