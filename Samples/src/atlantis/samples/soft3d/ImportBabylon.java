package atlantis.samples.soft3d;

import java.awt.Color;

import atlantis.engine.graphics3d.importer.babylonjs.BabylonImporter;
import atlantis.framework.Game;
import atlantis.framework.GameTime;

public class ImportBabylon extends BaseDemo3D {
	public ImportBabylon() {
		super("AtlantisEngine.java - 3D serie : Loading a babylon scene");
		this.camera.position.set(0, 13.9f, 32.0f);
		this.camera.rotation.set(-0.38f, 0.0f, 0.0f);
	}
	
	/**
	 * Load the scene from a file.
	 */
	public void loadContent() {
		super.loadContent();
		//meshes = BabylonImporter.loadBabyonScene("Content/models/repaireArea.babylon");
		//meshes = BabylonImporter.loadBabyonScene("Content/models/object.babylon");
		//meshes = BabylonImporter.loadBabyonScene("Content/models/smallScene.babylon");
		meshes = BabylonImporter.loadBabyonScene("Content/models/spaceship.babylon");
	
		for (int i = 0; i < meshes.length; i++) {
			meshes[i].color = Color.lightGray;
		}
	}
	
	/**
	 * Apply a rotation on each objects of the scene
	 */
	public void update(GameTime gameTime) {
		super.update(gameTime);
		for (int i = 0, l = meshes.length; i < l; i++) {
			meshes[i].rotation.y += 0.005f;
		}
	}

	public static void main(String[] args) {
		Game game = new ImportBabylon();
		game.run();
	}
}
