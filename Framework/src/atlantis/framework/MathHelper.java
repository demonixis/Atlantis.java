package atlantis.framework;

public class MathHelper {
	public static double Pi = Math.PI;
	public static double PiOver2 = Math.PI / 2;
	public static double PiOver4 = Math.PI / 4;
	public static double TwoPi = Math.PI * Math.PI;
	
	public static float clamp(float value, float min, float max) {
		value = (value > max) ? max : value;
		value = (value < min) ? min : value;
		return value;
	}
	
	public static int clamp(int value, int min, int max) {
		return (int)clamp((float)value, (float)min, (float)max);
	}
	
	public static float distance(float value1, float value2) {
		return Math.abs(value1 - value2);
	}
	
	public static int distance(int value1, int value2) {
		return (int)distance((float)value1, (float)value2);
	}
	
	public static float lerp(float value1, float value2, float amount) {
		return value1 + (value2 - value1) * amount;
	}
	
	public static double toDegress (double radians) {
		return (radians * (180 / Math.PI));
	}
	
	public static double toRadians(double degrees) {
		return (degrees * (Math.PI / 180));
	}
	
	public static boolean isPowerOfTwo(int value) {
		return (value > 0) && ((value & (value - 1)) == 0);
	}
}
