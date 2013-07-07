package atlantis.engine.graphics;

public class Collider2 {
	public static boolean collide(Sprite spriteA, Sprite spriteB) {
		return spriteA.getRectangle().contains(spriteB.getRectangle());
	}
}
