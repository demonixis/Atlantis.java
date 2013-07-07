package atlantis.engine.graphics;

public interface ISpriteMouseListener {
	void onMouseClick(int button);
	void onMouseJustClicked(int button);
	void onMouseOver();
	void onMouseOut();
}
