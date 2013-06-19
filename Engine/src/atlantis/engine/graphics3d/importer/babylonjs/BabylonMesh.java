package atlantis.engine.graphics3d.importer.babylonjs;

public class BabylonMesh {
	public String name;
    public String id;
    public String materialId;
    public float[] position;
    public float[] rotation;
    public float[] scaling;
    public boolean isVisible;
    public boolean isEnabled;
    public boolean checkCollisions;
    public int billboardMode;
    public int uvCount;
    public float[] vertices;
    public float[] indices;
    public BabylonSubMesh[] subMeshes;
}
