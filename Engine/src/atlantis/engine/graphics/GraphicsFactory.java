package atlantis.engine.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.util.Random;
import atlantis.framework.Rectangle;
import atlantis.framework.graphics.Texture2D;

public class GraphicsFactory {
	/**
	 * Create a procedural texture.
	 * @param width Desired width.
	 * @param height Desired height.
	 * @param color Desired color.
	 * @return Return a new texture.
	 */
	public static Texture2D createTexture(int width, int height, Color color) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		DataBuffer buffer = image.getRaster().getDataBuffer();
		
		for (int i = 0, l = buffer.getSize(); i < l; i += 4) {
			buffer.setElem(i, color.getAlpha());
			buffer.setElem(i + 1, color.getBlue());
			buffer.setElem(i + 2, color.getGreen());
			buffer.setElem(i + 3, color.getRed());
		}
		
		Texture2D texture = new Texture2D(image);
		return texture;
	}
	
	/**
	 * Create a random texture with specified size.
	 * @param width Desired width.
	 * @param height Desired height.
	 * @return Return a new texture.
	 * @throws Exception
	 */
	public static Texture2D createRandomTexture(int width, int height) throws Exception {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		
		Random random = new Random();
		int[] colors = new int[width * height * 4];
		for (int i = 0, l = colors.length; i < l; i += 4) {
			colors[i] = random.nextInt(255);
			colors[i + 1] = random.nextInt(255);
			colors[i + 2] = random.nextInt(255);
			colors[i + 3] = random.nextInt(255);
		}
		
		Texture2D texture = new Texture2D(image);
		texture.setData(colors);

		return texture;
	}
	
	/**
	 * Draw a line.
	 * @param graphics The graphics context
	 * @param x0 Start X position.
	 * @param y0 Start Y position.
	 * @param x1 End X position.
	 * @param y1 End Y position.
	 * @param color Desired color.
	 */
	public static void drawLine(Graphics graphics, int x0, int y0, int x1, int y1, Color color) {
		Color oldColor = graphics.getColor();
		graphics.setColor(color);
		graphics.drawLine(x0, y0, x1, y1);
		graphics.setColor(oldColor);
	}
	
	/**
	 * Draw a rectangle on screen.
	 * @param graphics The graphics context.
	 * @param texture The texture to use.
	 * @param rectangle Value of the rectangle to draw.
	 * @param color Fill color.
	 * @param borderSize Border size.
	 */
	public static void drawRectangle(Graphics graphics, Texture2D texture, Rectangle rectangle, Color color, int borderSize) {
		graphics.drawImage(texture.getTexture(), rectangle.getLeft(), rectangle.getTop(), rectangle.getWidth(), borderSize, null);
		graphics.drawImage(texture.getTexture(), rectangle.getLeft(), rectangle.getBottom(), rectangle.getWidth(), borderSize, null);
		graphics.drawImage(texture.getTexture(), rectangle.getLeft(), rectangle.getTop(), borderSize, rectangle.getHeight(), null);
		graphics.drawImage(texture.getTexture(), rectangle.getRight(), rectangle.getTop(), borderSize, rectangle.getHeight(), null);
	}
	
	/**
	 * Draw a rectangle on screen with 1px border white.
	 * @param graphics The graphics context.
	 * @param rectangle Value of the rectangle to draw.
	 * @param color Fill color.
	 */
	public static void drawRectangle(Graphics graphics, Rectangle rectangle, Color color) {
		Texture2D texture = createTexture(1, 1, Color.white);
		drawRectangle(graphics, texture, rectangle, color, 1);
	}
}
