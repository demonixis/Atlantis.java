// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d.importer.babylonjs;

/**
 * Define a camera in a babylon scene.
 * @author Yannick
 */
public class BabylonCamera {
	public String name;
    public String id;
    public float[] position;
    public float[] target;
    public float fov;
    public float minZ;
    public float maxZ;
    public float speed;
    public float inertia;
    public boolean checkCollisions;
    public boolean applyGravity;
    public float[] ellipsoid;
}
