package atlantis.engine.interpolation;

import atlantis.framework.MathHelper;

/**
 * An interpolator for float numbers.
 * @author Yannick
 */
public class FloatInterpolator extends BaseInterpolator<Float> {
	@Override
	protected void interpolateValue(float step) {
		this.interpolatedValue = MathHelper.lerp(this.startValue, this.endValue, step);
	}
}
