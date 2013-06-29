// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import atlantis.framework.Rectangle;
import atlantis.framework.Vector2;

/**
 * A sprite batch is responsible to draw image and text on screen.
 * @author Yannick
 */
public class SpriteBatch {
	protected GraphicsDevice graphicsDevice;
	protected boolean beginStarted;
	protected boolean readyToBatch;
	protected ArrayList<BatchOperation> batchOperations;
	
	/**
	 * Create a spriteBatch.
	 * @param graphics A graphics context.
	 */
	public SpriteBatch(GraphicsDevice graphics) {
		this.graphicsDevice = graphics;
		this.beginStarted = false;
		this.readyToBatch = false;
		this.batchOperations = new ArrayList<BatchOperation>();
	}

	public void begin() {
		this.beginStarted = true;
	}
	
	public void end() {
		if (this.beginStarted) {
			this.readyToBatch = true;
			this.beginStarted = false;
			processBatch();
		}
	}
	
	protected void processBatch() {
		int width = this.graphicsDevice.getWidth();
		int height = this.graphicsDevice.getHeight();
		BatchOperation operation = null;
		
		BufferedImage render = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = render.getGraphics();
		graphics.clearRect(0, 0, width, height);
		
		for (int i = 0, l = batchOperations.size(); i < l; i++) {
			operation = batchOperations.get(i);
			if (operation.sourceRectangle != null) {
				graphics.drawImage(batchOperations.get(i).texture.getTexture(), 
						operation.destinationRectangle.x, 
						operation.destinationRectangle.y, 
						operation.destinationRectangle.getRight(), 
						operation.destinationRectangle.getBottom(), 
						operation.sourceRectangle.x, 
						operation.sourceRectangle.y, 
						operation.sourceRectangle.getRight(), 
						operation.sourceRectangle.getBottom(),
						null);
			}
			else {
				graphics.drawImage(batchOperations.get(i).texture.getTexture(), 
						operation.destinationRectangle.x, 
						operation.destinationRectangle.y, 
						operation.destinationRectangle.getWidth(), 
						operation.destinationRectangle.getHeight(),
						null);
			}
		}
		
		this.graphicsDevice.getGraphics().drawImage(render, 0, 0, width, height, null);
		batchOperations.clear();
	}
	
	/**
	 * Draw a texture2D to screen.
	 * @param texture The texture2D to use.
	 * @param destRectangle The destination rectangle.
	 * @param sourceRectangle The source rectangle.
	 */
	public void draw(Texture2D texture, Rectangle destRectangle, Rectangle sourceRectangle, float rotation) {
		if (this.beginStarted) {
			batchOperations.add(new BatchOperation(texture, destRectangle, sourceRectangle, rotation));
		}
	}

	/**
	 * Draw a texture2D to screen.
	 * @param texture The texture2D to use.
	 * @param x The position on X axis.
	 * @param y The position on Y axis.
	 */
	public void draw(Texture2D texture, float x, float y) {
		this.draw(texture, new Rectangle((int)x, (int)y, texture.getWidth(), texture.getHeight()), null, 0.0f);
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
		this.draw(texture, destRectangle, null, 0.0f);
	}

	/**
	 * Draw a string on screen.
	 * @param spriteFont A spriteFont
	 * @param position The position of the text.
	 */
	public void drawString(SpriteFont spriteFont, Vector2 position) {
		
	}
}

/**
 * A batch operation
 * @author Yannick
 */
class BatchOperation {
	public Texture2D texture;
	public Rectangle destinationRectangle;
	public Rectangle sourceRectangle;
	public float rotation;
	
	public BatchOperation(Texture2D texture, Rectangle destRectangle, Rectangle srcRectangle, float rotation) {
		this.texture = texture;
		this.destinationRectangle = destRectangle;
		this.sourceRectangle = srcRectangle;
		this.rotation = rotation;
	}
}
