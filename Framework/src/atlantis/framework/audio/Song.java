package atlantis.framework.audio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.easyogg.OggClip;

/**
 * A class for song, it play music in ogg format.
 * @author Yannick
 */
public class Song {
	private OggClip clip;
	
	public Song(String path) {
		try {
			this.clip = new OggClip(new FileInputStream(path));
		} 
		catch (FileNotFoundException e) {
			System.out.println("[Song] Can't load " + path + "file !");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		this.play(false);
	}
	
	public void play(boolean loop) {
		if (loop) {
			this.clip.loop();
		}
		else {
			this.clip.play();
		}
	}
	
	public void stop() {
		this.clip.stop();
	}
	
	public void resume() {
		this.clip.resume();
	}
	
	public void pause() {
		this.clip.pause();		
	}
}
