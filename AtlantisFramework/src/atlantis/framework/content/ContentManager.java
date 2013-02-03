package atlantis.framework.content;

import java.util.HashMap;
import atlantis.framework.audio.SoundEffect;
import atlantis.framework.graphics.Texture2D;

public class ContentManager {
	protected HashMap<String, Object> assets;
	protected String rootDirectory;
	
	public ContentManager() {
		this.assets = new HashMap<String, Object>();
		this.rootDirectory = "Content";
	}
	
	/**
	 * Load an image from the content folder. If the image isn't already loaded
	 * it is loaded and added to the collection of assets.
	 * @param assetName
	 * @return an image
	 */
	public Texture2D loadImage(String assetName) {
		Texture2D image;
		
		image = (Texture2D) this.assets.get(assetName);
		if (image == null) {
			image = new Texture2D(this.rootDirectory + "/" + assetName);
			this.assets.put(assetName, image);
		}
		
		return image;
	}
	
	/**
	 * Load an sound from the content folder. If the sound isn't already loaded
	 * it is loaded and added to the collection of assets.
	 * @param assetName
	 * @return a sound
	 */
	public SoundEffect loadSound(String assetName) {
		SoundEffect sound = null;
		
		sound = (SoundEffect) this.assets.get(assetName);
		
		if (sound == null) {
			sound = new SoundEffect(this.rootDirectory + "/" + assetName);
			this.assets.put(assetName, sound);
		}
		
		return sound;
	}
	
	public void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}
	
	public String getRootDirectory() {
		return this.rootDirectory;
	}
}
