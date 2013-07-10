// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.interpolation;

import atlantis.framework.Vector2;

/**
 * An interpolator for Vector2.
 * @author Yannick
 */
public class Vector2Interpolator extends BaseInterpolator<Vector2> {

	@Override
	protected void interpolateValue(float step) {
		this.interpolatedValue = Vector2.lerp(this.startValue, this.endValue, step);
	}
}
