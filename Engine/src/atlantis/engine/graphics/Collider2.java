// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

import java.awt.Color;

import atlantis.framework.graphics.Texture2D;

public class Collider2 {
	public static boolean rectangleCollide(Sprite spriteA, Sprite spriteB) {
		return spriteA.getBounds().contains(spriteB.getBounds());
	}
	
	public static boolean pixelPerfectCollide(Sprite spriteA, Sprite spriteB) {
		if (rectangleCollide(spriteA, spriteB)) {
			int top = Math.max(spriteA.getBounds().getTop(), spriteB.getBounds().getTop());
			int bottom = Math.min(spriteA.getBounds().getBottom(), spriteB.getBounds().getBottom());
			int left = Math.max(spriteA.getBounds().getLeft(), spriteB.getBounds().getLeft());
			int right = Math.min(spriteA.getBounds().getRight(), spriteB.getBounds().getRight());
			
			for (int y = top; y < bottom; y++) {
				for (int x = left; x < right; x++) {
					int index_A = (x - spriteA.getBounds().getLeft()) + (y - spriteA.getBounds().getTop()) * spriteA.getBounds().getWidth();
					int index_B = (x - spriteB.getBounds().getLeft()) + (y - spriteB.getBounds().getTop()) * spriteB.getBounds().getWidth();
	
					Color[] colorsSpriteA = Texture2D.getData(spriteA.getTexture());
					Color[] colorsSpriteB = Texture2D.getData(spriteB.getTexture());
	
					Color colorSpriteA = colorsSpriteA[index_A];
					Color colorSpriteB = colorsSpriteB[index_B];
	
					if (colorSpriteA.getAlpha() != 0 && colorSpriteB.getAlpha() != 0)
						return true;
				}
			}
		}
		return false;
	}
}
