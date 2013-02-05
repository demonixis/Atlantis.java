package atlantis.framework.graphics;

import atlantis.framework.Rectangle;

public abstract class SpriteBatch {
	public abstract void draw(Texture2D texture, int x, int y);
	public abstract void draw(Texture2D texture, Rectangle destinationRectangle);
	public abstract void draw(Texture2D texture, Rectangle sourceRectangle, Rectangle destinationRectangle);
	public abstract void drawString(SpriteFont font, int x, int y);
}
