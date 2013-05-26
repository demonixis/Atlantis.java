package atlantis.engine;

import java.awt.Graphics;

import atlantis.framework.GameTime;

public class GameEntityCollection extends Collection<GameEntity> {
	 public  void initialize()
     {
         for (int i = 0; i < this.membersCount; i++)
             this.members.get(i).initialize();
     }

     public  void loadContent()
     {
         for (int i = 0; i < this.membersCount; i++)
             this.members.get(i).loadContent();
     }

     public  void unloadContent()
     {
         for (int i = 0; i < this.membersCount; i++)
             this.members.get(i).unloadContent();
     }

     protected void doUpdate(GameTime gameTime)
     {
         for (int i = 0; i < this.safeMembersCount; i++)
         {
             if (this.safeMembers.get(i).isEnabled())
                 this.safeMembers.get(i).update(gameTime);
         }
     }

     public void Draw(GameTime gameTime, Graphics graphics)
     {
         for (int i = 0; i < this.safeMembersCount; i++)
         {
             if (this.safeMembers.get(i).isVisible())
                 this.safeMembers.get(i).draw(gameTime, graphics);
         }
     }
}
