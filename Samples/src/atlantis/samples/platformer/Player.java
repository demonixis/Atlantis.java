package atlantis.samples.platformer;

import java.util.ArrayList;

import atlantis.engine.Atlantis;
import atlantis.engine.graphics.Sprite;
import atlantis.framework.GameTime;
import atlantis.framework.Vector2;
import atlantis.framework.audio.SoundEffect;
import atlantis.framework.content.ContentManager;

public final class Player extends Sprite {
	public enum MovementState {
		JumpingUp, JumpingDown, Walking	
	}
	
	private MovementState movementState;
	private int jumpHeight;
	private Vector2 initialJumpPosition;
	private float jumpSpeed;
	private SoundEffect[] soundEffects;
	private boolean canMove;
	
	public Player() {
		super("img/Player.png");
		
		this.soundEffects = new SoundEffect[3];
		
		// Physics
		this.gravity = new Vector2(0.0f, 9.0f);
		this.speed = 2.0f;
		this.canMove = true;
		
		// Jumping
		this.movementState = MovementState.Walking;
		this.jumpHeight = 125;
		this.jumpSpeed = 2.5f;
		this.initialJumpPosition = Vector2.Zero();
	}
	
	/**
	 * Load texture and prepare animations.
	 */
	public void loadContent(ContentManager content) {
		super.loadContent(content);
		
		this.soundEffects[0] = content.loadSound("Sounds/PlayerFall.wav");
		this.soundEffects[1] = content.loadSound("Sounds/PlayerJump.wav");
		this.soundEffects[2] = content.loadSound("Sounds/PlayerKilled.wav");
		
		this.prepareAnimation(64, 64);
		this.addAnimation("idle", new int[] { 0 }, 0);
		this.addAnimation("left", new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 30);
		this.addAnimation("right", new int[] { 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 }, 30);
		this.addAnimation("jumpLeft", new int[] { 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32 }, 30);
		this.addAnimation("jumpRight", new int[] { 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43 }, 30);
		this.addAnimation("dieLeft", new int[] { 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54 }, 10);
		this.addAnimation("dieRight", new int[] { 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65 }, 10);
		this.addAnimation("winLeft", new int[] { 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76 }, 10);
		this.addAnimation("winRight", new int[] { 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87 }, 10);
	}
	
	/**
	 * Update the logic and input.
	 * @param gameTime
	 */
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		if (this.canMove) {
			if (Atlantis.keyboard.left()) {
				this.play("left");
				this.setPosition((int) (this.getX() - this.speed), this.getY());
			}
			else if (Atlantis.keyboard.right()) {
				this.play("right");
				this.setPosition((int) (this.getX() + this.speed), this.getY());
			}
			else {
				this.play("idle");
			}
			
			if (Atlantis.keyboard.space()) {
				if (this.getDirection().x < 0) {
					this.play("jumpLeft");
				}
				else {
					this.play("jumpRight");
				}
				jump();
			}
		}
		// Manage the jump
		if (this.movementState != MovementState.Walking) {
			if (this.movementState == MovementState.JumpingUp) {
				this.setPosition(this.getX(), (int) (this.getY() - this.jumpSpeed));
				if (this.getY() < (this.initialJumpPosition.y - this.jumpHeight)) {
					this.movementState = MovementState.JumpingDown;
				}
			}
			
			if (movementState == MovementState.JumpingDown) {
				this.setPosition(this.getX(), (int) (this.getY() + this.jumpSpeed));
			}
			
			if (this.getY() >= this.initialJumpPosition.y) {
				this.movementState = MovementState.Walking;
			}
		}
	}
	
	/**
	 * Update physics of the player by using a gravity on it.
	 * @param blocksSize Size of the collection of blocks.
	 * @param blocks Blocks that can be collided.
	 */
	public void updatePhysics(int blocksSize, ArrayList<Sprite> blocks) {
		if (this.movementState != MovementState.JumpingUp) {
			this.setY((int) (this.getY() + this.gravity.y));
		}
		
		int i = 0;
		boolean collide = false;
	
		while(i < blocksSize && collide == false) {
			if (this.getBoundingRectangle().contains(blocks.get(i).getBoundingRectangle())) {
				if(this.position.y < blocks.get(i).getY() && this.movementState == MovementState.JumpingUp) {
						this.movementState = MovementState.JumpingDown;
						this.setY(blocks.get(i).getBoundingRectangle().getBottom());
						collide = true;
				} 
				else {
					collide = true;
					this.setY(blocks.get(i).getY() - this.getHeight());
					this.movementState = MovementState.Walking;
				}
			}
			i++;
		}
	}
	
	private void jump() {
		if (this.movementState == MovementState.Walking) {
			this.soundEffects[1].play();
			this.movementState = MovementState.JumpingUp;
			this.initialJumpPosition = new Vector2(this.getX(), this.getY());
		}
	}
	
	public void win() {
		if (this.direction.x < 0) {
			this.play("winLeft");
		}
		else {
			this.play("winRight");
		}
		this.canMove = false;
	}
	
	public void reset() {
		if (this.assetLoaded) {
			this.play("idle");
			this.canMove = true;
		}
	}
	
	public void die(String type) {
		if (this.direction.x < 0) {
			this.play("dieLeft");
		}
		else {
			this.play("dieRight");
		}
		
		if (type == "Lose") {
			this.soundEffects[0].play();
		}
		else {
			this.soundEffects[2].play();
		}
		
		this.canMove = false;
	}
	
	public void setStartPosition(Vector2 start) {
		setPosition(start.x * 24, start.y * 32 - this.getHeight());
	}
}
