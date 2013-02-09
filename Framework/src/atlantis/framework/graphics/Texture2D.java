package atlantis.framework.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import atlantis.framework.IDisposable;

/**
 * A texture 2D
 * @author Yannick
 */
public class Texture2D implements IDisposable {
	protected BufferedImage texture;
	
	/**
	 * Create a Texture2D, it is loaded from an external folder
	 * @param path the path of the image
	 */
	public Texture2D(String path) {
		this.texture = this.loadTexture(path, 1);
	}
	
	/**
	 * Create a Texture2D
	 * @param path the path of the image
	 * @param loadType the type of loading, internal = 0 (for applet), external = 1
	 */
	public Texture2D(String path, int loadType) {
		this.texture = this.loadTexture(path, loadType);
	}
	
	/**
	 * Load an image 
	 * @param path the path of the image
	 * @param loadType the type of loading, internal = 0 (for applet), external = 1
	 * @return an image
	 */
	public BufferedImage loadTexture(String path, int loadType) {
		BufferedImage image = null;

		try {
			if (loadType == 0) {
				image = ImageIO.read(this.getClass().getClassLoader().getResource(path)); 
			}
			else {
				image = ImageIO.read(new File(path));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}
	
	public BufferedImage getTexture() {
		return this.texture;
	}
	
	public void dispose() {
		this.texture = null;
	}
	
	public int getWidth() {
		return texture.getWidth();
	}
	
	public int getHeight() {
		return texture.getHeight();
	}
}
