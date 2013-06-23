// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine.input;

import java.awt.event.KeyEvent;

/**
 * A keyboard helper that provide instant access on different keyboard states.
 * @author Yannick
 */
public class KeyboardHelper {
	KeyboardComponent keyboardComponent;
	
	public KeyboardHelper(KeyboardComponent keyboardComponent) {
        this.keyboardComponent = keyboardComponent;
    }

    public boolean pressed(int key) {
        return this.keyboardComponent.pressed(key);
    }

    public boolean released(int key) {
        return this.keyboardComponent.released(key);
    }

    public boolean justpressed(int key) {
        return this.keyboardComponent.justPressed(key);
    }

    public boolean justReleased(int key) {
        return this.keyboardComponent.justReleased(key);
    }

    public boolean isUp() {
        return this.pressed(KeyEvent.VK_UP);
    }

    public boolean isDown() {
        return this.pressed(KeyEvent.VK_DOWN);
    }

    public boolean isLeft() {
        return this.pressed(KeyEvent.VK_LEFT);
    }

    public boolean isRright() {
        return this.pressed(KeyEvent.VK_RIGHT);
    }

    public boolean isEnter() {
        return this.pressed(KeyEvent.VK_ENTER);
    }

    public boolean isSpace() {
        return this.pressed(KeyEvent.VK_SPACE);
    }

    public boolean isEscape() {
        return this.pressed(KeyEvent.VK_ESCAPE);
    }

    public boolean isControl() {
        return this.pressed(KeyEvent.VK_CONTROL);
    }

    public boolean isShift() {
        return this.pressed(KeyEvent.VK_SHIFT);
    }

    public boolean isTab() {
        return this.pressed(KeyEvent.VK_TAB);
    }
}
