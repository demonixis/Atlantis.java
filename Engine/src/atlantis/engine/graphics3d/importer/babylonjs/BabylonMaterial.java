package atlantis.engine.graphics3d.importer.babylonjs;

public class BabylonMaterial {
	public String name;
    public String id;
    public float[] ambient;
    public float[] diffuse;
    public float[] specular;
    public float specularPower;
    public float[] emissive;
    public float alpha;
    public boolean backFaceCulling;
    public BabylonTexture diffuseTexture;
    public BabylonTexture opacityTexture;
    
    public BabylonMaterial() {
    	this.name = "";
    	this.id = "";
    	this.ambient = new float[0];
    	this.diffuse = new float[0];
    	this.specular = new float[0];
    	this.specularPower = 0.0f;
    	this.emissive = new float[0];
    	this.alpha = 0.0f;
    	this.backFaceCulling = false;
    	this.diffuseTexture = null;
    	this.opacityTexture = null;
    }
}
