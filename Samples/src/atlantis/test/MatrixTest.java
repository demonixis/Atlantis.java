package atlantis.test;

import atlantis.framework.MathHelper;
import atlantis.framework.Matrix;
import atlantis.framework.Vector3;

public class MatrixTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		float[] value1 = {
			1, 1, 1, 0,
			0, 0, 1, 0,
			1, 2, 1, 0,
			2, 1, 2, 0
		};
		
		float[] value2 = {
			1, 5, 9, 3,
			2, 6, 0, 4,
			3, 7, 1, 5,
			4, 8, 2, 6
		};
		
		float[] value3 = {
			1, 1, 1, 1,
			0, 0, 0, 0,
			2, 2, 0, 0,
			2, 2, 0, 0
		};
		
		float[] value4 = {
			1, 9, 0, 5,
			0, 9, 0, 5,
			7, 6, 0, 0,
			6, 7, 0, 1
		};
		
		Matrix m1 = new Matrix(value1);
		Matrix m2 = new Matrix(value2);
		Matrix m3 = new Matrix(value3);
		Matrix m4 = new Matrix(value4);
		
		P("Multiply", Matrix.multiply(m1, m2));
		m1.multiply(m2);
		P("Instance multiply", m1);
		P("Translation", Matrix.createTranslation(new Vector3(15, 0, -15)));
		P("RotationX", Matrix.createRotationX((float)MathHelper.toRadians(90)));
		P("RotationY", Matrix.createRotationY((float)MathHelper.toRadians(90)));
		P("RotationZ", Matrix.createRotationZ((float)MathHelper.toRadians(90)));
		P("Scale", Matrix.createScale(new Vector3(3.45f)));
		P("Three Multiplications", Matrix.multiply(m2, m3, m4));
		P("----------", "----------");
		P("PerspectifFOV", Matrix.createPerspectiveFieldOfView((float)(Math.PI / 4), 1.33f, 0.1f, 1.0f));
		P("LookAt", Matrix.createLookAt(new Vector3(25, 0, 0), new Vector3(), Vector3.getUnitY()));
	}

	public static void P(String title, Object o) {
		System.out.println("# " + title);
		System.out.println(o.toString());
	}
}
