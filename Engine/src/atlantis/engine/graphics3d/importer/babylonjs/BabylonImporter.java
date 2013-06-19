package atlantis.engine.graphics3d.importer.babylonjs;

import atlantis.engine.graphics3d.Face3;
import atlantis.engine.graphics3d.Mesh;
import atlantis.framework.Vector3;

public class BabylonImporter {
	public static Mesh[] loadBabyonScene(String filename) {
		
		// 1 - Open the file
		// 2 - Parse the json and create instance
		// 3 - Replace this line by something like : scene = JSON.deserialize<BabylonScene>(string);
		BabylonScene scene = new BabylonScene();
    
        Mesh[] meshesCollection = new Mesh[scene.meshes.length];

        for (int i = 0, l = scene.meshes.length; i < l; i++)
        {
            float[] verticesArray = scene.meshes[i].vertices;
            float[] indicesArray = scene.meshes[i].indices;
            int uvCount = scene.meshes[i].uvCount;
            int verticesStep = 1;

            verticesStep = uvCount == 0 ? 6 : verticesStep;
            verticesStep = uvCount == 1 ? 8 : verticesStep;
            verticesStep = uvCount == 2 ? 10 : verticesStep;

            int verticesCount = verticesArray.length / verticesStep;
            int facesCount = indicesArray.length / 3;

            Mesh mesh = new Mesh(scene.meshes[i].name, verticesCount, facesCount);

            for (int index = 0; index < verticesCount; index++)
            {
                float x = verticesArray[index * verticesStep];
                float y = verticesArray[index * verticesStep + 1];
                float z = verticesArray[index * verticesStep + 2];
                mesh.setVertex(i, new Vector3(x, y, z));
            }

            for (int index = 0; index < facesCount; index++)
            {
                int a = (int)indicesArray[index * 3];
                int b = (int)indicesArray[index * 3 + 1];
                int c = (int)indicesArray[index * 3 + 2];
                mesh.setFace(index, new Face3(a, b, c));
            }

            mesh.setPosition(new Vector3(scene.meshes[i].position[0], scene.meshes[i].position[1], scene.meshes[i].position[2]));
            mesh.setRotation(new Vector3(scene.meshes[i].rotation[0], scene.meshes[i].rotation[1], scene.meshes[i].rotation[2]));
            mesh.setScale(new Vector3(scene.meshes[i].scaling[0], scene.meshes[i].scaling[1], scene.meshes[i].scaling[2]));
            meshesCollection[i] = mesh;
        }

        return meshesCollection;
    }
}
