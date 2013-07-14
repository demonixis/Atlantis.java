// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

import java.awt.Color;

import atlantis.framework.graphics.Texture2D;

public class Collider2 {
	public static boolean rectangleCollide(Sprite spriteA, Sprite spriteB) {
		return spriteA.getBoundingRectangle().contains(spriteB.getBoundingRectangle());
	}
	
	public static boolean pixelPerfectCollide(Sprite spriteA, Sprite spriteB) {
		if (rectangleCollide(spriteA, spriteB)) {
			int top = Math.max(spriteA.getBoundingRectangle().getTop(), spriteB.getBoundingRectangle().getTop());
			int bottom = Math.min(spriteA.getBoundingRectangle().getBottom(), spriteB.getBoundingRectangle().getBottom());
			int left = Math.max(spriteA.getBoundingRectangle().getLeft(), spriteB.getBoundingRectangle().getLeft());
			int right = Math.min(spriteA.getBoundingRectangle().getRight(), spriteB.getBoundingRectangle().getRight());
			
			for (int y = top; y < bottom; y++) {
				for (int x = left; x < right; x++) {
					int index_A = (x - spriteA.getBoundingRectangle().getLeft()) + (y - spriteA.getBoundingRectangle().getTop()) * spriteA.getBoundingRectangle().getWidth();
					int index_B = (x - spriteB.getBoundingRectangle().getLeft()) + (y - spriteB.getBoundingRectangle().getTop()) * spriteB.getBoundingRectangle().getWidth();
	
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
