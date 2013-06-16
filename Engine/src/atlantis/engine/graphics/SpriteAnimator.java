package atlantis.engine.graphics;

import java.util.HashMap;

import atlantis.framework.GameTime;
import atlantis.framework.Rectangle;
import atlantis.framework.Vector2;

/**
 * A sprite animator that work with a collection of source rectangle
 * to display the correct animation.
 * @author Yannick
 */
public class SpriteAnimator {
	protected HashMap<String, SpriteAnimation> animations;
	protected int spriteWidth;
	protected int spriteHeight;
	protected int textureWidth;
	protected int textureHeight;
	protected int nbSpriteX;
	protected int nbSpriteY;
	protected int spritesheetLength;
	protected String currentAnimationName;
	
	public SpriteAnimator() {
		this.animations = new HashMap<String, SpriteAnimation>();
        this.spriteWidth = 0;
        this.spriteHeight = 0;
        this.textureWidth = 0;
        this.textureHeight = 0;
        this.nbSpriteX = 0;
        this.nbSpriteY = 0;
        this.spritesheetLength = 0;
        this.currentAnimationName = "";
	}
	
	/**
	 * Initiliaze the animation process.
	 * @param animationWidth
	 * @param animationHeight
	 * @param textureWidth
	 * @param textureHeight
	 */
	public void initialize(int animationWidth, int animationHeight, int textureWidth, int textureHeight) {
		this.animations.clear();
        this.spriteWidth = animationWidth;
        this.spriteHeight = animationHeight;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.nbSpriteX = this.textureWidth / this.spriteWidth;
        this.nbSpriteY = this.textureHeight / this.spriteHeight;
        this.spritesheetLength = this.nbSpriteX * this.nbSpriteY;
        this.currentAnimationName = "";
	}
	
	/**
	 * Add an animation to the animator
	 * @param name The name of the animation.
	 * @param framesIndex An array of index.
	 * @param frameRate Desired framerate.
	 */
	public void add(String name, int[] framesIndex, int frameRate) {
		int animationLength = framesIndex.length;

        SpriteAnimation animation = new SpriteAnimation(animationLength, frameRate);

        for (int i = 0; i < animationLength; i++) {
            int x = (int)(framesIndex[i] % this.nbSpriteX);
            int y = (int) Math.floor(framesIndex[i] / this.nbSpriteX);

            if (y > 0) {
                x = x % (this.nbSpriteX * y);
            }
            else {
                x = x % this.nbSpriteX; 
            }

            animation.rectangles[i] = new Rectangle(x * this.spriteWidth, y * this.spriteHeight, this.spriteWidth, this.spriteHeight);
        }
       
        this.animations.put(name, animation);
	}
	
	/**
	 * Add an animation to the animator
	 * @param name The name of the animation.
	 * @param Rectangle An array of Rectangle.
	 * @param frameRate Desired framerate.
	 */
	public void add(String name, Rectangle[] frames, int frameRate) {
		int animationLength = frames.length;

        SpriteAnimation animation = new SpriteAnimation(animationLength, frameRate);
        animation.rectangles = frames;
        
        this.animations.put(name, animation);
	}
	
	/**
	 * Play an animation by its name
	 * @param animationName The name of the animation to play.
	 * @return Return the source rectangle of the animation.
	 */
	public Rectangle play(String animationName) {
		this.currentAnimationName = animationName;
		return this.animations.get(animationName).next();
	}
	
	/**
	 * Update animator.
	 * @param gameTime
	 */
	public void update(GameTime gameTime) {
		if (this.currentAnimationName != "") {
			this.animations.get(this.currentAnimationName).update(gameTime);
		}
	}

	/**
	 * Gets the current animation if the sprite is currently animated.
	 * @return Return the current animation otherwise return null.
	 */
	public SpriteAnimation getCurrentAnimation() {
		if (this.currentAnimationName != "") {
			return this.animations.get(this.currentAnimationName);
		}
		return null;
	}
	
	/**
	 * Retrieve animations
	 * @return An HeashMap of animations.
	 */
	public HashMap<String, SpriteAnimation> getAnimations() {
		return this.animations;
	}
	
	/**
	 * Gets the current animation name.
	 * @return Return the current animation name.
	 */
	public String getCurrentAnimationName() {
		return this.currentAnimationName;
	}
}
