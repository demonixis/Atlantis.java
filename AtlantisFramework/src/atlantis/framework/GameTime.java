package atlantis.framework;

/**
 * Represents the time that elapses during the game
 * @author Yannick
 */
public class GameTime {
	private long currentTime;
	protected long elapsedTime;
	protected long totalGameTime;
	
	public GameTime() {
		this.currentTime = System.currentTimeMillis();
		this.elapsedTime = 0;
		this.totalGameTime = 0;
	}
	
	/**
	 * Update elapsed time
	 */
	public void update() {
		long now = System.currentTimeMillis();
		this.elapsedTime = now - this.currentTime;
		this.totalGameTime += this.elapsedTime;
		this.currentTime = now;
	}

	/**
	 * Get the elapsed time since last call
	 * @return 
	 */
	public long getElapsedTime() {
		return (long)(this.elapsedTime * 0.01f);
	}

	/**
	 * Get the total game time
	 * @return
	 */
	public long getTotalGameTime() {
		return (long)(this.totalGameTime * 0.01f);
	}
}
