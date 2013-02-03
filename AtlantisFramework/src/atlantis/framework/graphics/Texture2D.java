package atlantis.framework.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import atlantis.framework.IDisposable;

/**
 * A texture 2D
 * @author Yannick
 */
public class Texture2D implements IDisposable {
	protected BufferedImage texture;
	
	public Texture2D(String path) {
		this.texture = this.loadTexture(path);
	}
	
	/**
	 * Load an image 
	 * @param path the path of the image
	 * @return an image
	 */
	public BufferedImage loadTexture(String path) {
		BufferedImage image = null;

		try {
			image = ImageIO.read(new File(path));
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
