package atlantis.editor;

import java.awt.Color;

import atlantis.framework.Vector3;

public interface ILightChanged {
	public void OnLightChanged(Vector3 position, Color color, float intensity);
}
