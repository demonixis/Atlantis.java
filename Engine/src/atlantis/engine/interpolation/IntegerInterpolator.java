// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.interpolation;

import atlantis.framework.MathHelper;

/**
 * An interpolator for integer numbers.
 * @author Yannick
 */
public class IntegerInterpolator extends BaseInterpolator<Integer> {
	@Override
	protected void interpolateValue(float step) {
		this.interpolatedValue = (int)MathHelper.lerp(this.startValue, this.endValue, step);
	}
}
