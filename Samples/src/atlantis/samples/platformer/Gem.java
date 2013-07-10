package atlantis.samples.platformer;

import atlantis.engine.graphics.Sprite;
import atlantis.framework.GameTime;

public class Gem extends Sprite {
	public final int GemNormal = 5;
	public final int GemGold = 6;
	
	public int points;
	
	private float initialY;
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
		this.sign = 1;
	}
	
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		this.initialY = y;
	}
	
	@Override
	public void update(GameTime gameTime) {
		super.update(gameTime);
	
		if (this.enabled) {
	
		}
	}
	
	public int getPoints() {
		return this.points;
	}
}
