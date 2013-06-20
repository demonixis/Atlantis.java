package atlantis.engine.input;

import java.awt.event.KeyEvent;

public class KeyboardHelper {
	KeyboardComponent keyboardComponent;
	
	public KeyboardHelper(KeyboardComponent keyboardComponent)
    {
        this.keyboardComponent = keyboardComponent;
    }

    public boolean pressed(int key)
    {
        return this.keyboardComponent.pressed(key);
    }

    public boolean Released(int key)
    {
        return this.keyboardComponent.released(key);
    }

    public boolean Justpressed(int key)
    {
        return this.keyboardComponent.justPressed(key);
    }

    public boolean JustReleased(int key)
    {
        return this.keyboardComponent.justReleased(key);
    }

    public boolean Up()
    {
        return this.pressed(KeyEvent.VK_UP);
    }

    public boolean down()
    {
        return this.pressed(KeyEvent.VK_DOWN);
    }

    public boolean left()
    {
        return this.pressed(KeyEvent.VK_LEFT);
    }

    public boolean right()
    {
        return this.pressed(KeyEvent.VK_RIGHT);
    }

    public boolean enter()
    {
        return this.pressed(KeyEvent.VK_ENTER);
    }

    public boolean space()
    {
        return this.pressed(KeyEvent.VK_SPACE);
    }

    public boolean escape()
    {
        return this.pressed(KeyEvent.VK_ESCAPE);
    }

    public boolean control()
    {
        return this.pressed(KeyEvent.VK_CONTROL);
    }

    public boolean shift()
    {
        return this.pressed(KeyEvent.VK_SHIFT);
    }

    public boolean tab()
    {
        return this.pressed(KeyEvent.VK_TAB);
    }
}
