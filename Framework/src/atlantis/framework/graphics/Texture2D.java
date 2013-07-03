// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * A texture 2D
 * @author Yannick
 */
public class Texture2D extends BufferedImage {
	protected DataBuffer dataBuffer;
	protected int bufferSize;
	
	public Texture2D() {
		this(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
	}
	
	public Texture2D(int width, int height, int type) {
		super(width, height, type);
		this.dataBuffer = this.getRaster().getDataBuffer();
		this.bufferSize = this.dataBuffer.getSize();
		
		for (int i = 0; i < this.bufferSize; i++) {
			this.dataBuffer.setElem(i, 255);
		}
	}
	
	public Texture2D(BufferedImage image) {
		super(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = this.createGraphics();
		graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		graphics.dispose();
	}
	
	public Texture2D(DataBuffer dataBuffer, int width, int height, int type) {
		super(width, height, type);
		this.setData(dataBuffer);
	}
	
	public static Texture2D createFromPath(String path, int loadType) {
		BufferedImage image = loadBufferedImage(path, loadType);
		return createFromImage(image);
	}
	
	public static Texture2D createFromImage(BufferedImage image) {
		Texture2D texture = new Texture2D(image.getWidth(), image.getHeight(), image.getType());
		texture.setData(image.getRaster().getDataBuffer());
		return texture;
	}
	
	/**
	 * Load an image 
	 * @param path the path of the image
	 * @param loadType the type of loading, internal = 0 (for applet), external = 1
	 * @return an image
	 */
	protected static BufferedImage loadBufferedImage(String path, int loadType) {
		BufferedImage image = null;

		try {
			if (loadType == 0) {
				URL url = Texture2D.class.getClass().getClassLoader().getResource(path);
				image = ImageIO.read(url); 
			}
			else {
				image = ImageIO.read(new File(path));
			}
		} catch (IOException e) {
			System.err.println("[Texture2D] Can't load " + path + " asset !");
		}

		return image;
	}

	/**
	 * Gets data of the texture.
	 * @return Return an array that contains the structure of the texture;
	 */
	public int[] getTexData() {
		DataBuffer buffer = this.getRaster().getDataBuffer();
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
	public boolean setData(int[] data) {
		DataBuffer buffer = this.getRaster().getDataBuffer();
		if (data.length == buffer.getSize()) {
			for (int i = 0, l = buffer.getSize(); i < l; i++) {
				buffer.setElem(i, data[i]);
			}
			return true;
		}
		return false;
	}
	
	public boolean setData(DataBuffer buffer) {
		if (buffer.getSize() == this.bufferSize) {
			for (int i = 0; i < this.bufferSize; i++) {
				this.dataBuffer.setElem(i, buffer.getElem(i));
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the size of the data array.
	 * @return Return the size of the buffer array.
	 */
	public int getDataSize() {
		return this.bufferSize;
	}
	
	public int getSurfaceType() {
		return this.getType();
	}
}
