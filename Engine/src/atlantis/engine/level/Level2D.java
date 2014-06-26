package atlantis.engine.level;

import atlantis.engine.Application;
import atlantis.engine.graphics.Camera2D;
import atlantis.engine.graphics.SpriteGroup;
import atlantis.framework.GameTime;
import atlantis.framework.graphics.SpriteBatch;

public class Level2D extends Level {

	protected SpriteGroup scene;
	protected Camera2D camera;
	protected SpriteBatch spriteBatch;
	
	public Level2D(String name) {
		super(name);
	}
	
	@Override
	protected void intialize() {
		this.spriteBatch = new SpriteBatch(Application.game.graphicsDevice());
		this.camera = new Camera2D();
		this.scene = new SpriteGroup();
	}

	@Override
	protected void update(GameTime gameTime) {
		this.scene.update(gameTime);
		this.camera.update(gameTime);
	}

	@Override
	protected void draw(GameTime gameTime) {
		this.spriteBatch.begin();
		this.scene.draw(gameTime, spriteBatch);
		this.spriteBatch.end();
	}
}
