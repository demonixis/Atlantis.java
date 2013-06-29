package atlantis.framework.platform;

import java.awt.GraphicsDevice;

import javax.swing.JFrame;

public class GameWindow extends JFrame implements IGameWindow {
	private static final long serialVersionUID = -417120026570767131L;
	protected JPanelRenderer renderer;
	protected boolean isFullscreen;


	public GameWindow(int width, int height, String title) {
		this.setTitle(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.isFullscreen = false;
		this.setRenderer(new JPanelRenderer());
	}

	@Override
	public void setRenderer(IWindowRenderer renderer) {
		this.renderer = (JPanelRenderer)renderer;
		this.setContentPane(this.renderer);
	}

	@Override
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

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);


		if (this.renderer != null) {
			this.renderer.setSize(width, height);
		}
	}

	@Override
	public JPanelRenderer getRenderer() {
		return this.renderer;
	}
}
