AtlantisEngine.java
===================

### Summary
Atlantis Engine is an experimental game engine for make 2D games. It's available in Java and JavaScript and the goal is to provide the same API for each language.
The engine is composed of two main modules, the Framework who's close to XNA/MonoGame Framework and the Engine who's close to YnaEngine.

### Some features
* Content manager
* Desktop and Applet 
* Game component system
* Input management (Keyboard, Mouse)
* Sprite, Animated Sprite (Flixel like), Text
* SpriteGroup
* State management
* 3D Software renderer (work in progress but working)

### Example of a 2D demo

```java
public class SpriteGame extends GameDesktop {
	private Entity background;
	private Sprite sprite;
	
	public SpriteGame() {
		super(1024, 600, "Atlantis Game Engine for Java - Sprite Sample");
		// Create the background and add it to the scene.
		background = new Entity("background.png");
		this.scene.add(background);
		
		// Create a sprite and add it to the scene.
		sprite = new Sprite("BRivera-femaleelfwalk.png");
		this.scene.add(sprite);
	}
	
	@Override
	public void loadContent() {
		super.loadContent();
		
		// Entire the background to fill the screen.
		background.setSize(this.width, this.height);
		
		// Add animations to the sprite
		sprite.prepareAnimation(64, 64);
		sprite.addAnimation("up", new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, 75);
		sprite.addAnimation("left", new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17 }, 75);
		sprite.addAnimation("down", new int[] { 18, 19, 20, 21, 22, 23, 24, 25, 26 }, 75);
		sprite.addAnimation("right", new int[] { 27, 28, 29, 30, 31, 32, 33, 34, 35 }, 75);
	    
		// Desired display size
	    sprite.setSize(72, 72); 
		// Sets the position.
	    sprite.setPosition(this.width / 2 - sprite.getWidth() / 2, this.height / 2 - sprite.getHeight() / 2);
		// Desired viewport size.
		sprite.setViewport(0, 0, this.width, this.height);
		// Force to stay in screen.
		sprite.setInsideScreen(true);
	}
	
	@Override
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		// Move the sprite and animate it.
		
		if (Atlantis.keyboard.up()) {
			this.sprite.play("up");
			this.sprite.setY(this.sprite.getY() - 1);
		}
		else if (Atlantis.keyboard.down()) {
			this.sprite.play("down");
			this.sprite.setY(this.sprite.getY() + 1);
		}
	
		// Other tests
		
		if (Atlantis.keyboard.escape()) {
			this.exit();
		}
	}
	
	public static void main(String [] args) {
		SpriteGame game = new SpriteGame();
		game.run();
	}
}
```

### Example of a 3D demo

```java
public class Soft3DRendering extends Game {
	protected Renderer renderer;
	protected Camera camera;
	protected Mesh[] meshes;
	protected KeyboardState keyboardState;
	
	public Soft3DRendering() {
		super(1024, 768, "3D Software demo");
		this.camera = new Camera();
		this.renderer = new Renderer(this.width, this.height);
		this.meshes = new Mesh[2];
	}
	
	@Override
	public void loadContent() {
		super.loadContent();
				
		Mesh cube = new Mesh("cube", new CubeGeometry());
		cube.color = Color.GREEN;
		cube.position = new Vector3(-2.5f, -2.5f, 0);
		meshes[0] = cube;
		
		Mesh pyramid = new Mesh("triangle", new PyramidGeometry());
		pyramid.color = Color.YELLOW;
		pyramid.position = new Vector3(2.5f, -2.5f, 0);
		meshes[1] = pyramid;
	}
	
	@Override
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		keyboardState = this.keyboardManager.getState();
		
		if (keyboardState.isKeyDown(KeyEvent.VK_UP))
            camera.translate(0.0f, 0.0f, -1.0f);
        else if (keyboardState.isKeyDown(KeyEvent.VK_DOWN))
            camera.translate(0.0f, 0.0f, 1.0f);

		// Other tests...
		
		// Rotate meshes
		for (int i = 0, l = meshes.length; i < l; i++) {
			meshes[i].rotation.x += 0.01f;
			meshes[i].rotation.y += 0.01f;
		}
		
		if (keyboardState.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.exit();
		}
	}
	
	@Override
	public void draw(GameTime gameTime) {
		super.draw(gameTime);
		renderer.clear(Color.black);
		renderer.render(this.graphicsDevice().getGraphics(), camera, meshes);
	}

	public static void main(String [] args) {
		Soft3DRendering game = new Soft3DRendering();
		game.run();
	}
}
```

### ThirdParty libraries

JSON in Java by Douglas Crockford https://github.com/douglascrockford/JSON-java
Jorbis by JCraft http://www.jcraft.com/jorbis/
EasyOgg by cokeandcode http://www.cokeandcode.com/index.html?page=libs

### License

Framework and Engine are under MIT License, take a look of LICENSE file for more informations.
Jorbis is under LGPL license
JSON in Java and EasyOgg haven't license.