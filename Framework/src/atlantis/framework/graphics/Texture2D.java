// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import atlantis.framework.IDisposable;

/**
 * A texture 2D
 * @author Yannick
 */
public class Texture2D implements IDisposable {
	protected BufferedImage texture;
	
	/**
	 * Create a texture with a BufferedImage.
	 * @param texture Image to use.
	 */
	public Texture2D(BufferedImage texture) {
		this.texture = texture;
	}
	
	/**
	 * Create a Texture2D, it is loaded from an external folder
	 * @param path the path of the image
	 */
	public Texture2D(String path) {
		this(path, 1);
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
				URL url = this.getClass().getClassLoader().getResource(path);
				image = ImageIO.read(url); 
			}
			else {
				image = ImageIO.read(new File(path));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}
	
	/**
	 * Gets the raw texture.
	 * @return
	 */
	public BufferedImage getTexture() {
		return this.texture;
	}
	
	/**
	 * Remove this texture.
	 */
	public void dispose() {
		this.texture = null;
	}
	
	/**
	 * Gets the width of the texture.
	 * @return Return the width of the texture.
	 */
	public int getWidth() {
		return texture.getWidth();
	}
	
	/**
	 * Gets the height of the texture.
	 * @return Return the height of the texture.
	 */
	public int getHeight() {
		return texture.getHeight();
	}
	
	/**
	 * Gets data of the texture.
	 * @return Return an array that contains the structure of the texture;
	 */
	public int[] getData() {
		DataBuffer buffer = this.texture.getRaster().getDataBuffer();
		int [] data = new int[buffer.getSize()];
		
		for (int i = 0, l = buffer.getSize(); i < l; i++) {
			data[i] = buffer.getElem(i);
		}
		
		return data;
	}
	
	/**
	 * Sets data into the texture.
	 * @param data An array of data to inject.
	 * @throws Exception The array of data must have the same size of texture data.
	 */
	public boolean setData(int[] data) throws Exception {
		DataBuffer buffer = this.texture.getRaster().getDataBuffer();
		if (data.length == buffer.getSize()) {
			for (int i = 0, l = buffer.getSize(); i < l; i++) {
				buffer.setElem(i, data[i]);
			}
			return true;
		}
		else {
			throw new Exception("The data array must have the same size of the texture data");
		}
	}
	
	/**
	 * Gets the size of the data array.
	 * @return Return the size of the buffer array.
	 */
	public int getDataSize() {
		return this.texture.getRaster().getDataBuffer().getSize();
	}
	
	public int getSurfaceType() {
		return this.texture.getType();
	}
}
