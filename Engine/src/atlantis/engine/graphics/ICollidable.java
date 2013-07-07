// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

import atlantis.framework.Rectangle;
import atlantis.framework.Vector2;

public interface ICollidable {
	Vector2 getPosition();
	Rectangle getRectangle();
	float getX();
	float getY();
	float[] getColor();
}
