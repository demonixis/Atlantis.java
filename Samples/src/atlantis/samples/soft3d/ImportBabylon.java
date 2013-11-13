package atlantis.samples.soft3d;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import atlantis.engine.graphics3d.Mesh;
import atlantis.engine.graphics3d.geometry.PlaneGeometry;
import atlantis.engine.graphics3d.importer.babylonjs.BabylonImporter;
import atlantis.framework.Game;
import atlantis.framework.GameTime;

public class ImportBabylon extends BaseDemo3D {
	private boolean autoRotate;
	
	public ImportBabylon() {
		super("AtlantisEngine.java - 3D serie : Loading a babylon scene");
		this.camera.position.set(0, 13.9f, 32.0f);
		this.camera.rotation.set(-0.38f, 0.0f, 0.0f);
		this.autoRotate = true;
	}
	
	/**
	 * Load the scene from a file.
	 */
	public void loadContent() {
		super.loadContent();
		ArrayList<Mesh> meshArray = new ArrayList<Mesh>();
		//meshes = BabylonImporter.loadBabyonScene("Content/models/repaireArea.babylon");
		//meshes = BabylonImporter.loadBabyonScene("Content/models/object.babylon");
		//meshes = BabylonImporter.loadBabyonScene("Content/models/smallScene.babylon");
		Mesh[] bbMeshes = BabylonImporter.loadBabyonScene("Content/models/spaceship.babylon");
		
		for (Mesh m : bbMeshes) {
			meshArray.add(m);
			m.randomizeFaceColor();
		}
		
		Mesh ground = new Mesh("ground", new PlaneGeometry(20, 20));
		ground.getPosition().set(-10, -0.9f, -10);
		meshArray.add(ground);
		
		meshes = meshArray.toArray(meshes);
	}
	
	/**
	 * Apply a rotation on each objects of the scene
	 */
	public void update(GameTime gameTime) {
		super.update(gameTime);
		if (this.autoRotate) {
			for (int i = 0, l = meshes.length - 1; i < l; i++) {
				meshes[i].getRotation().y += 0.005f;
				
				if (keyboardState.isKeyDown(KeyEvent.VK_R)) {
					meshes[i].randomizeFaceColor();
				}
			}
		}
		
		if (this.keyboardManager.getState().isKeyDown(KeyEvent.VK_ENTER)) {
			this.autoRotate = !this.autoRotate;
		}
	}

	public static void main(String[] args) {
		Game game = new ImportBabylon();
		game.run();
	}
}
