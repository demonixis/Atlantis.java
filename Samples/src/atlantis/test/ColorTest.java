package atlantis.test;

import java.awt.Color;

public class ColorTest {
	public static void main(String[] args) {
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
