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
    
    public BabylonMesh() {
    	this.name = "";
    	this.id = "";
    	this.materialId = "";
    	this.position = new float[0];
    	this.rotation = new float[0];
    	this.scaling = new float[0];
    	this.isVisible = true;
    	this.isEnabled = true;
    	this.checkCollisions = false;
    	this.billboardMode = 0;
    	this.uvCount = 0;
    	this.vertices = new float[0];
    	this.indices = new float[0];
    	this.subMeshes = new BabylonSubMesh[0];
    }
}
