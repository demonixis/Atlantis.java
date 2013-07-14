package atlantis.test;

import java.awt.Color;

import atlantis.framework.graphics.Texture2D;

public class ColorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Texture2D texture = Texture2D.createFromPath("Content/background.png", 1);
		Color[] colors = Texture2D.getData(texture);
		Color color = null;
		for (int y = 0; y < texture.getHeight(); y++) {
			String line = "";
			for (int x = 0; x < texture.getWidth(); x++) {
				color = colors[x + y * texture.getWidth()];
				line += ("[R:" + color.getRed() + " G:" + color.getGreen() + " B:" + color.getBlue() + " A:" + color.getAlpha() + "]");
			}
			System.out.println(line);
		}
	}

}
