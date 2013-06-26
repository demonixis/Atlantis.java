package atlantis.samples.soft3d;

import java.awt.Color;
import atlantis.engine.graphics3d.Mesh;
import atlantis.engine.graphics3d.geometry.CubeGeometry;
import atlantis.engine.graphics3d.geometry.PlaneGeometry;
import atlantis.engine.graphics3d.geometry.PyramidGeometry;
import atlantis.framework.GameTime;
import atlantis.framework.Vector3;

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

		meshes = new Mesh[6];
				
		Mesh cube = new Mesh("cube", new CubeGeometry());
		cube.color = Color.GREEN;
		cube.position = new Vector3(-2.5f, -2.5f, 0);
		cube.setWireframeMode(true);
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
	
	/**
	 * Apply a small rotation on each mesh.
	 */
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		for (int i = 0, l = meshes.length; i < l; i++) {
			meshes[i].rotation.z += 0.01f;
			meshes[i].rotation.y += 0.01f;
		}
	}

	public static void main(String [] args) {
		ProceduralGeometries game = new ProceduralGeometries();
		game.run();
	}
}
