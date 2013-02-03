package atlantis.engine.graphics;

import java.util.HashMap;
import atlantis.framework.Vector2;
import atlantis.framework.Rectangle;

public class SpriteAnimator {
	protected HashMap<String, SpriteAnimation> animations;
	protected int spriteWidth;
	protected int spriteHeight;
	protected int textureWidth;
	protected int textureHeight;
	protected int nbSpriteX;
	protected int nbSpriteY;
	protected int spritesheetLength;
	
	public SpriteAnimator() {
		this.animations = new HashMap<String, SpriteAnimation>();
        this.spriteWidth = 0;
        this.spriteHeight = 0;
        this.textureWidth = 0;
        this.textureHeight = 0;
        this.nbSpriteX = 0;
        this.nbSpriteY = 0;
        this.spritesheetLength = 0;
	}
	
	public void initialize(int animationWidth, int animationHeight, int textureWidth, int textureHeight) {
		this.animations.clear();
        this.spriteWidth = animationWidth;
        this.spriteHeight = animationHeight;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.nbSpriteX = this.textureWidth / this.spriteWidth;
        this.nbSpriteY = this.textureHeight / this.spriteHeight;
        this.spritesheetLength = this.nbSpriteX * this.nbSpriteY;
	}
	
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
	
	public void add(String name, Rectangle[] frames, int frameRate) {
		int animationLength = frames.length;

        SpriteAnimation animation = new SpriteAnimation(animationLength, frameRate);
        animation.rectangles = frames;
        
        this.animations.put(name, animation);
	}
	
	public void update(long elapsedTime, Vector2 lastDistance) {
		
	}
	
	public SpriteAnimation getAnimation(String name) {
		return this.animations.get(name);
	}

}
