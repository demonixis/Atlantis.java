// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.image.BufferedImage;
import atlantis.framework.Rectangle;
import atlantis.framework.Vector2;

/**
 * A sprite batch is responsible to draw image and text on screen.
 * @author Yannick
 */
public class SpriteBatch {
	protected GraphicsDevice graphics;
	
	/**
	 * Create a spriteBatch.
	 * @param graphics A graphics context.
	 */
	public SpriteBatch(GraphicsDevice graphics) {
		this.graphics = graphics;
	}
	

	/**
	 * Draw a texture2D to screen.
	 * @param texture The texture2D to use.
	 * @param x The position on X axis.
	 * @param y The position on Y axis.
	 */
	public void draw(Texture2D texture, float x, float y) {
		this.graphics.getGraphics().drawImage(texture.getTexture(), (int)x, (int)y, texture.getWidth(), texture.getHeight(), null);
	}
	

	@Deprecated
	public void draw(BufferedImage texture, float x, float y) {
		this.graphics.getGraphics().drawImage(texture, (int)x, (int)y, texture.getWidth(), texture.getHeight(), null);
	}
	
	/**
	 * Draw a texture2D to screen.
	 * @param texture The texture2D to use.
	 * @param position The position of the texture on screen.
	 */
	public void draw(Texture2D texture, Vector2 position) {
		this.draw(texture, position.x, position.y);
	}
	
	/**
	 * Draw a texture2D on screen.
	 * @param texture
	 * @param destRectangle
	 */
	public void draw(Texture2D texture, Rectangle destRectangle) {
		this.graphics.getGraphics().drawImage(texture.getTexture(), destRectangle.x, destRectangle.y, destRectangle.getRight(), destRectangle.getBottom(), null);
	}
	
	/**
	 * Draw a texture2D to screen.
	 * @param texture The texture2D to use.
	 * @param destRectangle The destination rectangle.
	 * @param sourceRectangle The source rectangle.
	 */
	public void draw(Texture2D texture, Rectangle destRectangle, Rectangle sourceRectangle) {
		this.graphics.getGraphics().drawImage(texture.getTexture(), destRectangle.x, destRectangle.y, destRectangle.getRight(), destRectangle.getBottom(), sourceRectangle.x, sourceRectangle.y, sourceRectangle.getRight(), sourceRectangle.getBottom(), null);
	}
	
	/**
	 * Draw a string on screen.
	 * @param spriteFont A spriteFont
	 * @param position The position of the text.
	 */
	public void drawString(SpriteFont spriteFont, Vector2 position) {
		this.graphics.getGraphics().drawString(spriteFont.getText(), (int)position.x, (int)position.y);
	}
}
