package atlantis.samples.soft3d;

import java.awt.Color;
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
	protected float moveSpeed;
	protected float rotateSpeed;
	protected float strafeSpeed;
	protected float viewSpeed;
	
	public BaseDemo3D(String title) {
		super(1024, 768, title);
		this.camera = new Camera();
		this.renderer = new Renderer(this.width, this.height, 640, 480, true);
		this.meshes = new Mesh[0];
		this.moveSpeed = 0.01f;
		this.rotateSpeed = 0.0005f;
		this.strafeSpeed = -0.005f;
		this.renderer.getLight().setEnabled(false);
	}
		
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		this.keyboardState = this.keyboardManager.getState();
		
		if (keyboardState.isKeyDown(KeyEvent.VK_UP))
            camera.translate(0.0f, 0.0f, -this.moveSpeed * gameTime.getElapsedTime());
        
        else if (keyboardState.isKeyDown(KeyEvent.VK_DOWN))
            camera.translate(0.0f, 0.0f, this.moveSpeed * gameTime.getElapsedTime());

        if (keyboardState.isKeyDown(KeyEvent.VK_LEFT))
            camera.rotate(0.0f, this.rotateSpeed * gameTime.getElapsedTime(), 0.0f);

        else if (keyboardState.isKeyDown(KeyEvent.VK_RIGHT))
            camera.rotate(0.0f, -this.rotateSpeed * gameTime.getElapsedTime(), 0.0f);

        if (keyboardState.isKeyDown(KeyEvent.VK_Q))
            camera.translate(this.strafeSpeed * gameTime.getElapsedTime(), 0.0f, 0.0f);

        if (keyboardState.isKeyDown(KeyEvent.VK_D))
            camera.translate(-this.strafeSpeed * gameTime.getElapsedTime(), 0.0f, 0.0f);

        if (keyboardState.isKeyDown(KeyEvent.VK_PAGE_UP))
            camera.rotate(-this.strafeSpeed * gameTime.getElapsedTime() * 0.01f, 0.0f, 0.0f);

        else if (keyboardState.isKeyDown(KeyEvent.VK_PAGE_DOWN))
            camera.rotate(this.strafeSpeed * gameTime.getElapsedTime() * 0.01f, 0.0f, 0.0f);

        if (keyboardState.isKeyDown(KeyEvent.VK_A))
            camera.translate(0.0f, -this.strafeSpeed * gameTime.getElapsedTime(), 0.0f);

        else if (keyboardState.isKeyDown(KeyEvent.VK_E))
            camera.translate(0.0f, this.strafeSpeed * gameTime.getElapsedTime(), 0.0f);
        
        
        if (keyboardState.isKeyDown(KeyEvent.VK_NUMPAD7)) 
        	renderer.getLight().getPosition().x -= 5.0f;
        else if (keyboardState.isKeyDown(KeyEvent.VK_NUMPAD4)) 
        	renderer.getLight().getPosition().x += 5.0f;
        
        
        if (keyboardState.isKeyDown(KeyEvent.VK_NUMPAD8)) 
        	renderer.getLight().getPosition().y -= 5.0f;
        else if (keyboardState.isKeyDown(KeyEvent.VK_NUMPAD5)) 
        	renderer.getLight().getPosition().y += 5.0f;
        
        if (keyboardState.isKeyDown(KeyEvent.VK_NUMPAD9)) 
        	renderer.getLight().getPosition().z -= 5.0f;
        else if (keyboardState.isKeyDown(KeyEvent.VK_NUMPAD6)) 
        	renderer.getLight().getPosition().z += 5.0f;
		
		if (keyboardState.isKeyDown(KeyEvent.VK_F1)) 
			this.renderer.getLight().setEnableFlatShading(true);
		
		else if (keyboardState.isKeyDown(KeyEvent.VK_F2)) 
			this.renderer.getLight().setEnableFlatShading(false);
		
		else if (keyboardState.isKeyDown(KeyEvent.VK_F3))
			this.renderer.getLight().setEnabled(false);
		
		else if (keyboardState.isKeyDown(KeyEvent.VK_F4))
			this.renderer.getLight().setEnabled(true);
		
        
        if (keyboardState.isKeyDown(KeyEvent.VK_F5)) {
        	System.out.println("Position: " + camera.position.toString());
        	System.out.println("Rotation: " + camera.rotation.toString());
        	System.out.println("Light: " + renderer.getLight().getPosition().toString());
        }
		
		if (keyboardState.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.exit();
		}
	}
	
	public void draw(GameTime gameTime) {
		super.draw(gameTime);
		renderer.clear(Color.black);
		renderer.render(this.graphicsDevice().getGraphics(), camera, meshes);
		this.graphicsDevice().getRenderTarget().getGraphics().drawString("FPS: " + gameTime.getFPS(), 20, 20);
	}
}
