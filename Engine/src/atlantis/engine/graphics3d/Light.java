// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d;

import atlantis.framework.Vector3;

public class Light extends Object3D {
	public boolean enableFlatShading;

    public Light(float x, float y, float z) {
        this.position.set(x, y, z);
        this.enableFlatShading = false;
    }

    public Vector3 normalize() {
        return Vector3.normalize(this.position);
    }

	public final boolean isEnableFlatShading() {
		return enableFlatShading;
	}

	public final void setEnableFlatShading(boolean enableFlatShading) {
		this.enableFlatShading = enableFlatShading;
	}
}
