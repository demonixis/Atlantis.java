package atlantis.test;

import atlantis.framework.Matrix;
import atlantis.framework.Vector3;

public class Vector3Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Vector3 v1 = new Vector3();
		Vector3 v2 = new Vector3(45, 15, 35);
		Vector3 v3 = new Vector3(55, -15, 0.5f);
		Vector3 v4 = new Vector3(0, 0, 15);
		
		float [] values = {
			12, 0, 0, 0,
			0, 12, 0, 45,
			0, 0, 12, 0,
			25, -15, 17, 1
		};
		
		Matrix transformMatrix = new Matrix(values);
		
		P("Subtract", Vector3.subtract(v1, v2));
		P("Normalize", Vector3.normalize(v3));
		P("Cross", Vector3.cross(v3, v4));
		P("Distance", Vector3.distance(v1, v4));
		P("Dot", Vector3.dot(v3, v4));
		P("Transform", Vector3.transform(v2, transformMatrix));
	}

	public static void P(String title, Object o) {
		System.out.println("# " + title);
		System.out.println(o.toString());
	}
}
