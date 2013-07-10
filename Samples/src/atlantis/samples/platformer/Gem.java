package atlantis.samples.platformer;

import atlantis.engine.graphics.Sprite;
import atlantis.framework.GameTime;

public class Gem extends Sprite {
	private static int counter = 0;
	public final int GemNormal = 5;
	public final int GemGold = 6;
	
	public int points;
	
	private float initialY;
	private float maxY;
	private float minY;
	private int sign;
	
	public Gem(int assetId) {
		super();
		
		this.textureName = "img/Tiles/";
		
		if (assetId == GemNormal) {
			this.textureName += "Gem.png";
			this.points = 20;
			this.name = "Gem";
		}
		else {
			this.textureName += "YellowGem.png";
			this.points = 50;
			this.name = "GemG";
		}
		
		this.initialY = 0;
		this.sign = counter % 2 == 0 ? -1 : 1;
		this.speed = 0.01f;
		counter++;
	}
	
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		this.initialY = y;
		this.maxY = this.initialY + 5;
		this.minY = this.initialY - 10;
	}
	
	@Override
	public void update(GameTime gameTime) {
		super.update(gameTime);
	
		if (this.enabled) {
			
			
			this.setY(this.getY() + this.speed * this.sign * gameTime.getElapsedTime()); 
			
			if (this.getY() <= this.minY) {
				this.sign = 1;
				this.setY(this.minY);
			}
			else if (this.getY() >= this.maxY) {
				this.sign = -1;
				this.setY(this.maxY);
			}
		}
	}
	
	public int getPoints() {
		return this.points;
	}
}
