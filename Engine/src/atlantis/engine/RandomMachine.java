package atlantis.engine;

import java.awt.Color;
import java.util.Random;

import atlantis.framework.Point;
import atlantis.framework.Rectangle;
import atlantis.framework.Vector2;
import atlantis.framework.Vector3;
import atlantis.framework.Vector4;

/**
 * A class for generating random values
 * @author Yannick
 */
public final class RandomMachine {
	private static final Random random = new Random();
	
	/**
	 * Reset the random machine and sets a new seed.
	 * @param seed The new seed to use.
	 */
	public static void resetRandomMachine(long seed) {
		random.setSeed(seed);
	}

	/**
	 * Gets a random float value between min and max.
	 * @param min Min value
	 * @param max Max value
	 * @return A random float value.
	 */
	public static float getFloat(float min, float max) {
		return (float)(random.nextDouble() * (max - min) + min);
	}

	/**
	 * Gets a random float value between Float.MIN_VALUE and Float.MAX_VALUE.
	 * @return A random float value.
	 */
	public static float getFloat() {
		return getFloat(Float.MIN_VALUE, Float.MAX_VALUE);
	}

	/**
	 * Gets a random integer value between min and max.
	 * @param min Min value
	 * @param max Max value
	 * @return An random integer value.
	 */
	public static int getInteger(int min, int max) {
		return random.nextInt(max) + min;
	}

	/**
	 * Gets a random integer value between Integer.MIN_VALUE and Integer.MAX_VALUE.
	 * @return A random integer value.
	 */
	public static int getInteger() {
		return getInteger(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * Gets a random Point.
	 * @param minX Min X value.
	 * @param minY Min Y value.
	 * @param maxX Max X value.
	 * @param maxY Max Y value.
	 * @return A random Point.
	 */
	public static Point getPoint(int minX, int minY, int maxX, int maxY) {
		return new Point(getInteger(minX, maxX), getInteger(minY, maxY));
	}

	/**
	 * Gets a random point.
	 * @return A random point.
	 * */
	public static Point getPoint() {
		return getPoint(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * Gets a random Vector2.
	 * @param minX Min X value.
	 * @param minY Min Y value.
	 * @param maxX Max X value.
	 * @param maxY Max Y value.
	 * @return A random Vector2.
	 */
	public static Vector2 getVector2(float minX, float minY, float maxX, float maxY) {
		return new Vector2(
				getFloat(minX, maxX),
				getFloat(minY, maxY));
	}

	/**
	 * Gets a random Vector2.
	 * @return A random Vector2 value.
	 * */
	public static Vector2 getVector2() {
		return getVector2(Float.MIN_VALUE, Float.MIN_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
	}

	/**
	 * Gets a random Vector3.
	 * @param minX Min X value.
	 * @param minY Min Y value.
	 * @param minZ Min Z value.
	 * @param maxX Max X value.
	 * @param maxY Max Y value.
	 * @param maxZ Max Z value.
	 * @return A random Vector3.
	 * @return 
	 */
	public static Vector3 getVector3(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
		return new Vector3(getFloat(minX, maxX), getFloat(minY, maxY), getFloat(minZ, maxZ));
	}

	/**
	 * Gets a random Vector3.
	 * @return A random Vector3.
	 */
	public static Vector3 getVector3() {
		return getVector3(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
	}

	/**
	 * Gets a random Vector4.
	 *
	 * @param minX Min X value.
	 * @param minY Min Y value.
	 * @param minZ Min Z value.
	 * @param minW Min W value.
	 * @param maxX Max X value.
	 * @param maxY Max Y value.
	 * @param maxZ Max Z value.
	 * @param maxW Max W value.
	 * @return A random Vector4.
	 */
	public static Vector4 getVector4(float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
		return new Vector4(getFloat(minX, maxX), getFloat(minY, maxY), getFloat(minZ, maxZ), getFloat(minW, maxW));
	}

	/**
	 * Gets a random Vector4.
	 * @return A random Vector4.
	 */
	public static Vector4 getVector4() {
		return getVector4(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);   
	}

	/**
	 * Gets a random color. Ex: getColor(45, -1, -1, 0) for a random color with a fixed red value and a fixed alpha.
	 * @param "red Red amount
	 * @param "green Green amount
	 * @param "blue Blue amount
	 * @param "alpha Alpha amount
	 * @return A random color.
	 */
	public static Color getColor(float red, float green, float blue, float alpha) {
		int r = (red != -1) ? (int)(red * 256) % 256 : getInteger(0, 255);
		int g = (green != -1) ? (int)(green * 256) % 256 : getInteger(0, 255);
		int b = (blue != -1) ? (int)(blue * 256) % 256 : getInteger(0, 255);
		int a = (alpha != -1) ? (int)(alpha * 256) % 256 : getInteger(0, 255);

		return new Color(r, g, b, a);
	}

	/**
	 * Gets a random color.
	 * @param alpha Alpha amount
	 * @return A random color.
	 * */
	public static Color getColor(float alpha) {
		return new Color(getFloat(0.0f, 1.0f), getFloat(0.0f, 1.0f), getFloat(0.0f, 1.0f), alpha);
	}

	/**
	 * Gets a random color with an alpha set to 1.0f;
	 * @return A random color.
	 */
	public static Color getColor() {
		return getColor(1.0f);
	}

	/**
	 * Gets a random Rectangle.
	 * @return a random Rectangle with random position and random size.
	 */
	public static Rectangle getRectangle() {
		int x = getInteger();
		int y = getInteger();
		int width = getInteger(1, Integer.MAX_VALUE);
		int height = getInteger(1, Integer.MAX_VALUE);

		return new Rectangle(x, y, width, height);
	}

	/**
	 * Gets a random Rectangle.
	 * @param width Rectangle width
	 * @param height Rectangle height
	 * @return A random Rectangle with fixed size.
	 * */
	public static Rectangle getRectangle(int width, int height) {
		return new Rectangle(getInteger(), getInteger(), width, height);
	}
}
