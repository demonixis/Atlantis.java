package atlantis.engine.interpolation;

public abstract class BaseInterpolator<T> {
	protected boolean enabled;
	protected long desiredDuration;
	protected long elapsedTime;
	protected T startValue;
	protected T endValue;
	protected T interpolatedValue;
	protected boolean repeat;
	protected IInterpolatorListener listener;
	
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
	
	public void startInterpolation(T startValue, T endValue, long desiredDuration) {
		this.startInterpolation(startValue, endValue, desiredDuration, false);
	}
	
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
	
	protected abstract void interpolateValue(float step);
}
