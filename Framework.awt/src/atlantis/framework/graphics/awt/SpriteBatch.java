package atlantis.framework.graphics.awt;

import java.awt.Graphics;

import atlantis.framework.Rectangle;
import atlantis.framework.graphics.ISpriteBatch;
import atlantis.framework.graphics.SpriteFont;
import atlantis.framework.graphics.Texture2D;

public class SpriteBatch implements ISpriteBatch {
	private Graphics graphics;
	
	public SpriteBatch(GraphicsDevice device) {
		
	}
	
	@Override
	public void draw(Texture2D texture, int x, int y) {
		graphics.drawImage(texture.getTexture(), x, y, texture.getWidth(), texture.getHeight(), null);
	}

	@Override
	public void draw(Texture2D texture, Rectangle destinationRectangle) {
		graphics.drawImage(texture.getTexture(), destinationRectangle.getX(), destinationRectangle.getY(), destinationRectangle.getWidth(), destinationRectangle.getHeight(), null);
	}

	@Override
	public void draw(Texture2D texture, Rectangle sourceRectangle, Rectangle destinationRectangle) {
		graphics.drawImage(texture.getTexture(), sourceRectangle.getX(), sourceRectangle.getY(), sourceRectangle.getRight(), sourceRectangle.getBottom(), destinationRectangle.getX(), destinationRectangle.getY(), destinationRectangle.getRight(), destinationRectangle.getBottom(), null);
		
	}

	@Override
	public void drawString(SpriteFont spriteFont, int x, int y) {
		graphics.drawString(spriteFont.getText(), x, y);
	}

}
