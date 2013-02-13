package atlantis.framework;

import java.awt.Graphics;

import atlantis.framework.content.ContentManager;
import atlantis.framework.input.KeyboardManager;
import atlantis.framework.input.MouseManager;
import atlantis.framework.platform.IGamePlatform;
import atlantis.framework.platform.JPanelRenderer;

/**
 * The Game class provide basic initialization and game logic. 
 * It implements GameState pattern.
 * @author Yannick
 *
 */
public abstract class BaseGame implements IDrawable, IUpdateable {
	protected KeyboardManager keyboardState;
	protected MouseManager mouseState;
	protected GameTime gameTime;
	protected IGamePlatform window;
	protected JPanelRenderer renderer;
	protected GameComponentCollection components;
	protected ContentManager content;
	
	protected int width;
	protected int height;
	protected boolean isRunning;
	protected boolean initialized;
	protected boolean assetsLoaded;
	protected Thread gameThread;
	
	public BaseGame(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.keyboardState = new KeyboardManager();
		this.mouseState = new MouseManager();
		this.gameTime = new GameTime();
		this.components = new GameComponentCollection();
		this.content = new ContentManager();
		
		this.initialized = false;
		this.assetsLoaded = false;
		
		// The renderer
		this.renderer = null;

		// Thread for rendering
		this.gameThread = new Thread(new GameLoop(this));
	}
	
	/**
	 * Launch the main game loop and start the game
	 */
	public void run() {
		if (!this.isRunning) {
			this.loadContent();
			this.initialize();
			this.gameThread.start();
			this.isRunning = true;
		}
	}
	
	/**
	 * Game initialization
	 */
	protected void initialize() {
		this.components.initialize();
		this.initialized = true;
	}
	
	/**
	 * Load asset from content manager
	 */
	protected void loadContent() {
		this.components.loadContent();
		this.assetsLoaded = true;
	}
	
	/**
	 * Unload content 
	 */
	protected void unloadContent() {
		this.components.unloadContent();
		this.assetsLoaded = false;
	}
	
	/**
	 * Update game logic
	 */
	public void update(GameTime gameTime) {
		this.components.update(gameTime);
	}
	
	/**
	 * Draw game graphics
	 */
	public void draw(Graphics graphics) { }
	
	/**
	 * Exit the game
	 */
	public void exit() {
		System.exit(0);
	}
	
	public void setWindow(IGamePlatform window) {
		this.window = window;
	}
	
	/**
	 * Toggle on fullscreen mode
	 */
	public void toggleFullscreen() {
		this.window.toggleFullscreen();
	}
	
	/**
	 * Add a component to the components collection
	 * @param component
	 */
	public void addComponent(GameComponent component) {
		if (this.initialized) {
			if (component instanceof DrawableGameComponent) {
				((DrawableGameComponent)component).loadContent();
				((DrawableGameComponent)component).initialize();
			}
		}
		this.components.add(component);
	}
	
	/**
	 * Gets the renderer
	 * @return
	 */
	public JPanelRenderer getRenderer() {
		return this.renderer;
	}
	
	/**
	 * Retrieve the game components
	 * @return Game components
	 */
	public GameComponentCollection getComponents() {
		return components;
	}
	
	/**
	 * Retrieve the keyboard state
	 * @return A KeyboardState object
	 */
	public KeyboardManager getKeyboardState() {
		return this.keyboardState;
	}
	
	/**
	 * Retrieve the mouse state
	 * @return A MouseState object
	 */
	public MouseManager getMouseState() {
		return this.mouseState;
	}
	
	/**
	 * Retrieve the content manager
	 * @return The content manager
	 */
	public ContentManager getContentManager() {
		return this.content;
	}
}
