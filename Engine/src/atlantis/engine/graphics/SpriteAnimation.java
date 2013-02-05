package atlantis.engine.graphics;

import atlantis.framework.Rectangle;

public class SpriteAnimation {
	protected Rectangle[] rectangles;
	protected int frameRate;
	protected int index;
	protected int max;
	protected long elapsedTime;
	protected int length;
	
	public SpriteAnimation(int length, int frameRate) {
		this.rectangles = new Rectangle[length];
		this.frameRate = frameRate;
		this.index = 0;
		this.max = length;
		this.elapsedTime = 0;
		this.length = 0;
	}
	
	public Rectangle next(long elapsedTime) {
		this.elapsedTime += elapsedTime * 0.001f;
		
		if (this.elapsedTime > this.frameRate) {
            this.index++;
            if (this.index >= this.max) {
                this.index = 0;
            }
            
            this.elapsedTime = 0;
        }
 
        return this.rectangles[this.index];
	}
	
	public Rectangle getRectangle(int index) {
		return this.rectangles[index];
	}
}
