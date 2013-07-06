// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine;

import atlantis.framework.GameTime;
import atlantis.framework.IUpdateable;

/**
 * A timer class
 * @author Yannick
 */
public class Timer implements IUpdateable {
	protected int interval;
	protected int repeat;
	protected int counter;
	protected long elapsedTime;
	protected boolean enabled;
	protected ITimerListener completedListener;
	
	public Timer(int interval) {
		this.interval = interval;
		this.repeat = -1;
		this.elapsedTime = 0;
		this.counter = this.repeat;
		this.enabled = false;
		this.completedListener = null;
	}
	
	public Timer(int interval, int repeat) {
		this(interval);
		this.repeat = repeat;
		this.counter = this.repeat;
	}
	
	public void addTimerCompletedListener(ITimerListener listener) {
		this.completedListener = listener;
	}
	
	public void start() {
		this.enabled = true;
	}
	
	public void restart() {
		this.enabled = true;
		this.counter = 0;
		this.elapsedTime = 0;
	}
	
	public void pause() {
		this.enabled = false;
	}
	
	public void resume() {
		this.enabled = true;
	}
	
	public void stop() {
		this.enabled = false;
		this.counter = 0;
		this.elapsedTime = 0;
	}
	
	@Override
	public void update(GameTime gameTime) {
		if (this.enabled) { 
			this.elapsedTime += gameTime.getElapsedTime();
			
			if (this.elapsedTime >= this.interval) {
				if (this.repeat != -1) {
					if (this.counter == 0) {
						this.enabled = false;
					}
					else {
						this.counter--;
						if (this.completedListener != null) {
							this.completedListener.onRestart();
						}
					}
				}
				this.elapsedTime = 0;
				if (this.completedListener != null) {
					this.completedListener.onCompleted();
				}
			}
		}
	}
	
	public long getTimeRemaining() {
		if (this.elapsedTime == 0) {
			return 0;
		}
		else {
			return (long)(this.interval - this.elapsedTime);
		}
	}

	public int getInterval() {
		return interval;
	}

	public int getRepeat() {
		return repeat;
	}

	public int getCounter() {
		return counter;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
