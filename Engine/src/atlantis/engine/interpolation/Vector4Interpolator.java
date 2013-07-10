// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.interpolation;

import atlantis.framework.Vector4;

/**
 * An interpolator for Vector3.
 * @author Yannick
 */
public class Vector4Interpolator extends BaseInterpolator<Vector4> {
	@Override
	protected void interpolateValue(float step) {
		Vector4.lerp(this.startValue, this.endValue, step);
	}
}
