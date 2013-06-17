package atlantis.engine.graphics3d;

import atlantis.framework.Vector3;

/**
 * A camera is used to view a 3D scene.
 * @author Yannick
 *
 */
public class Camera {
	public Vector3 position;
	public Vector3 rotation;
	public Vector3 target;
	
	/**
	 * Create a camera with a position of (0, 0, 10).
	 */
	public Camera() {
		this.position = new Vector3(0.0f, 0.0f, 10.0f);
		this.rotation = new Vector3();
		this.target = new Vector3();
	}
}
