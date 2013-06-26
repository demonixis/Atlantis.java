// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import atlantis.framework.IDisposable;

/**
 * A sound effect
 * Inspired by this article http://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
 * @author Yannick
 */
public class SoundEffect implements IDisposable {
	protected boolean muted;
	protected Clip clip;
	
	/**
	 * Create a new sound effect. It is loaded from external folder
	 * @param path the file path
	 */
	public SoundEffect(String path) {
		this.clip = this.loadSound(path, 1);
		this.muted = false;
	}
	
	/**
	 * Create a new sound effect
	 * @param path the file path
	 * @param loadType the type of loading, internal = 0 (for applet), external = 1
	 */
	public SoundEffect(String path, int loadType) {
		this.clip = this.loadSound(path, loadType);
		this.muted = false;
	}
	
	/**
	 * Play the sound
	 */
	public void play() {
		if (!this.muted) {
			if (this.clip.isRunning()) {
				this.clip.stop();
			}
			this.clip.setFramePosition(0);
			this.clip.start();
		}
	}
	
	/**
	 * Pause the sound
	 */
	public void pause() {
		this.stop();
	}
	
	/**
	 * Resume the sound if paused
	 */
	public void resume() {
		if (this.clip.isActive()) {
			this.clip.start();
		}
	}
	
	/**
	 * Stop playing the sound
	 */
	public void stop() {
		if (this.clip.isRunning()) {
			this.clip.stop();
		}
	}
	
	/*
	 * Stop and dispose the sound, you must reconstruct it if you want to use again
	 * @see atlantis.framework.IDisposable#dispose()
	 */
	public void dispose() {
		this.stop();
		this.clip.close();
	}
	
	/**
	 * Load a sound
	 * @param path the file path of the sound
	 * @param loadType the type of loading, internal = 0 (for applet), external = 1
	 * @return a sound
	 */
	public Clip loadSound(String path, int loadType) {
		Clip clip = null;
		
		try {
			AudioInputStream audioStream = null;
			
			if (loadType == 0) {
				audioStream = AudioSystem.getAudioInputStream(this.getClass().getResource(path));
			}
			else {
				audioStream = AudioSystem.getAudioInputStream(new File(path));
			}
			
			clip = AudioSystem.getClip();
			clip.open(audioStream);
		}
		catch (UnsupportedAudioFileException ue) {
			ue.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		return clip;
	}
}
