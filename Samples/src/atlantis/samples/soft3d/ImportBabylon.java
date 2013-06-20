package atlantis.samples.soft3d;

import atlantis.engine.graphics3d.importer.babylonjs.BabylonImporter;
import atlantis.framework.Game;
import atlantis.framework.GameTime;

public class ImportBabylon extends BaseDemo3D {
	public ImportBabylon() {
		super("AtlantisEngine.java - 3D serie : Loading a babylon scene");
		this.camera.position.z = 20;
	}
	
	/**
	 * Load the scene from a file.
	 */
	public void loadContent() {
		super.loadContent();
		//meshes = BabylonImporter.loadBabyonScene("Content/models/object.babylon");
		//meshes = BabylonImporter.loadBabyonScene("Content/models/smallScene.babylon");
		meshes = BabylonImporter.loadBabyonScene("Content/models/spaceship.babylon");
	}
	
	/**
	 * Apply a rotation on each objet of the scene
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
