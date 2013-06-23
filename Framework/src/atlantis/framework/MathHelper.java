// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * A class for helping in math operations.
 * @author Yannick
 *
 */
public class MathHelper {
	public static double Pi = Math.PI;
	public static double PiOver2 = Math.PI / 2;
	public static double PiOver4 = Math.PI / 4;
	public static double TwoPi = Math.PI * Math.PI;
	
	/**
	 * Restricts a value to be within a specified range.
	 * @param value
	 * @param min The minimum desired value
	 * @param max The maximum desired value
	 * @return Return a value between min and max
	 */
	public static float clamp(float value, float min, float max) {
		value = (value > max) ? max : value;
		value = (value < min) ? min : value;
		return value;
	}
	
	/**
	 * Restricts a value to be within a specified range.
	 * @param value
	 * @param min The minimum desired value
	 * @param max The maximum desired value
	 * @return Return a value between min and max
	 */
	public static int clamp(int value, int min, int max) {
		return (int)clamp((float)value, (float)min, (float)max);
	}
	
	/**
	 * Get the distance between two values.
	 * @param value1
	 * @param value2
	 * @return Return the distance between value1 and value2
	 */
	public static float distance(float value1, float value2) {
		return Math.abs(value1 - value2);
	}
	
	/**
	 * Get the distance between two values.
	 * @param value1
	 * @param value2
	 * @return Return the distance between value1 and value2
	 */
	public static int distance(int value1, int value2) {
		return (int)distance((float)value1, (float)value2);
	}
	
	/**
	 * Interpolates between value1 and value2 by amount.
	 * @param value1
	 * @param value2
	 * @param amount a value between 0 and 1.
	 * @return An interpolated value.
	 */
	public static float lerp(float value1, float value2, float amount) {
		return value1 + (value2 - value1) * amount;
	}
	
	/**
	 * Convert a radians value to degrees
	 * @param radians
	 * @return Returns the converted value in degrees.
	 */
	public static double toDegrees (double radians) {
		return (radians * (180 / Math.PI));
	}
	
	/**
	 * Convert a degrees value to radians
	 * @param degrees
	 * @return Returns the converted value in radians.
	 */
	public static double toRadians(double degrees) {
		return (degrees * (Math.PI / 180));
	}
	
	/**
	 * Determine if the value is power of two
	 * @param value to test
	 * @return Return true if the value is power of two, otherwise return false.
	 */
	public static boolean isPowerOfTwo(int value) {
		return (value > 0) && ((value & (value - 1)) == 0);
	}
}
