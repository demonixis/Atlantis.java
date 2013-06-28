// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

import java.awt.Graphics;

import atlantis.framework.content.ContentManager;
import atlantis.framework.input.KeyboardManager;
import atlantis.framework.input.MouseManager;
import atlantis.framework.platform.GameWindow;
import atlantis.framework.platform.IGameWindow;

/**
 * The Game class provide basic initialization and game logic. 
 * It implements GameState pattern.
 * @author Yannick
 */
public class Game implements IDrawable, IUpdateable {
	protected KeyboardManager keyboardManager;
	protected MouseManager mouseManager;
	protected GameTime gameTime;
	protected IGameWindow gameWindow;
	protected GameComponentCollection components;
	protected ContentManager content;
	
	protected int width;
	protected int height;
	protected boolean isRunning;
	protected boolean initialized;
	protected Thread gameThread;
	
	public Game(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.keyboardManager = new KeyboardManager();
		this.mouseManager = new MouseManager();
		this.gameTime = new GameTime();
		this.components = new GameComponentCollection();
		this.content = new ContentManager();
		this.initialized = false;
		
		// The window container
		GameWindow window = new GameWindow(width, height, title);
		window.addKeyListener((KeyboardManager)this.keyboardManager);
		window.addMouseListener((MouseManager)this.mouseManager);
		window.addMouseMotionListener((MouseManager)this.mouseManager);
		window.addMouseWheelListener((MouseManager)this.mouseManager);
		window.getRenderer().addDrawable(this);
		window.getRenderer().addDrawable(this.components);
		this.gameWindow = window;

		// Thread for rendering
		this.gameThread = new Thread(new GameLoop(this));
	}
	
	/**
	 * Launch the main game loop and start the game
	 */
	public void run() {
		if (!this.isRunning) {
			this.isRunning = true;
			
			this.loadContent();this.initialize();
			this.initialized = true;
			this.gameThread.start();
		}
	}
	
	/**
	 * Game initialization
	 */
	protected void initialize() {
		this.components.initialize();
	}
	
	/**
	 * Load asset from content manager
	 */
	protected void loadContent() {
		this.components.loadContent();
	}
	
	/**
	 * Unload content 
	 */
	protected void unloadContent() {
		this.components.unloadContent();
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
	public void draw(Graphics graphics) { 
		this.components.draw(graphics);
	}
	
	/**
	 * Exit the game
	 */
	public void exit() {
		System.exit(0);
	}
	
	public void setWindow(IGameWindow window) {
		this.gameWindow = window;
	}
	
	public IGameWindow getGameWindow() {
		return this.gameWindow;
	}
	
	/**
	 * Toggle on fullscreen mode
	 */
	public void toggleFullscreen() {
		this.gameWindow.toggleFullscreen();
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
	public KeyboardManager getKeyboardManager() {
		return this.keyboardManager;
	}
	
	/**
	 * Retrieve the mouse state
	 * @return A MouseState object
	 */
	public MouseManager getMouseManager() {
		return this.mouseManager;
	}
	
	/**
	 * Retrieve the content manager
	 * @return The content manager
	 */
	public ContentManager getContentManager() {
		return this.content;
	}
}
