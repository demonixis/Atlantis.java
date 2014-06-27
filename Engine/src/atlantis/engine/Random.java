package atlantis.engine;

import java.awt.Color;
import atlantis.framework.Point;
import atlantis.framework.Vector2;
import atlantis.framework.Vector3;
import atlantis.framework.Vector4;

/**
 * A class for generating random values
 * @author Yannick
 */
public final class Random {
	private static final java.util.Random random = new java.util.Random();
	
	/**
	 * Reset the random machine and sets a new seed.
	 * @param seed The new seed to use.
	 */
	public static void reset(long seed) {
		random.setSeed(seed);
	}
	
	public static float value() {
		return range(Float.MIN_VALUE, Float.MAX_VALUE);
	}
	
	/**
	 * Gets a random float value between min and max.
	 * @param min Min value
	 * @param max Max value
	 * @return A random float value.
	 */
	public static float range(float min, float max) {
		return random.nextFloat() * (max - min) + min;
	}
	
	/**
	 * Gets a random integer value between min and max.
	 * @param min Min value
	 * @param max Max value
	 * @return A random integer value.
	 */
	public static int range(int min, int max) {
		return random.nextInt() * (max - min) + min;
	}

	/**
	 * Gets a random Point.
	 * @param minX Min X value.
	 * @param minY Min Y value.
	 * @param maxX Max X value.
	 * @param maxY Max Y value.
	 * @return A random Point.
	 */
	public static Point point(int minX, int minY, int maxX, int maxY) {
		return new Point(range(minX, maxX), range(minY, maxY));
	}

	/**
	 * Gets a random point.
	 * @return A random point.
	 * */
	public static Point point() {
		return point(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * Gets a random Vector2.
	 * @param minX Min X value.
	 * @param minY Min Y value.
	 * @param maxX Max X value.
	 * @param maxY Max Y value.
	 * @return A random Vector2.
	 */
	public static Vector2 vector2(float minX, float minY, float maxX, float maxY) {
		return new Vector2(range(minX, maxX), range(minY, maxY));
	}

	/**
	 * Gets a random Vector2.
	 * @return A random Vector2 value.
	 * */
	public static Vector2 vector2() {
		return vector2(Float.MIN_VALUE, Float.MIN_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
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
	public static Vector3 vector3(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
		return new Vector3(range(minX, maxX), range(minY, maxY), range(minZ, maxZ));
	}

	/**
	 * Gets a random Vector3.
	 * @return A random Vector3.
	 */
	public static Vector3 vector3() {
		return vector3(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
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
	public static Vector4 vector4(float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
		return new Vector4(range(minX, maxX), range(minY, maxY), range(minZ, maxZ), range(minW, maxW));
	}

	/**
	 * Gets a random Vector4.
	 * @return A random Vector4.
	 */
	public static Vector4 vector4() {
		return vector4(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);   
	}

	/**
	 * Gets a random color.
	 * @param alpha Alpha amount
	 * @return A random color.
	 * */
	public static Color getColor(float alpha) {
		return new Color(range(0.0f, 1.0f), range(0.0f, 1.0f), range(0.0f, 1.0f), alpha);
	}

	/**
	 * Gets a random color with an alpha set to 1.0f;
	 * @return A random color.
	 */
	public static Color getColor() {
		return getColor(1.0f);
	}
	
	public static Object randomArrayItem(Object[] objects) {
		if (objects.length > 0) {
			return objects[range(0, objects.length - 1)];
		}
		
		return null;
	}
}
