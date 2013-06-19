AtlantisEngine.java
===================

### Summary
Atlantis Engine is an experimental game engine for make 2D games (not that a 3D software renderer is implemented). It's available in Java and JavaScript and the goal is to provide the same API for each language.
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
		this.background = new Entity("background.png");
		this.sprite = new Sprite("BRivera-femaleelfwalk.png");
		this.sprite.setViewport(0, 0, this.width, this.height);
		this.sprite.setInsideScreen(true);
	}
	
	public void loadContent() {
		super.loadContent();
		
		this.background.loadContent(this.content);
		this.background.setSize(this.width, this.height);
		
		// Add animation to the sprite
		this.sprite.loadContent(this.content);
		this.sprite.prepareAnimation(64, 64);
		this.sprite.addAnimation("up", new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, 75);
		this.sprite.addAnimation("left", new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17 }, 75);
		this.sprite.addAnimation("down", new int[] { 18, 19, 20, 21, 22, 23, 24, 25, 26 }, 75);
		this.sprite.addAnimation("right", new int[] { 27, 28, 29, 30, 31, 32, 33, 34, 35 }, 75);
	    
	    this.sprite.setSize(72, 72);
	    this.sprite.setPosition(
	    		this.width / 2 - this.sprite.getWidth() / 2,
	    		this.height / 2 - this.sprite.getHeight() / 2);
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		this.sprite.update(gameTime);
		
		if (this.keyboardState.isKeyDown(KeyEvent.VK_UP)) {
			this.sprite.play("up");
			this.sprite.setY(this.sprite.getY() - 1);
		}
		else if (this.keyboardState.isKeyDown(KeyEvent.VK_DOWN)) {
			this.sprite.play("down");
			this.sprite.setY(this.sprite.getY() + 1);
		}
		
		if (this.keyboardState.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.sprite.play("right");
			this.sprite.setX(this.sprite.getX() + 1);
		}
		else if (this.keyboardState.isKeyDown(KeyEvent.VK_LEFT)) {
			this.sprite.play("left");
			this.sprite.setX(this.sprite.getX() - 1);
		}
		
		if (this.keyboardState.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.exit();
		}
	}
	
	public void draw(Graphics graphics) {
		super.draw(graphics);
		this.background.draw(graphics);
		this.sprite.draw(graphics);
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
	Device device;
	Camera camera;
	Mesh [] meshes;
	BufferedImage frontBuffer;
	
	public Soft3DRendering() {
		super(1024, 768, "Atlantis Game Engine for Java - Software 3D rendering");
		camera = new Camera();
		camera.position = new Vector3(0, 0, 30);
		device = new Device(this.width, this.height);
		frontBuffer = device.getFrontBuffer();
	}
	
	public void loadContent() {
		super.loadContent();
		meshes = new Mesh[2];
				
		Mesh cube = new Mesh("cube", new CubeGeometry());
		cube.color = Color.GREEN;
		cube.position = new Vector3(-2.5f, -2.5f, 0);
		meshes[0] = cube;
		
		Mesh pyramid = new Mesh("triangle", new PyramidGeometry());
		pyramid.color = Color.YELLOW;
		pyramid.position = new Vector3(2.5f, -2.5f, 0);
		meshes[1] = pyramid;
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		for (int i = 0, l = meshes.length; i < l; i++) {
			meshes[i].rotation.x += 0.01f;
			meshes[i].rotation.y += 0.01f;
		}
	}
	
	public void draw(Graphics graphics) {
		super.draw(graphics);

		for (int i = 0, l = meshes.length; i < l; i++) {
			meshes[i].rotation.x += 0.01f;
			meshes[i].rotation.y += 0.01f;
		}
		
		device.clear(Color.black);
		device.render(camera, meshes);
		device.present();
		
		graphics.drawImage(frontBuffer, 0, 0, this.width, this.height, null);
	}

	public static void main(String [] args) {
		Soft3DRendering game = new Soft3DRendering();
		game.run();
	}
}
```

### License

MIT License, take a look of LICENSE file for more informations.