// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d;

import atlantis.framework.Matrix;
import atlantis.framework.Vector3;

/**
 * Define a basic 3d actor who has a position, a rotation and a scale.
 * @author Yannick
 */
public abstract class Object3D {
	protected String name;
    protected Vector3 position = Vector3.Zero();
    protected Vector3 rotation = Vector3.Zero();
    protected Vector3 scale = Vector3.One();

    public void move(float x, float y, float z) {
        this.move(new Vector3(x, y, z));
    }

    public void move(Vector3 move) {
        this.position.x = move.x;
        this.position.y = move.y;
        this.position.z = move.z;
    }

    public void translate(float x, float y, float z) {
        this.translate(new Vector3(x, y, z));
    }

    public void translate(Vector3 translation) {
        Vector3 transform = Vector3.transformCoordinate(translation, Matrix.createRotationY(this.rotation.y));
        this.position.x += transform.x;
        this.position.y += transform.y;
        this.position.z += transform.z;
    }

    public void rotate(float rx, float ry, float rz) {
        this.rotation.x += rx;
        this.rotation.y += ry;
        this.rotation.z += rz;
    }

    public void rotate(Vector3 rotation) {
        this.rotation.add(rotation);
    }
    
    public void scale(float sx, float sy, float sz) {
    	this.scale.x += sx;
    	this.scale.y += sy;
    	this.scale.z += sz;
    }
    
    public void scale(Vector3 scale) {
    	this.scale.add(scale);
    }

    // ---
    // --- Getters and setters
    // ---
    
	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final Vector3 getPosition() {
		return position;
	}

	public final void setPosition(Vector3 position) {
		this.position = position;
	}

	public final Vector3 getRotation() {
		return rotation;
	}

	public final void setRotation(Vector3 rotation) {
		this.rotation = rotation;
	}

	public final Vector3 getScale() {
		return scale;
	}

	public final void setScale(Vector3 scale) {
		this.scale = scale;
	}
}
