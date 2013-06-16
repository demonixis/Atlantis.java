package atlantis.engine.graphics;

import java.awt.Graphics;
import atlantis.framework.GameTime;
import atlantis.framework.Rectangle;
import atlantis.framework.Vector2;
import atlantis.framework.content.ContentManager;
import atlantis.framework.graphics.Texture2D;

/**
 * Define a basic entity who is updated and drawn on each frame if enabled and/or visible.
 * @author Yannick
 */
public class Entity extends BaseEntity {
	protected Rectangle rectangle;
	protected Vector2 position;
	protected Texture2D texture;
	protected String textureName;
	
	public Entity() {
		this.rectangle = new Rectangle();
		this.position = new Vector2();
		this.texture = null;
		this.textureName = "";
	}
	
	public Entity(String textureName) {
		this();
		this.textureName = textureName;
	}
	
	public void initialize() {
		this.initialized = true;
	}
	
	public void loadContent(ContentManager content) {
		if (this.textureName != "" && this.assetLoaded == false) {
			this.texture = content.loadTexture(this.textureName);
			this.rectangle.setWidth(this.texture.getWidth());
			this.rectangle.setHeight(this.texture.getHeight());
			this.assetLoaded = true;
		}
	}
	

	@Override
	public void update(GameTime gameTime) { }
	
	@Override
	public void draw(Graphics graphics) {
        if (this.visible && this.assetLoaded) {
            graphics.drawImage(this.texture.getTexture(), this.rectangle.getX(), this.rectangle.getY(), this.rectangle.getWidth(), this.rectangle.getHeight(), null);
        }
	}
	
	/**
	 * Set the position of the entity on the screen
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void setPosition(int x, int y) {
		this.position.setPosition((float)x, (float)y);
		this.rectangle.setPosition(x, y);
	}
	
	/**
	 * Change the size of the entity
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height) {
		this.rectangle.setSize(width, height);
	}
	
	/**
	 * Sets the value of X coordinate of the entity
	 * @param x
	 */
	public void setX(int x) {
		this.position.setX(x);
		this.rectangle.setX(x);
	}
	
	/**
	 * Sets the value of Y coordinate of the entity
	 * @param y
	 */
	public void setY(int y) {
		this.position.setY(y);
		this.rectangle.setY(y);
	}
	
	public void setWidth(int width) {
		this.rectangle.setWidth(width);
	}
	
	public void setHeight(int height) {
		this.rectangle.setHeight(height);
	}

	public int getX() {
		return this.rectangle.getX();
	}
	
	public int getY() {
		return this.rectangle.getY();
	}
	
	public int getWidth() {
		return this.rectangle.getWidth();
	}
	
	public int getHeight() {
		return this.rectangle.getHeight();
	}
	
	public Rectangle getRectangle() {
		return this.rectangle;
	}
}
