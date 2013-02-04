package atlantis.framework.platform;

import java.awt.GraphicsDevice;

import javax.swing.JFrame;

public class DesktopPlatform extends JFrame implements IGamePlatform {
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
	
	public void setSize(int width, int height) {
		super.setSize(width, height);
		
		if (this.renderer != null) {
			this.renderer.setSize(width, height);
		}
	}
}
