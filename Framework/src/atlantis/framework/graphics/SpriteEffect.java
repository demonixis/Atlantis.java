// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.graphics;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;

public final class SpriteEffect {
	public static final int None = 0;
	public static final int FlipHorizontaly = 1;
	public static final int FlipVerticaly = 2;
	
	public static DataBuffer flip(BufferedImage image, int flip) {
		int fx = flip == FlipHorizontaly ? -1 : 1;
		int fy = flip == FlipVerticaly ? -1 : 1;
		
		AffineTransform transform = AffineTransform.getScaleInstance(fx, fy);
		AffineTransformOp operation = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		BufferedImage flippedImage = operation.filter(image, null);
		
		return flippedImage.getRaster().getDataBuffer();
	}
}