package atlantis.samples.soft3d;

import java.awt.Color;
import java.util.ArrayList;

import atlantis.engine.graphics3d.Mesh;
import atlantis.engine.graphics3d.geometry.CubeGeometry;
import atlantis.engine.graphics3d.geometry.PlaneGeometry;
import atlantis.engine.graphics3d.geometry.QuadGeometry;
import atlantis.engine.graphics3d.geometry.PyramidGeometry;
import atlantis.framework.GameTime;

public class ProceduralGeometries extends BaseDemo3D {
	
	public ProceduralGeometries() {
		super("Atlantis Game Engine for Java - Software 3D rendering");
		this.camera.position.z = 50;
	}
	
	/**
	 * We create some geometries.
	 */
	public void loadContent() {
		super.loadContent();

		ArrayList<Mesh> meshArray = new ArrayList<Mesh>();
				
		Mesh cube = new Mesh("cube", new CubeGeometry());
		cube.setFace4Color(0, Color.red);
		cube.setFace4Color(2, Color.white);
		cube.setFace4Color(4, Color.red);
		cube.setFace4Color(6, Color.white);
		cube.setFace4Color(8, Color.red);
		cube.getPosition().set(-2.5f, -2.5f, 0);
		//cube.setWireframeMode(true);
		meshArray.add(cube);
		
		Mesh cube2 = new Mesh("cube2", new CubeGeometry());
		cube2.setFace4Color(0, Color.red);
		cube2.setFace4Color(2, Color.white);
		cube2.setFace4Color(4, Color.orange);
		cube2.setFace4Color(6, Color.blue);
		cube2.setFace4Color(8, Color.gray);
		cube2.getPosition().set(2.5f, 2.5f, 0);
		meshArray.add(cube2);
		
		Mesh plane = new Mesh("plane", new QuadGeometry());
		plane.setFacesColor(Color.PINK);
		plane.getPosition().set(-2.5f, 2.5f, 0.0f);
		meshArray.add(plane);
		
		Mesh pyramid = new Mesh("triangle", new PyramidGeometry());
		pyramid.setFace4Color(0, Color.cyan);
		pyramid.setFace4Color(2, Color.green);
		pyramid.setFace4Color(4, Color.orange);
		pyramid.setFace4Color(6, Color.magenta);
		pyramid.getPosition().set(2.5f, -2.5f, 0);
		meshArray.add(pyramid);
		
		Mesh pyramid2 = new Mesh("triangle", new PyramidGeometry());
		pyramid2.setFace4Color(0, Color.red);
		pyramid2.setFace4Color(2, Color.green);
		pyramid2.setFace4Color(4, Color.pink);
		pyramid2.setFace4Color(6, Color.magenta);
		pyramid2.getPosition().set(7.5f, 2.5f, 0);
		meshArray.add(pyramid2);
		
		Mesh ground = new Mesh("plane", new PlaneGeometry(20, 20));
		ground.getPosition().set(-10f,  -4.5f, 0);
		meshArray.add(ground);
		
		this.meshes = meshArray.toArray(this.meshes);
	}
	
	/**
	 * Apply a small rotation on each mesh.
	 */
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		for (int i = 0, l = meshes.length - 1; i < l; i++) {
			meshes[i].getRotation().z += 0.01f;
			meshes[i].getRotation().y += 0.01f;
		}
	}

	public static void main(String [] args) {
		ProceduralGeometries game = new ProceduralGeometries();
		game.run();
	}
}
