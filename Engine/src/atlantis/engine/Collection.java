package atlantis.engine;

import java.util.ArrayList;

import atlantis.framework.GameTime;

public abstract class Collection<T> {
	protected ArrayList<T> members;
	protected ArrayList<T> safeMembers;
	protected boolean secureCycle;
	protected int membersCount;
	protected int safeMembersCount;
	
	public Collection() {
		this.members = new ArrayList<>();
		this.safeMembers = new ArrayList<>();
		this.secureCycle = false;
	}
	
	public void update(GameTime gameTime) {
		if (this.membersCount > 0) {
			if (this.secureCycle) {
				this.safeMembers.clear();
				this.safeMembers.addAll(this.members);
			}
			else {
				this.safeMembers = this.members;
			}
			this.safeMembersCount = this.safeMembers.size();
			this.doUpdate(gameTime);
		}
	}
	
	protected abstract void doUpdate(GameTime gameTime);
	
    public boolean Add(T item)
    {
        if (this.members.contains(item))
            return false;

        this.members.add(item);
        this.membersCount++;
        return true;
    }

    public boolean Remove(T item)
    {
        if (!this.members.contains(item))
            return false;

        this.members.remove(item);
        this.membersCount--;
        return true;
    }

    public void Clear()
    {
        this.members.clear();
        this.membersCount = 0;
    }

    public int IndexOf(T element)
    {
        return this.members.indexOf(element);
    }

    public boolean contains(T element)
    {
        return this.members.contains(element);
    }
}
