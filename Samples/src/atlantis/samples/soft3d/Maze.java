package atlantis.samples.soft3d;

import java.awt.Color;
import java.util.ArrayList;
import atlantis.engine.graphics3d.Mesh;
import atlantis.engine.graphics3d.geometry.CubeGeometry;
import atlantis.framework.Vector3;

public class Maze extends BaseDemo3D {
	int[][] level;
	
	public Maze() {
		super("AtlantisEngine.java - 3D serie : Procedural Maze");
		this.camera.position.setValues(-21.56f, 51.60f, 86.7f);
		this.camera.rotation.setValues(-0.58f, -0.4f, 0f);
		
		this.level = new int [][] {
			{2, 2, 2, 2, 2, 1, 2, 2, 2, 2},
			{2, 1, 1, 1, 1, 1, 1, 1, 1, 2},
			{2, 1, 1, 1, 2, 2, 1, 1, 2, 2},
			{2, 1, 2, 1, 2, 2, 1, 1, 1, 2},
			{2, 1, 2, 2, 2, 2, 2, 2, 1, 2},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{2, 5, 2, 2, 2, 1, 2, 2, 2, 2},
			{2, 5, 2, 1, 1, 1, 2, 6, 6, 2},
			{2, 5, 2, 1, 1, 1, 2, 2, 2, 2},
			{2, 5, 2, 1, 1, 1, 2, 1, 8, 2},
			{2, 1, 1, 1, 1, 1, 1, 1, 1, 2},
			{2, 2, 2, 2, 1, 2, 2, 2, 2, 2}
		};
	}
	
	public void loadContent() {
		int width = this.level[0].length;
		int depth = this.level.length;
		
		ArrayList<Mesh> mazeMeshes = new ArrayList<Mesh>();
		
		CubeGeometry groundGeo = new CubeGeometry(width, 0.1f, depth);
		Mesh ground = new Mesh("ground", groundGeo);
		ground.vertexColor = Color.darkGray;
		ground.position = new Vector3(width - 1, -0.9f, depth - 1);
		mazeMeshes.add(ground);
		
		for (int y = 0; y < depth; y++) {
			for (int x = 0; x < width; x++) {
				switch (this.level[y][x]) {
				case 1: break;
				case 2: mazeMeshes.add(createCubeWall(x, y, 1, 1, 2, Color.CYAN)); break;
				case 6: mazeMeshes.add(createCubeWall(x, y, 1, 0.3f, 2, Color.red)); break;
				case 5: mazeMeshes.add(createCubeWall(x, y, 1, 0.2f, 2, Color.green)); break;
				case 8: break;
				case 9: break;
				}
			}
		}
		
		this.meshes = new Mesh[mazeMeshes.size()];
		mazeMeshes.toArray(this.meshes);
	}
	
	private Mesh createCubeWall(float x, float z, float sx, float sy, float sz, Color color) {
		int blockSize = 2;
		CubeGeometry wallGeo = new CubeGeometry();
		Mesh wallMesh = new Mesh("wall", wallGeo);
		wallMesh.position = new Vector3(x * blockSize, 0, z * blockSize);
		wallMesh.vertexColor = color;
		
		if (sy < 1) {
			wallMesh.position.y = -sy;
		}
		return wallMesh;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Maze mazeGame = new Maze();
		mazeGame.run();
	}

}
