package atlantis.samples.soft3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import atlantis.engine.graphics3d.Camera;
import atlantis.engine.graphics3d.Device;
import atlantis.engine.graphics3d.Mesh;
import atlantis.engine.graphics3d.geometry.CubeGeometry;
import atlantis.engine.graphics3d.geometry.PlaneGeometry;
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
		camera.position = new Vector3(20, 5, 20);
		device = new Device(this.width, this.height);
		frontBuffer = device.getFrontBuffer();
	}
	
	public void loadContent() {
		super.loadContent();
		meshes = new Mesh[6];
				
		Mesh cube = new Mesh("cube", new CubeGeometry());
		cube.color = Color.GREEN;
		cube.position = new Vector3(-2.5f, -2.5f, 0);
		meshes[0] = cube;
		
		Mesh cube2 = new Mesh("cube2", new CubeGeometry());
		cube2.color = Color.BLUE;
		cube2.position = new Vector3(2.5f, 2.5f, 0);
		meshes[1] = cube2;
		
		Mesh plane = new Mesh("plane", new PlaneGeometry());
		plane.color = Color.PINK;
		plane.position = new Vector3(-2.5f, 2.5f, 0.0f);
		meshes[2] = plane;
		
		Mesh pyramid = new Mesh("triangle", new PyramidGeometry());
		pyramid.color = Color.YELLOW;
		pyramid.position = new Vector3(2.5f, -2.5f, 0);
		meshes[3] = pyramid;
		
		Mesh pyramid2 = new Mesh("triangle", new PyramidGeometry());
		pyramid2.color = Color.ORANGE;
		pyramid2.position = new Vector3(7.5f, 2.5f, 0);
		meshes[4] = pyramid2;
		
		Mesh plane2 = new Mesh("plane", new PlaneGeometry());
		plane2.color = Color.RED;
		plane2.position = new Vector3(-7.5f, 2.5f, 0.0f);
		meshes[5] = plane2;
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
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
