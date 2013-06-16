package atlantis.engine.graphics;

import atlantis.framework.GameTime;
import atlantis.framework.Rectangle;

public class SpriteAnimation {
	protected int frameRate;
	protected double frameRateValue;
	protected int index;
	protected long elapsedTime;
	protected int length;
	protected Rectangle[] rectangles;
	
	public SpriteAnimation(int length, int framerate) {
		this.rectangles = new Rectangle[length];
		this.index = 0;
		this.elapsedTime = 0;
		this.length = length;
		this.setFramerate(framerate);
	}
	
	public Rectangle next() {
        return this.rectangles[this.index];
	}
	
	public void update(GameTime gameTime) {
		this.elapsedTime += gameTime.getElapsedTime();
		if (this.elapsedTime > this.frameRateValue) {
			this.setIndex(this.index + 1);
			this.elapsedTime = 0;
		}
	}
	
	public Rectangle getRectangle(int index) {
		return this.rectangles[index];
	}
	
	public int count() {
		return this.length;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setIndex (int index) {
		this.index = (index < 0) ? 0 : index;
		this.index = (index >= this.length) ? this.index = 0 : index;
	}
	
	public int getFramerate() {
		return this.frameRate;
	}
	
	public void setFramerate(int framerate) {
		this.frameRate = framerate;
		
		if (this.frameRate > 0) {
			this.frameRateValue = 1000 / this.frameRate;
		}
		else {
			this.frameRateValue = 0;
		}
	}
}
