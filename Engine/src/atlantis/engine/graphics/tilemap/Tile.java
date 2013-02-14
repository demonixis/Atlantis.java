package atlantis.engine.graphics.tilemap;

public class Tile {
	protected int x;
	protected int y;
	protected int textureIndex;
	protected int type;
	
	public Tile() {
		this(0, 0, 0, 0);
	}
	
	public Tile(int x, int y, int textureId) {
		this(x, y, textureId, 0);
	}
	
	public Tile(int x, int y, int textureId, int type) {
		this.x = x;
		this.y = y;
		this.textureIndex = textureId;
		this.type = type;
	}
}
