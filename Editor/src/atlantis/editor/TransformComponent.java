package atlantis.editor;

import atlantis.framework.Vector3;

public class TransformComponent extends AbstractComponent implements ITransformChanged {
	private static final long serialVersionUID = 3820593234844534864L;
	protected TransformLine[] transformProperties;
	protected ITransformChanged transformChangedHandler;
	
	public TransformComponent() {
		super("Transform", 195, 135);
		
		String[] names = { "Position", "Rotation", "Scale" };
		
		this.transformProperties = new TransformLine[3];
		
		for (int i = 0; i < 3; i++) {
			this.transformProperties[i] = new TransformLine(names[i]);
			this.transformProperties[i].addTransformChangedListener(this);
			this.container.add(transformProperties[i]);
		}
	}
	
	public void addTransformChangedListener(ITransformChanged handler) {
		this.transformChangedHandler = handler;
	}
	
	public void Setup(Vector3 position, Vector3 rotation, Vector3 scale) {
		this.transformProperties[0].setup(position.x, position.y, position.z);
		this.transformProperties[1].setup(rotation.x, rotation.y, rotation.z);
		this.transformProperties[2].setup(scale.x, scale.y, scale.z);
	}

	@Override
	public void OnPropertyChanged(String name, float x, float y, float z) {
		if (this.transformChangedHandler != null) {
			this.transformChangedHandler.OnPropertyChanged(name, x, y, z);
		}
	}
}
