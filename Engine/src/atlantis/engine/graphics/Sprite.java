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
	protected Vector2 lastPosition;
	protected Vector2 lastDistance;
	protected Vector2 acceleration;
	protected Vector2 velocity;
	protected float maxVelocity;
	protected Rectangle viewport;
	protected boolean insideScreen;
	protected boolean acrossScreen;
	protected SpriteAnimator spriteAnimator;
	protected boolean hasAnimation;
	protected long elapsedTime;
	
	public Sprite() {
		this.sourceRectangle = new Rectangle();
		this.direction = new Vector2();
		this.lastPosition = new Vector2();
		this.lastDistance = new Vector2();
		this.acceleration = new Vector2();
		this.velocity = new Vector2();
		this.maxVelocity = 1.0f;
		this.viewport = new Rectangle();
		this.insideScreen = false;
		this.acrossScreen = false;
		this.spriteAnimator = new SpriteAnimator();
		this.hasAnimation = false;
		this.elapsedTime = 0;
	}
	
	public Sprite(String textureName) {
		this();
		this.textureName = textureName;
	}
	
	public void prepareAnimation(int width, int height) {
		this.hasAnimation = true;
		this.spriteAnimator.initialize(width, height, this.texture.getWidth(), this.texture.getHeight());
		this.rectangle.setWidth(width);
		this.rectangle.setHeight(height);
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
            this.elapsedTime += gameTime.getElapsedTime();

            // Determine the last distance and direction
            this.lastDistance.x = (this.position.x - this.lastPosition.x);
            this.lastDistance.y = (this.position.y - this.lastPosition.y);   

            // Determine the last position
            this.lastPosition.x = ((int)this.position.x);
            this.lastPosition.y = ((int)this.position.y);

            // Update physics
            this.position.x = (this.position.x + this.velocity.x * this.acceleration.x);
            this.position.y = (this.position.y + this.velocity.y * this.acceleration.y);
            this.velocity.multiply(this.maxVelocity);

            // Update the rectangle position
            this.rectangle.setX((int)this.position.x);
            this.rectangle.setY((int)this.position.y);

            // Update animation
            if (this.hasAnimation) {
                this.spriteAnimator.update(gameTime);
                
                if (this.lastDistance.x == 0 && this.lastDistance.y == 0 && this.spriteAnimator.getCurrentAnimationName() != "") {
                	this.sourceRectangle = this.spriteAnimator.getCurrentAnimation().getRectangle(0);
                }
            }
        }
	}
	
	protected void postUpdate() {
		if (this.enabled) {
            this.direction.x = this.position.x - this.lastPosition.x;
            this.direction.y = this.position.y - this.lastPosition.y;

            // Force the sprite to stay inside screen
            if (this.insideScreen) {
                if (this.position.x < this.viewport.getX()) {
                    this.position.x = this.viewport.getX();
                    this.velocity.multiply(0);
                }
                else if (this.rectangle.getRight() > this.viewport.getWidth()) {
                    this.position.x = this.viewport.getWidth() - this.rectangle.getWidth();
                    this.velocity.multiply(0);
                }

                if (this.position.y < this.viewport.getY()) {
                    this.position.y = this.viewport.getY();
                    this.velocity.multiply(0);
                }
                else if (this.rectangle.getBottom() > this.viewport.getHeight()) {
                    this.position.y = this.viewport.getHeight() - this.rectangle.getHeight();
                    this.velocity.multiply(0);
                }
            }

            // The sprite move throw the screen
            else if (this.acrossScreen) {
                if (this.rectangle.getRight() < this.viewport.getX()) {
                    this.position.x = this.viewport.getWidth();
                }
                else if (this.position.x > this.viewport.getWidth()) {
                    this.position.x = this.viewport.getX();
                }

                if (this.rectangle.getBottom() < this.viewport.getY()) { 
                    this.position.y = this.viewport.getHeight();
                }
                else if (this.position.y > this.viewport.getHeight()) {
                    this.position.y = this.viewport.getY();
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
                graphics.drawImage(this.texture.getTexture(), this.rectangle.getX(), this.rectangle.getY(), this.rectangle.getRight(), this.rectangle.getBottom(), this.sourceRectangle.getX(), this.sourceRectangle.getY(), this.sourceRectangle.getRight(), this.sourceRectangle.getBottom(), null);
            }
            else {
                graphics.drawImage(this.texture.getTexture(), this.rectangle.getX(), this.rectangle.getY(), this.rectangle.getWidth(), this.rectangle.getHeight(), null);
            }
        }
	}
	
	public void setViewport(Rectangle viewport) {
		this.viewport = viewport;
	}
	
	public void setViewport(int x, int y, int width, int height) {
		this.viewport.setX(x);
		this.viewport.setY(y);
		this.viewport.setWidth(width);
		this.viewport.setHeight(height);
	}

	public Vector2 getDirection() {
		return direction;
	}

	public Vector2 getLastPosition() {
		return lastPosition;
	}

	public Vector2 getLastDistance() {
		return lastDistance;
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

	public boolean isInsideScreen() {
		return insideScreen;
	}

	public boolean isAcrossScreen() {
		return acrossScreen;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public void setLastPosition(Vector2 lastPosition) {
		this.lastPosition = lastPosition;
	}

	public void setLastDistance(Vector2 lastDistance) {
		this.lastDistance = lastDistance;
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

	public void setInsideScreen(boolean insideScreen) {
		this.insideScreen = insideScreen;
	}

	public void setAcrossScreen(boolean acrossScreen) {
		this.acrossScreen = acrossScreen;
	}
}
