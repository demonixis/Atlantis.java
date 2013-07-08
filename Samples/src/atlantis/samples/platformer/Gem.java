package atlantis.samples.platformer;

import atlantis.engine.graphics.Sprite;
import atlantis.framework.GameTime;

public class Gem extends Sprite {
	public final int GemNormal = 5;
	public final int GemGold = 6;
	
	public int points;
	
	public Gem(int assetId) {
		super();
		
		this.textureName = "img/Backgrounds/";
		
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
