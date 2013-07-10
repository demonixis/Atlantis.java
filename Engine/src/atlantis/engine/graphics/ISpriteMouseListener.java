// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics;

public interface ISpriteMouseListener {
	void onMouseClick(int button);
	void onMouseJustClicked(int button);
	void onMouseOver();
	void onMouseOut();
}
