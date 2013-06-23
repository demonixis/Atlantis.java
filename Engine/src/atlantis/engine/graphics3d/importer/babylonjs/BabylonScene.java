// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.graphics3d.importer.babylonjs;

/**
 * Define the structure of a Babylon scene.
 * @author Yannick
 */
public class BabylonScene {
    public boolean autoClear;
    public float[] clearColor;
    public float[] ambientColor;
    public float[] gravity;
    public BabylonCamera[] cameras;
    public String activeCamera;
    public BabylonLight[] lights;
    public BabylonMaterial[] materials;
    public BabylonMesh[] meshes;
    public Object[] multiMaterials;
    
    public BabylonScene() {
    	this.autoClear = false;
    	this.clearColor = new float[3];
    	this.ambientColor = new float[0];
    	this.gravity = new float[0];
    	this.cameras = new BabylonCamera[0];
    	this.activeCamera = "";
    	this.lights = new BabylonLight[0];
    	this.materials = new BabylonMaterial[0];
    	this.meshes = new BabylonMesh[0];
    	this.multiMaterials = new Object[0];
    }
}
