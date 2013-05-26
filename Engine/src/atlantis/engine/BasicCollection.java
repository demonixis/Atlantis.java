package atlantis.engine;

import atlantis.framework.GameTime;

public class BasicCollection extends Collection<BasicEntity> {
	protected void doUpdate(GameTime gameTime)
    {
        for (int i = 0; i < this.safeMembersCount; i++)
        {
            if (this.safeMembers.get(i).isEnabled())
                this.safeMembers.get(i).update(gameTime);
        }
    }
}
