// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.interpolation;

/**
 * A base class for making interpolator.
 * @author Yannick
 * @param <T> The type of interpolator.
 */
public abstract class BaseInterpolator<T> {
	protected boolean enabled;
	protected long desiredDuration;
	protected long elapsedTime;
	protected T startValue;
	protected T endValue;
	protected T interpolatedValue;
	protected boolean repeat;
	protected IInterpolatorListener listener;

	/**
	 * Create a new interpolator.
	 */
	public BaseInterpolator() {
		this.enabled = false;
		this.elapsedTime = 0;
		this.desiredDuration = 0;
		this.startValue = null;
		this.endValue = null;
		this.interpolatedValue = null;
		this.repeat = false;
		this.listener = null;
	}

	/**
	 * Start a new interpolation
	 * @param startValue Start value 
	 * @param endValue End value 
	 * @param desiredDuration Desired duration
	 * @param force Force the interpolator to start.
	 */
	public void startInterpolation(T startValue, T endValue, long desiredDuration, boolean force) {
		if (!this.enabled || force) {
			this.startValue = startValue;
			this.endValue = endValue;
			this.desiredDuration = desiredDuration;
			this.elapsedTime = 0;
			this.interpolatedValue = startValue;
			this.enabled = true;
			if (this.listener != null) {
				this.listener.onStart();
			}
		}
	}

	/**
	 * Start a new interpolation
	 * @param startValue Start value 
	 * @param endValue End value 
	 * @param desiredDuration Desired duration
	 */
	public void startInterpolation(T startValue, T endValue, long desiredDuration) {
		this.startInterpolation(startValue, endValue, desiredDuration, false);
	}

	/**
	 * Update and get the interpolated value.
	 * @param The time elapsed since last call.
	 * @return The interpolated value.
	 */
	public void getInterpolatedValue(long elapsedTime) {
		if (this.enabled) {
			this.elapsedTime += elapsedTime;

			if (this.elapsedTime >= this.desiredDuration) {
				if (this.repeat) {
					this.enabled = true;
					this.elapsedTime = 0;
					if (this.listener != null) {
						this.listener.onRestart();
					}
				}
			}
			else {
				this.enabled = false;
				this.elapsedTime = this.desiredDuration;
				if (this.listener != null) {
					this.listener.onFinish();
				}
			}
		}

		float step = (float)(this.elapsedTime / (float)this.desiredDuration);
		this.interpolateValue(step);
	}

	/**
	 * Interpolate the value.
	 * @param step The step of interpolation.
	 */
	protected abstract void interpolateValue(float step);
}
