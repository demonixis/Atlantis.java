// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	private Rectangle tempRectangle;
	
	/**
	 * Create a spriteBatch.
	 * @param graphics A graphics context.
	 */
	public SpriteBatch(GraphicsDevice graphics) {
		this.graphicsDevice = graphics;
		this.beginStarted = false;
		this.readyToBatch = false;
		this.batchOperations = new ArrayList<BatchOperation>();
		this.tempRectangle = Rectangle.Empty();
	}
	
	/**
	 * Draw a texture2D to screen.
	 * @param texture The texture2D to use.
	 * @param x The position on X axis.
	 * @param y The position on Y axis.
	 */
	public void draw(Texture2D texture, float x, float y, int color, float rotation, float layerDepth) {
		this.tempRectangle.x = (int)x;
		this.tempRectangle.y = (int)y;
		this.tempRectangle.width = texture.getWidth();
		this.tempRectangle.height = texture.getHeight();
		this.draw(texture, this.tempRectangle, null, color, rotation, layerDepth);
	}
	
	/**
	 * Draw a texture2D to screen.
	 * @param texture The texture2D to use.
	 * @param position The position of the texture on screen.
	 */
	public void draw(Texture2D texture, Vector2 position, int color, float rotation, float layerDepth) {
		this.draw(texture, position.x, position.y, color, rotation, layerDepth);
	}
	
	/**
	 * Draw a texture2D on screen.
	 * @param texture
	 * @param destRectangle
	 */
	public void draw(Texture2D texture, Rectangle destRectangle) {
		this.draw(texture, destRectangle, null, Color.TRANSLUCENT, 0.0f);
	}
	
	public void draw(Texture2D texture, Vector2 position, Rectangle sourceRectangle, int color, int rotation, float layerDepth) {
		this.tempRectangle.x = (int)position.x;
		this.tempRectangle.y = (int)position.y;
		this.tempRectangle.width = texture.getWidth();
		this.tempRectangle.height = texture.getHeight();
		this.draw(texture, this.tempRectangle, sourceRectangle, color, rotation, layerDepth);
	}
	
	/**
	 * Draw a texture2D to screen.
	 * @param texture The texture2D to use.
	 * @param destRectangle The destination rectangle.
	 * @param sourceRectangle The source rectangle.
	 */
	public void draw(Texture2D texture, Rectangle destRectangle, Rectangle sourceRectangle, int color, float rotation) {
		this.draw(texture, destRectangle, sourceRectangle, color, rotation, 1);
	}
	
	public void draw(Texture2D texture, Rectangle destRectangle, Rectangle sourceRectangle, int color, float rotation, float layerDepth) {
		if (this.beginStarted) {
			batchOperations.add(new BatchOperation(texture, destRectangle, sourceRectangle, color, rotation, layerDepth));
		}
	}

	/**
	 * Draw a string on screen.
	 * @param spriteFont A spriteFont
	 * @param position The position of the text.
	 */
	public void drawString(SpriteFont spriteFont, Vector2 position) {
		
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
		Graphics2D graphics = (Graphics2D)render.getGraphics();
		graphics.clearRect(0, 0, width, height);
		Collections.sort(batchOperations, new ComparatorBatchOperation());
		
		for (int i = 0, l = batchOperations.size(); i < l; i++) {
			operation = batchOperations.get(i);
			graphics.rotate(operation.rotation);
			
			if (operation.sourceRectangle != null) {
				graphics.drawImage(batchOperations.get(i).texture, 
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
				graphics.drawImage(batchOperations.get(i).texture, 
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
}

/**
 * A batch operation
 * @author Yannick
 */
class BatchOperation {
	public Texture2D texture;
	public Rectangle destinationRectangle;
	public Rectangle sourceRectangle;
	public int color;
	public float rotation;
	public float depth;
	
	public BatchOperation(Texture2D texture, Rectangle destRectangle, Rectangle srcRectangle, int color, float rotation, float depth) {
		this.texture = texture;
		this.destinationRectangle = destRectangle;
		this.sourceRectangle = srcRectangle;
		this.color = color;
		this.rotation = rotation;
		this.depth = depth;
	}
}

class ComparatorBatchOperation implements Comparator<BatchOperation> {
	@Override
	public int compare(BatchOperation batch1, BatchOperation batch2) {
		if (batch1.depth < batch2.depth) {
			return -1;
		}
		else if (batch1.depth > batch2.depth) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
