package atlantis.framework.content;

import java.util.HashMap;
import atlantis.framework.audio.SoundEffect;
import atlantis.framework.graphics.Texture2D;


public class ContentManager {
	protected HashMap<String, Object> assets;
	protected String rootDirectory;
	protected int loadType;
	
	public ContentManager() {
		this.assets = new HashMap<String, Object>();
		this.rootDirectory = "Content";
		this.loadType = 1;
	}
	
	/**
	 * Load an image from the content folder. If the image isn't already loaded
	 * it is loaded and added to the collection of assets.
	 * @param assetName
	 * @return an image
	 */
	public Texture2D loadTexture(String assetName) {
		Texture2D image;
		
		image = (Texture2D) this.assets.get(assetName);
		if (image == null) {
			image = new Texture2D(this.rootDirectory + "/" + assetName, this.loadType);
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
	
	/**
	 * Get an asset by its type and its name
	 * @param assetName
	 * @return 
	 */
	public <T> T load(String assetName) {
		T asset = null;
		
		asset = (T)this.assets.get(assetName);
		
		if (asset == null) {
			if (asset.getClass().equals(Texture2D.class)) {
				asset = (T) loadTexture(assetName);
			}
			else if (asset.getClass().equals(SoundEffect.class)) {
				asset = (T) loadSound(assetName);
			}
		}
		
		return asset;
	}
	
	public void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}
	
	public String getRootDirectory() {
		return this.rootDirectory;
	}
	
	/**
	 * Sets the load type.
	 * @param loadType the type of loading, internal = 0 (for applet), external = 1
	 */
	public void setLoadType(int loadType) {
		this.loadType = loadType;
	}
}
