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
}
