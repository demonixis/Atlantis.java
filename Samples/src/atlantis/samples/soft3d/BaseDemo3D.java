package atlantis.samples.soft3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import atlantis.engine.graphics3d.Camera;
import atlantis.engine.graphics3d.Mesh;
import atlantis.engine.graphics3d.Renderer;
import atlantis.framework.Game;
import atlantis.framework.GameTime;
import atlantis.framework.input.KeyboardState;

public class BaseDemo3D extends Game {
	protected Renderer renderer;
	protected Camera camera;
	protected Mesh[] meshes;
	protected KeyboardState keyboardState;
	
	public BaseDemo3D(String title) {
		super(1024, 768, title);
		this.camera = new Camera();
		this.renderer = new Renderer(this.width, this.height);
		this.meshes = new Mesh[0];
	}
		
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		this.keyboardState = this.keyboardManager.getState();
		
		if (keyboardState.isKeyDown(KeyEvent.VK_UP))
            camera.translate(0.0f, 0.0f, -1.0f);
        
        else if (keyboardState.isKeyDown(KeyEvent.VK_DOWN))
            camera.translate(0.0f, 0.0f, 1.0f);

        if (keyboardState.isKeyDown(KeyEvent.VK_LEFT))
            camera.rotate(0.0f, 0.01f, 0.0f);

        else if (keyboardState.isKeyDown(KeyEvent.VK_RIGHT))
            camera.rotate(0.0f, -0.01f, 0.0f);

        if (keyboardState.isKeyDown(KeyEvent.VK_Q))
            camera.translate(-0.1f, 0.0f, 0.0f);

        if (keyboardState.isKeyDown(KeyEvent.VK_D))
            camera.translate(0.1f, 0.0f, 0.0f);

        if (keyboardState.isKeyDown(KeyEvent.VK_PAGE_UP))
            camera.rotate(-0.01f, 0.0f, 0.0f);

        else if (keyboardState.isKeyDown(KeyEvent.VK_PAGE_DOWN))
            camera.rotate(0.01f, 0.0f, 0.0f);

        if (keyboardState.isKeyDown(KeyEvent.VK_A))
            camera.translate(0.0f, -0.05f, 0.0f);

        else if (keyboardState.isKeyDown(KeyEvent.VK_E))
            camera.translate(0.0f, 0.05f, 0.0f);
        
        if (keyboardState.isKeyDown(KeyEvent.VK_F5)) {
        	System.out.println("Position: " + camera.position.toString());
        	System.out.println("Rotation: " + camera.rotation.toString());
        }
		
		if (keyboardState.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.exit();
		}
	}
	
	public void draw(Graphics graphics) {
		super.draw(graphics);
		renderer.clear(Color.black);
		renderer.render(graphics, camera, meshes);
	}
}
