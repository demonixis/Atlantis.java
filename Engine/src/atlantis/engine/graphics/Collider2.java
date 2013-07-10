// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

public class Collider2 {
	public static boolean collide(Sprite spriteA, Sprite spriteB) {
		return spriteA.getBoundingRectangle().contains(spriteB.getBoundingRectangle());
	}
}
