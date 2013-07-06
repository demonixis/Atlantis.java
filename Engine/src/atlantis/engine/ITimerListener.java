// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine;

/**
 * An interface to work with Timer.
 * @author Yannick
 */
public interface ITimerListener {
	/**
	 * Triggered when the timer is completed.
	 */
	void onCompleted();
	
	/**
	 * Triggered when the timer is completed and restart.
	 */
	void onRestart();
}
