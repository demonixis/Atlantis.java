package atlantis.framework.graphics;

import atlantis.framework.Rectangle;

/**
 * An interface to draw on the screen
 * @author yannick
 *
 */
public interface ISpriteBatch {
	public void draw(Texture2D texture, int x, int y);
	public void draw(Texture2D texture, Rectangle destinationRectangle);
	public void draw(Texture2D texture, Rectangle sourceRectangle, Rectangle destinationRectangle);
	public void drawString(SpriteFont font, int x, int y);
}
