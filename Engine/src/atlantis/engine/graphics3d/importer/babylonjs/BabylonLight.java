package atlantis.engine.graphics3d.importer.babylonjs;

/**
 * Describe a light on a babylon scene
 * @author Yannick
 */
public class BabylonLight {
	public String name;
    public String id;
    public float type;
    public float[] data;
    public float intensity;
    public float[] diffuse;
    public float[] specular;
    
    public BabylonLight() {
    	this.name = "";
    	this.id = "";
    	this.type = 0.0f;
    	this.data = new float[0];
    	this.intensity = 0.0f;
    	this.diffuse = new float[0];
    	this.specular = new float[0];
    }
}
