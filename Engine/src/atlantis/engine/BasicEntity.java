package atlantis.engine;

import atlantis.framework.GameTime;

public abstract class BasicEntity {
	private static int counterId = 0;

    protected int id;
    protected String name;
    protected boolean enabled;


    public BasicEntity()
    {
        this.id = counterId++;
        this.name = "Base_" + this.id;
        this.enabled = true;
    }

    public abstract void update(GameTime gameTime);

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
