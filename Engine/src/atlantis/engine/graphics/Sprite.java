// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

import atlantis.framework.GameTime;
import atlantis.framework.Rectangle;
import atlantis.framework.Vector2;
import atlantis.framework.content.ContentManager;
import atlantis.framework.graphics.SpriteBatch;
import atlantis.framework.graphics.Texture2D;

/**
 * A sprite is a graphical object which can be a simple image or a more complex animated entity.
 * @author Yannick
 */
public class Sprite extends BaseEntity {
	protected Rectangle rectangle;
	protected Vector2 position;
	protected Texture2D texture;
	protected String textureName;
	protected Rectangle sourceRectangle;
	protected Vector2 direction;
	protected Vector2 previousPosition;
	protected Vector2 previousDistance;
	protected Vector2 acceleration;
	protected Vector2 velocity;
	protected float maxVelocity;
	protected Rectangle viewport;
	protected boolean forceInsideScreen;
	protected boolean allowAcrossScreen;
	protected SpriteAnimator spriteAnimator;
	protected boolean hasAnimation;
	
	public Sprite() {
		this.rectangle = new Rectangle();
		this.position = new Vector2();
		this.texture = null;
		this.textureName = "";
		this.sourceRectangle = new Rectangle();
		this.direction = new Vector2();
		this.previousPosition = new Vector2();
		this.previousDistance = new Vector2();
		this.acceleration = new Vector2();
		this.velocity = new Vector2();
		this.maxVelocity = 1.0f;
		this.viewport = new Rectangle();
		this.forceInsideScreen = false;
		this.allowAcrossScreen = false;
		this.spriteAnimator = new SpriteAnimator();
		this.hasAnimation = false;
	}
	
	public Sprite(String textureName) {
		this();
		this.textureName = textureName;
	}
	
	public void loadContent(ContentManager content) {
		if (this.textureName != "" && this.assetLoaded == false) {
			this.texture = content.loadTexture(this.textureName);
			this.rectangle.width = this.texture.getWidth();
			this.rectangle.height = this.texture.getHeight();
			this.assetLoaded = true;
		}
	}
	
	public void update(GameTime gameTime) {
		if (this.enabled) {
            // Determine the last distance and direction
            this.previousDistance.x = (this.position.x - this.previousPosition.x);
            this.previousDistance.y = (this.position.y - this.previousPosition.y);   

            // Determine the last position
            this.previousPosition.x = (int)this.position.x;
            this.previousPosition.y = (int)this.position.y;

            // Update physics
            this.position.x = this.position.x + this.velocity.x * this.acceleration.x;
            this.position.y = this.position.y + this.velocity.y * this.acceleration.y;
            this.velocity.multiply(this.maxVelocity);

            // Update the rectangle position
            this.rectangle.x = (int)this.position.x;
            this.rectangle.y = (int)this.position.y;

            // Update animation
            if (this.hasAnimation) {
                this.spriteAnimator.update(gameTime);
                
                if (this.previousDistance.x == 0 && this.previousDistance.y == 0 && this.spriteAnimator.getCurrentAnimationName() != "") {
                	this.sourceRectangle = this.spriteAnimator.getCurrentAnimation().getRectangle(0);
                }
            }
        }
	}
	
	/**
	 * Called when the update process is done and before the draw method.
	 * Some operations are done in this method such as position, direction updates and collides detection. 
	 */
	protected void postUpdate() {
		if (this.enabled) {
            this.direction.x = this.position.x - this.previousPosition.x;
            this.direction.y = this.position.y - this.previousPosition.y;

            // Force the sprite to stay inside screen
            if (this.forceInsideScreen) {
                if (this.position.x < this.viewport.x) {
                    this.position.x = this.viewport.x;
                    this.velocity.multiply(0);
                }
                else if (this.rectangle.getRight() > this.viewport.width) {
                    this.position.x = this.viewport.width - this.rectangle.width;
                    this.velocity.multiply(0);
                }

                if (this.position.y < this.viewport.y) {
                    this.position.y = this.viewport.y;
                    this.velocity.multiply(0);
                }
                else if (this.rectangle.getBottom() > this.viewport.height) {
                    this.position.y = this.viewport.height - this.rectangle.height;
                    this.velocity.multiply(0);
                }
            }

            // The sprite move throw the screen
            else if (this.allowAcrossScreen) {
                if (this.rectangle.getRight() < this.viewport.x) {
                    this.position.x = this.viewport.width;
                }
                else if (this.position.x > this.viewport.width) {
                    this.position.x = this.viewport.x;
                }

                if (this.rectangle.getBottom() < this.viewport.y) { 
                    this.position.y = this.viewport.height;
                }
                else if (this.position.y > this.viewport.height) {
                    this.position.y = this.viewport.y;
                }
            }
        }
	}
		
	@Override
	public void draw(GameTime gameTime, SpriteBatch spriteBatch) {
		if (this.enabled) {
			this.postUpdate();
		}
		
		if (this.visible && this.assetLoaded) {
            if (this.hasAnimation) {
                spriteBatch.draw(this.texture, this.rectangle, this.sourceRectangle, 0, 0);
            }
            else {
            	spriteBatch.draw(this.texture, this.rectangle);
            }
        }
	}
	
	// ---
	// --- Animation methods
	// ---
	
	/**
	 * Initialize the first step of the animation process. You must call this method before add any animation.
	 * @param width The width of an animation (Can be recomputed later).
	 * @param height The height of an animation (Can be recomputed later).
	 */
	public void prepareAnimation(int width, int height) {
		this.hasAnimation = true;
		this.spriteAnimator.initialize(width, height, this.texture.getWidth(), this.texture.getHeight());
		this.rectangle.width = width;
		this.rectangle.height = height;
	}
	
	/**
	 * Add an animation which is a set of frame index on the sprite sheet.
	 * @param name The name of the animation.
	 * @param framesIndex An array of index.
	 * @param frameRate The desired frame rate for this animation.
	 */
	public void addAnimation(String name, int[] framesIndex, int frameRate) {
		this.spriteAnimator.add(name, framesIndex, frameRate);
		this.sourceRectangle = this.spriteAnimator.getAnimations().get(name).getRectangle(0);
	}
	
	/**
	 * Play an animation by its name.
	 * @param name The name of the animation to play.
	 */
	public void play(String name) {
		this.sourceRectangle = this.spriteAnimator.play(name);
	}
	
	// ---
	// --- Getters and setters
	// ---
	
	/**
	 * Set the position of the entity on the screen
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	public void setPosition(int x, int y) {
		this.position.set((float)x, (float)y);
		this.rectangle.setPosition(x, y);
	}
	
	/**
	 * Change the size of the sprite
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height) {
		this.rectangle.setSize(width, height);
	}
	
	/**
	 * Sets the value of X coordinate of the entity
	 * @param x New X value on screen.
	 */
	public void setX(int x) {
		this.position.x = x;
		this.rectangle.x = x;
	}
	
	/**
	 * Sets the value of Y coordinate of the entity
	 * @param y New Y value on screen.
	 */
	public void setY(int y) {
		this.position.y = y;
		this.rectangle.y = y;
	}
	
	/**
	 * Sets the width of the sprite.
	 * @param width Desired width.
	 */
	public void setWidth(int width) {
		this.rectangle.width = width;
	}
	
	/**
	 * Sets the height of the sprite.
	 * @param height Desired height.
	 */
	public void setHeight(int height) {
		this.rectangle.height = height;
	}

	/**
	 * Gets the X position on screen.
	 * @return Return the X position.
	 */
	public int getX() {
		return this.rectangle.x;
	}
	
	/**
	 * Gets the Y position on screen.
	 * @return Return the Y position.
	 */
	public int getY() {
		return this.rectangle.y;
	}
	
	/**
	 * Gets the width.
	 * @return Return width.
	 */
	public int getWidth() {
		return this.rectangle.width;
	}
	
	/**
	 * Gets the height.
	 * @return Return height
	 */
	public int getHeight() {
		return this.rectangle.height;
	}
	
	/**
	 * Gets the rectangle of the sprite
	 * @return Return the rectangle.
	 */
	public Rectangle getRectangle() {
		return this.rectangle;
	}
	
	/**
	 * Determine the viewport of the sprite.
	 * @param viewport A rectangle where the sprite can move
	 */
	public void setViewport(Rectangle viewport) {
		this.viewport = viewport;
	}
	
	/**
	 * Determine the viewport of the sprite.
	 * @param x Start position on X.
	 * @param y Start position on Y.
	 * @param width Width
	 * @param height Height
	 */
	public void setViewport(int x, int y, int width, int height) {
		this.viewport.x = x;
		this.viewport.y = y;
		this.viewport.width = width;
		this.viewport.height = height;
	}

	/**
	 * Gets the actual direction of the Sprite. 
	 * X: -1 = left / 1 = right
	 * Y: -1 = up / 1 = down
	 * @return
	 */
	public Vector2 getDirection() {
		return direction;
	}

	public Vector2 getPreviousPosition() {
		return previousPosition;
	}

	public Vector2 getPreviousDistance() {
		return previousDistance;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public float getMaxVelocity() {
		return maxVelocity;
	}

	public Rectangle getViewport() {
		return viewport;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public void setMaxVelocity(float maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	/**
	 * Force the sprite to stay inside its viewport.
	 * @param insideScreen Sets to true to force.
	 */
	public void forceInsideScreen(boolean insideScreen) {
		this.forceInsideScreen = insideScreen;
	}

	/**
	 * Allow the sprite across the screen.
	 * @param acrossScreen Sets to true to enable.
	 */
	public void allowAcrossScreen(boolean acrossScreen) {
		this.allowAcrossScreen = acrossScreen;
	}
}
