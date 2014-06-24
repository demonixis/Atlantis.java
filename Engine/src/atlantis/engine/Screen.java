package atlantis.engine;

public class Screen {
	private static int screenWidth;
	private static int screenHeight;
	private static int widthPerTwo;
	private static int heightPerTwo;
	
	public static void setup(int width, int height) {
		screenWidth = width;
		screenHeight = height;
		widthPerTwo = width / 2;
		heightPerTwo = height / 2;
	}
	
	public static int getWidth() {
		return screenWidth;
	}
	
	public static int getHeight() {
		return screenHeight;
	}
	
	public static int getWidthPerTwo() {
		return widthPerTwo;
	}
	
	public static int getHeightPerTwo() {
		return heightPerTwo;
	}
}
