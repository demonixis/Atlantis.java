package atlantis.engine.graphics.tilemap;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import atlantis.engine.Atlantis;
import atlantis.engine.graphics.BaseEntity;
import atlantis.framework.GameTime;
import atlantis.framework.IDrawable;
import atlantis.framework.Rectangle;
import atlantis.framework.graphics.Texture2D;

/**
 * The Layer class define a layer used by a Tilemap
 * @author yannick
 */
public class Layer extends BaseEntity implements IDrawable {
	protected Tile [][] tiles;
	protected Texture2D tileset;
	protected int width;
	protected int height;
	protected int tileWidth;
	protected int tileHeight;
	protected int numOnX;
	protected Rectangle rectangle;
	
	public Layer(Texture2D tileset, int [][] rawTiles) {
		this(tileset, rawTiles, 0, 0, 32, 32);
	}
	
	public Layer(Texture2D tileset, int [][] rawTiles, int tileWidth, int tileHeight) {
		this(tileset, rawTiles, 0, 0, tileWidth, tileHeight);
	}
	
	public Layer(Texture2D tileset, int [][] rawTiles, int offsetX, int offsetY, int tileWidth, int tileHeight) {
		this.tileset = tileset;
		this.width = rawTiles[0].length;
		this.height = rawTiles.length;
		this.tiles = new Tile[this.height][this.width];
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.numOnX = tileset.getWidth() / tileWidth;
		this.rectangle = new Rectangle(offsetX, offsetY, this.width * this.tileWidth, this.height * this.tileHeight);
		this.initialize(rawTiles);
	}
	
	/**
	 * Initialize the tilemap
	 */
	public void initialize(int [][] rawTiles) {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				this.tiles[y][x] = new Tile(x * this.tileWidth, y * this.tileHeight, rawTiles[y][x]);
			}
		}
	}
	
	/**
	 * Initialize the tilemap
	 */
	public void initialize(int tileWidth, int tileHeight) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.rectangle = new Rectangle(this.rectangle.getX(), this.rectangle.getY(), this.width * this.tileWidth, this.height * this.tileHeight);
		
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				this.tiles[y][x] = new Tile(x * this.tileWidth, y * this.tileHeight, this.tiles[y][x].textureIndex);
			}
		}
	}
	
	@Override
	public void update(GameTime gameTime) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics graphics) {
		if (this.visible) {
			int sourceX = 0;
			int sourceY = 0;
			
			for (int y = 0; y < this.height; y++) {
				for (int x = 0; x < this.width; x++) {
					sourceX = this.tiles[y][x].textureIndex % this.numOnX;
					sourceY = (int) Math.floor(this.tiles[y][x].textureIndex / this.numOnX);
					
					if (sourceY > 0) {
						sourceX = sourceX % (this.numOnX * sourceY);
					}
					else {
						sourceX = sourceX % this.numOnX;
					}
					
					sourceX *= this.tileWidth;
					sourceY *= this.tileHeight; 
					
					//graphics.drawImage(this.tileset.getTexture(), 0, 0, this.rectangle.getWidth(), this.rectangle.getHeight(), 0 * 32, 3 * 32, 160, 128, null);
		            graphics.drawImage(this.tileset.getTexture(), this.rectangle.getX(), this.rectangle.getY(), this.rectangle.getWidth(), this.rectangle.getHeight(), sourceX, sourceY, 160, 128, null);
				}
			}
		}
	}
}
