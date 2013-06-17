package atlantis.engine.graphics;

import java.awt.Graphics;

import atlantis.framework.GameTime;
import atlantis.framework.Rectangle;
import atlantis.framework.Vector2;

/**
 * A class that represent a sprite object that can be have animations and some physics.
 * @author Yannick
 */
public class Sprite extends Entity {
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
	
	public void prepareAnimation(int width, int height) {
		this.hasAnimation = true;
		this.spriteAnimator.initialize(width, height, this.texture.getWidth(), this.texture.getHeight());
		this.rectangle.width = width;
		this.rectangle.height = height;
	}
	
	public void addAnimation(String name, int[] framesIndex, int frameRate) {
		this.spriteAnimator.add(name, framesIndex, frameRate);
		this.sourceRectangle = this.spriteAnimator.getAnimations().get(name).getRectangle(0);
	}
	
	public void play(String name) {
		this.sourceRectangle = this.spriteAnimator.play(name);
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
		
	public void draw(Graphics graphics) {
		if (this.enabled) {
			this.postUpdate();
		}
		
		if (this.visible && this.assetLoaded) {
            if (this.hasAnimation) {
                graphics.drawImage(this.texture.getTexture(), this.rectangle.x, this.rectangle.y, this.rectangle.getRight(), this.rectangle.getBottom(), this.sourceRectangle.x, this.sourceRectangle.y, this.sourceRectangle.getRight(), this.sourceRectangle.getBottom(), null);
            }
            else {
                graphics.drawImage(this.texture.getTexture(), this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height, null);
            }
        }
	}
	
	// ---
	// --- Getters and setters
	// ---
	
	/**
	 * Determine the viewport of the sprite.
	 * @param viewport A rectangle where the sprite can move
	 */
	public void setViewport(Rectangle viewport) {
		this.viewport = viewport;
	}
	
	public void setViewport(int x, int y, int width, int height) {
		this.viewport.x = x;
		this.viewport.y = y;
		this.viewport.width = width;
		this.viewport.height = height;
	}

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

	public void setDirection(Vector2 direction) {
		this.direction = direction;
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

	public void forceInsideScreen(boolean insideScreen) {
		this.forceInsideScreen = insideScreen;
	}

	public void allowAcrossScreen(boolean acrossScreen) {
		this.allowAcrossScreen = acrossScreen;
	}
}
