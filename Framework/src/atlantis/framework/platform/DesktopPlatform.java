// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework.platform;

import java.awt.GraphicsDevice;

import javax.swing.JFrame;


/**
 * Define a class for use the desktop platform of the JRE.
 * It use AWT/Swing for rendering.
 * @author Yannick
 *
 */
public class DesktopPlatform extends JFrame implements IGameWindow {
	private static final long serialVersionUID = -417120026570767131L;
	protected JPanelRenderer renderer;
	protected boolean isFullscreen;
	
	public DesktopPlatform(int width, int height, String title) {
		this.setTitle(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.isFullscreen = false;
	}
	
	public void setRenderer(JPanelRenderer renderer) {
		this.renderer = renderer;
		this.setContentPane(this.renderer);
	}
	
	/**
	 * Exit the platfom.
	 */
	public void exit() {
		this.dispose();
	}

	@Override
	public void toggleFullscreen() {
		GraphicsDevice device = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (this.isFullscreen) {
			device.setFullScreenWindow(null);
			this.isFullscreen = false;
		}
		else {
			device.setFullScreenWindow(this);
			this.isFullscreen = true;
		}
	}
	
	/**
	 * Change the size of the window.
	 */
	public void setSize(int width, int height) {
		super.setSize(width, height);
		
		if (this.renderer != null) {
			this.renderer.setSize(width, height);
		}
	}
}
