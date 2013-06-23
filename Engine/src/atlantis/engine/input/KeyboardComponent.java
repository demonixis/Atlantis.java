// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.input;

import java.awt.event.KeyEvent;

import atlantis.framework.Game;
import atlantis.framework.GameComponent;
import atlantis.framework.GameTime;
import atlantis.framework.input.KeyboardState;

/**
 * A keyboard component that be used with KeyboardHelper to provide some
 * interesting keyboars states as pressed, justPressed, etc.
 * @author Yannick
 */
public class KeyboardComponent extends GameComponent {
	protected KeyboardState keysState;
	protected KeyboardState previousKeysState;
	
	public KeyboardComponent(Game game) {
		super(game);
		this.keysState = game.getKeyboardManager().getState();
		this.previousKeysState = game.getKeyboardManager().getState();
	}

     public void update(GameTime gameTime)
     {
         this.previousKeysState = this.keysState;
         this.keysState = this.game.getKeyboardManager().getState();
     }

     /**
      * 
      * @param key
      * @return
      */
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
     
     public boolean up() {
         return this.pressed(KeyEvent.VK_UP);
     }

     public boolean down() {
         return this.pressed(KeyEvent.VK_DOWN);
     }

     public boolean left() {
         return this.pressed(KeyEvent.VK_LEFT);
     }

     public boolean right() {
         return this.pressed(KeyEvent.VK_RIGHT);
     }

     public boolean enter() {
         return this.pressed(KeyEvent.VK_ENTER);
     }

     public boolean space() {
         return this.pressed(KeyEvent.VK_SPACE);
     }

     public boolean escape() {
         return this.pressed(KeyEvent.VK_ESCAPE);
     }

     public boolean control() {
         return this.pressed(KeyEvent.VK_CONTROL);
     }

     public boolean shift() {
         return this.pressed(KeyEvent.VK_SHIFT);
     }

     public boolean tab() {
         return this.pressed(KeyEvent.VK_TAB);
     }
}
