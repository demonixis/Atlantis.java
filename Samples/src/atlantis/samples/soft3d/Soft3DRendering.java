package atlantis.samples.soft3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import atlantis.engine.graphics3d.Camera;
import atlantis.engine.graphics3d.Device;
import atlantis.engine.graphics3d.Mesh;
import atlantis.engine.graphics3d.geometry.CubeGeometry;
import atlantis.engine.graphics3d.geometry.PyramidGeometry;
import atlantis.framework.Game;
import atlantis.framework.GameTime;
import atlantis.framework.Vector3;

public class Soft3DRendering extends Game {
	Device device;
	Camera camera;
	Mesh [] meshes;
	BufferedImage frontBuffer;
	
	public Soft3DRendering() {
		super(1024, 768, "Atlantis Game Engine for Java - Software 3D rendering");
		camera = new Camera();
		camera.position = new Vector3(0, 0, 30);
		device = new Device(640, 480);
		frontBuffer = device.getFrontBuffer();
	}
	
	public void loadContent() {
		super.loadContent();
		meshes = new Mesh[2];
		
		Mesh cube = new Mesh("cube", new CubeGeometry());
		cube.position.setValues(-2.5f, -2.5f, 0.0f);
		cube.color = Color.pink;
		meshes[0] = cube;
		
		Mesh pyramid = new Mesh("pyramid", new PyramidGeometry());
		pyramid.position.setValues(2.5f, 2.5f, 0.0f);
		pyramid.color = Color.green;
		meshes[1] = pyramid;
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
	}
	
	public void draw(Graphics graphics) {
		super.draw(graphics);
		if (meshes == null) return;
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
