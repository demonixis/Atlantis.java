package atlantis.framework;

import java.awt.Graphics;

import atlantis.framework.content.ContentManager;
import atlantis.framework.input.IKeyboardState;
import atlantis.framework.input.IMouseState;
import atlantis.framework.platform.IGamePlatform;
import atlantis.framework.platform.JPanelRenderer;

/**
 * The Game class provide basic initialization and game logic. 
 * It implements GameState pattern.
 * @author Yannick
 *
 */
public abstract class BaseGame implements IDrawable, IUpdateable {
	protected IKeyboardState keyboardState;
	protected IMouseState mouseState;
	protected GameTime gameTime;
	protected IGamePlatform window;
	protected JPanelRenderer renderer;
	protected GameComponentCollection components;
	protected ContentManager content;
	
	protected int width;
	protected int height;
	protected boolean isRunning;
	protected Thread gameThread;
	
	public BaseGame(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.keyboardState = null;
		this.mouseState = null;
		this.gameTime = new GameTime();
		this.components = new GameComponentCollection();
		this.content = new ContentManager();
		
		// The renderer
		this.renderer = null;

		// Thread for rendering
		this.gameThread = new Thread(new MainLoop(this));
	}
	
	private class MainLoop implements Runnable {
		private BaseGame game;
		
		public MainLoop(BaseGame game) {
			this.game = game;
		}
		
		@Override
		public void run() {
			while(isRunning) {
				this.game.gameTime.update();
				
				for (int i = 0; i < 2; i++) {
					this.game.update(this.game.gameTime);
				}
			
				this.game.renderer.repaint();
				
				// TODO : Use a correct value
				try {
					Thread.sleep(10);
				}
				catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public void run() {
		if (!this.isRunning) {
			this.loadContent();
			this.initialize();
			this.gameThread.start();
			this.isRunning = true;
		}
	}
	
	protected void initialize() {
		this.components.initialize();
	}
	
	protected void loadContent() {
		this.components.loadContent();
	}
	
	protected void unloadContent() {
		this.components.unloadContent();
	}
	
	public void update(GameTime gameTime) {
		this.components.update(gameTime);
	}
	
	public void draw(Graphics graphics) {

	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void toggleFullscreen() {
		this.window.toggleFullscreen();
	}
	
	public GameComponentCollection getComponents() {
		return components;
	}

	public void setComponents(GameComponentCollection components) {
		this.components = components;
	}
	
	public IKeyboardState getKeyboard() {
		return this.keyboardState;
	}
	
	public IMouseState getMouse() {
		return this.mouseState;
	}
	
	public ContentManager getContentManager() {
		return this.content;
	}
}
