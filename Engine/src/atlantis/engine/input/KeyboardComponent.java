// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.input;

import atlantis.framework.BaseGame;
import atlantis.framework.GameComponent;
import atlantis.framework.GameTime;
import atlantis.framework.input.KeyboardState;

/**
 * A keyboard component
 * @author Yann
 */
public class KeyboardComponent extends GameComponent {
	protected KeyboardState keysState;
	protected KeyboardState previousKeysState;
	
	public KeyboardComponent(BaseGame game) {
		super(game);
		this.keysState = game.getKeyboardManager().getState();
		this.previousKeysState = game.getKeyboardManager().getState();
	}

     public void update(GameTime gameTime)
     {
         this.previousKeysState = this.keysState;
         this.keysState = this.game.getKeyboardManager().getState();
     }

     public boolean pressed(int key)
     {
         return this.keysState.isKeyDown(key);
     }

     public boolean released(int key)
     {
         return this.keysState.isKeyUp(key);
     }

     public boolean justPressed(int key)
     {
         return this.keysState.isKeyUp(key) && this.previousKeysState.isKeyDown(key);
     }

     public boolean justReleased(int key)
     {
         return this.keysState.isKeyDown(key) && this.previousKeysState.isKeyUp(key);
     }
}
