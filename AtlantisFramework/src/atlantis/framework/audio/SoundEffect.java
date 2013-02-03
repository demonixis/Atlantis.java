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
	
	public SoundEffect(String path) {
		this.clip = this.loadSound(path);
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
	 * @param path
	 * @return a sound
	 */
	public Clip loadSound(String path) {
		Clip clip = null;
		
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
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
