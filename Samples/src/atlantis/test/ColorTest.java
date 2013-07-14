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
		Color tempColor = null;
		for (int y = 0; y < texture.getHeight(); y++) {
			String line = "";
			for (int x = 0; x < texture.getWidth(); x++) {
				tempColor = colors[x + y * texture.getWidth()];
				line += ("[R:" + tempColor.getRed() + " G:" + tempColor.getGreen() + " B:" + tempColor.getBlue() + " A:" + tempColor.getAlpha() + "]");
			}
			System.out.println(line);
		}
		
		Color color = new Color(1.0f, 0.0f, 0.65f, 0.0f);
		Color nColor = colorAddValue(color, 0.75f);
		System.out.println("Color: " + color + " nColor: " + nColor);
	}
	
	public static Color colorAddValue(Color color, float value) {
		float r = (float)color.getRed() / 255.0f;
		float g = (float)color.getGreen() / 255.0f;
		float b = (float)color.getBlue() / 255.0f;
		float a = (float)color.getAlpha() / 255.0f;
		
		float nr = (r * value) % 255;
		float ng = (g * value) % 255;
		float nb = (b * value) % 255;
		float na = (a * value) % 255;
		
		Color nColor = new Color(nr, ng, nb, na);
		
		return nColor;
	}
}
