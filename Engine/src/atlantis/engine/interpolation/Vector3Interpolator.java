// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.interpolation;

import atlantis.framework.Vector3;

/**
 * An interpolator for Vector3.
 * @author Yannick
 */
public class Vector3Interpolator extends BaseInterpolator<Vector3> {
	@Override
	protected void interpolateValue(float step) {
		Vector3.lerp(this.startValue, this.endValue, step);
	}
}
