package atlantis.engine;

import atlantis.framework.GameTime;
import atlantis.framework.IUpdateable;

public class Timer implements IUpdateable {
	protected int interval;
	protected int repeat;
	protected int counter;
	protected long elapsedTime;
	protected boolean enabled;
	
	public Timer(int interval) {
		this.interval = interval;
		this.repeat = -1;
		this.elapsedTime = 0;
		this.counter = this.repeat;
		this.enabled = true;
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
				if (this.counter == 0) {
					this.enabled = false;
				}
				else {
					this.counter--;
				}
				this.elapsedTime = 0;
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

}
