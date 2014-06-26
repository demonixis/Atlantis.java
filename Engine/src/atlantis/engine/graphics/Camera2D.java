package atlantis.engine.graphics;

import atlantis.engine.Screen;
import atlantis.framework.GameTime;
import atlantis.framework.IUpdateable;
import atlantis.framework.Rectangle;
import atlantis.framework.Vector2;

public class Camera2D implements IUpdateable {
	protected int x;
	protected int y;
	protected Rectangle viewport;
	protected Vector2 center;
	protected Sprite follow;
	protected boolean hasToFollow;
	
	public Camera2D() {
		this(0, 0, Screen.getWidth(), Screen.getHeight());
	}
	
	public Camera2D(int x, int y, int width, int height) {
		this.set(x, y, width, height);
		this.follow = null;
		this.hasToFollow = false;
	}
	
	protected void set(int x, int y, int width, int height) {
		this.x =  x;
		this.y = y;
		this.viewport.set(0, 0, width, height);
		this.center.set(Screen.getWidthPerTwo(), Screen.getHeightPerTwo()); 
	}
	
	public void reset() {
		this.x = 0;
		this.y = 0;
	}
	
	public void resize(int width, int height) {
		this.center.set(width >> 1, height >> 1);
	}
	
	public void follow(Sprite sprite) {
		this.follow = sprite;
		this.hasToFollow = true;
	}
	
	public void unfollow() {
		this.follow = null;
		this.hasToFollow = false;
	}
	
	public Vector2 getPosition() {
		if (this.follow != null) {
			return this.getRelativePosition(this.follow);
		}
		
		return new Vector2(this.x, this.y);
	}
	
	public Vector2 getRelativePosition(Sprite sprite) {
		return new Vector2(sprite.getX() + this.x, sprite.getY() + this.y);
	}
	
	@Override
	public void update(GameTime gameTime) {
		if (this.follow != null) {
	        float diffX = (this.follow.position.x - this.follow.lastPosition.x);
	        float diffY = (this.follow.position.y - this.follow.lastPosition.y);
	        float targX = this.follow.position.x;
	        float targY = this.follow.position.y;
	        
	        if ((this.follow.rectangle.x >= this.center.x) && (this.x < this.viewport.getRight() - Screen.getWidth()) ||
	            (this.follow.rectangle.x <= this.center.x) && (this.x > this.viewport.x)) {
	            this.x += diffX;
	            targX = this.center.x;
	        }
	            
	        if ((this.follow.rectangle.y >= this.center.y) && (this.y < this.viewport.getBottom() - Screen.getHeight()) ||
	            (this.follow.rectangle.y <= this.center.y) && (this.y > this.viewport.y)) {
	            this.y += diffY;
	            targY = this.center.y;
	        }
	        
	        this.follow.move(targX, targY); 
	    }
	    
	    if (this.x < this.viewport.x) {
	        this.x = this.viewport.x;
	    }
	    
	    else if (this.x + Screen.getWidth() > this.viewport.getRight()) {
	        this.x = this.viewport.getRight() - Screen.getWidth();
	    }
	    
	    else if (this.y < this.viewport.y) {
	        this.y = this.viewport.y;
	    }
	    
	    if (this.y + Screen.getHeight() > this.viewport.getBottom()) {
	        this.y = this.viewport.getBottom() - Screen.getHeight();
	    }		
	}
}
