// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.Color;
import java.awt.Font;
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
	public enum SpriteSortMode {
		BackToFront, FrontToBack, Immediate
	}
	
	private enum BatchItemType {
		Texture, Font
	}
	
	protected GraphicsDevice graphicsDevice;
	protected Graphics2D graphics2D;
	protected boolean beginStarted;
	protected boolean readyToBatch;
	protected SpriteSortMode spriteSortMode;
	protected Font previousFont;
	protected Color previousColor;
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
		this.spriteSortMode = SpriteSortMode.Immediate;
		this.tempRectangle = Rectangle.Empty();
		this.previousColor = Color.WHITE;
		this.previousFont = null;
	}
	
	public void resize(int width, int height) {
	
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

	public void drawString(SpriteFont spriteFont, String text, int x, int y, int color) {
		this.graphics2D = (Graphics2D)this.graphicsDevice.getGraphics();
		
		this.previousColor = this.graphics2D.getColor();
		this.previousFont = this.graphics2D.getFont();
		
		this.graphics2D.setColor(new Color(color));
		this.graphics2D.setFont(spriteFont.font);
		
		// TODO : Make SpriteBatch inherit from BufferedImage or Texture2D
		this.graphics2D.drawString(text, x, y);
		
		this.graphics2D.setColor(this.previousColor);
		this.graphics2D.setFont(this.previousFont);
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
		this.graphics2D = (Graphics2D)render.getGraphics();
		this.graphics2D.clearRect(0, 0, width, height);
		Collections.sort(batchOperations, new ComparatorBatchOperation());
		
		for (int i = 0, l = batchOperations.size(); i < l; i++) {
			operation = batchOperations.get(i);
			this.graphics2D.rotate(operation.rotation);
			
			if (operation.sourceRectangle != null) {
				this.graphics2D.drawImage(batchOperations.get(i).texture, 
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
				this.graphics2D.drawImage(batchOperations.get(i).texture, 
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
