package atlantis.engine.graphics;

import atlantis.framework.Vector2;

/**
 * A basic physics module for a 2D entity.
 * @author Yannick
 */
public class Physics2 {
	protected Vector2 acceleration;
	protected Vector2 velocity;
	protected float maxVelocity;
	protected Vector2 gravity;
	protected float friction;
	protected boolean enableVelocity;
	protected boolean enableGravity;
	
	public Physics2() {
		this.acceleration = new Vector2(1.0f);
		this.velocity = new Vector2();
		this.maxVelocity = 1.0f;
		this.gravity = new Vector2();
		this.friction = 1.0f;
		this.enableVelocity = true;
		this.enableGravity = true;
	}
	
	public void applyVelocity(Sprite sprite) {
		sprite.position.x = (sprite.position.x + this.velocity.x) * this.acceleration.x;
        sprite.position.y = (sprite.position.y + this.velocity.y) * this.acceleration.y;
        this.velocity.multiply(this.maxVelocity);
	}
	
	public void applyGravity(Sprite sprite) {
		sprite.position.add(this.gravity);
		sprite.rectangle.setPosition(sprite.position.x, sprite.position.y);
	}

	// ---
	// --- Getters and Setters declarations
	// ---
	
	/**
	 * @return the acceleration
	 */
	public final Vector2 getAcceleration() {
		return acceleration;
	}

	/**
	 * @return the velocity
	 */
	public final Vector2 getVelocity() {
		return velocity;
	}

	/**
	 * @return the maxVelocity
	 */
	public final float getMaxVelocity() {
		return maxVelocity;
	}

	/**
	 * @return the gravity
	 */
	public final Vector2 getGravity() {
		return gravity;
	}

	/**
	 * @return the friction
	 */
	public final float getFriction() {
		return friction;
	}

	/**
	 * @param acceleration the acceleration to set
	 */
	public final void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * @param velocity the velocity to set
	 */
	public final void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	/**
	 * @param maxVelocity the maxVelocity to set
	 */
	public final void setMaxVelocity(float maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	/**
	 * @param gravity the gravity to set
	 */
	public final void setGravity(Vector2 gravity) {
		this.gravity = gravity;
	}

	/**
	 * @param friction the friction to set
	 */
	public final void setFriction(float friction) {
		this.friction = friction;
	}
}
