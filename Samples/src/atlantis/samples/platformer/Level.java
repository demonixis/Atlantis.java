package atlantis.samples.platformer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import atlantis.engine.graphics.Sprite;
import atlantis.engine.graphics.SpriteGroup;
import atlantis.framework.Vector2;
import atlantis.framework.content.ContentManager;

public final class Level {
	private static Random random = new Random();
	private int width;
	private int height;
	private int levelId;
	private Sprite[] layers;
	private ArrayList<Sprite> blocks;
	private ArrayList<Sprite> items;
	private ArrayList<Sprite> monsters;
	private Vector2 startPosition;
	private int blocksSize;
	private int itemsSize;
	private int monstersSize;
	private boolean loaded;
	
	public Level(int id) {
		this.levelId = id;
		this.layers = new Sprite[3];
		this.startPosition = Vector2.Zero();
		
		// Blocks
		this.blocks = new ArrayList<Sprite>();
		this.blocksSize = 0;
		
		// Items
		this.items = new ArrayList<Sprite>();
		this.itemsSize = 0;
		
		// Monsters
		this.monsters = new ArrayList<>();
		this.monstersSize = 0;
		
		this.loaded = false;
	}
	
	/**
	 * Load the level, collections of Sprite and attach items, blocks and monster to the scene.
	 * @param content
	 * @param scene
	 */
	public void loadLevel(ContentManager content, SpriteGroup scene) {
		if (!this.loaded) {
			BufferedReader reader = null;
			StringBuilder jsonString = new StringBuilder();
	
			try {
				reader = new BufferedReader(new FileReader("Content/Platformer/levels/level_" + this.levelId + ".json"));
				String line;
				while((line = reader.readLine()) != null) {
					jsonString.append(line);
				}
				reader.close();
				
				JSONObject json = new JSONObject(jsonString.toString());
				
				// 1 - Create and add background and layers
				int layerId = json.getInt("layerId");
				this.layers[0] = new Sprite("img/Backgrounds/Layer0_" + layerId + ".png");
				this.layers[1] = new Sprite("img/Backgrounds/Layer1_" + layerId + ".png");
				this.layers[2] = new Sprite("img/Backgrounds/Layer2_" + layerId + ".png");
			
				for (Sprite layer : this.layers) {
					layer.loadContent(content);
					scene.add(layer);
				}
				
				// 2 - Create the level
				JSONArray jsonLevel = json.getJSONArray("level");
				JSONArray row = null;
				Sprite sprite = null;
				String assetName = "";
				
				for (int y = 0, ly = jsonLevel.length(); y < ly; y++) {
					row = jsonLevel.getJSONArray(y);
					
					for (int x = 0, lx = row.length(); x < lx; x++) {
						int id = row.getInt(x);
						assetName = "";
						
						// Start position of the player
						if (id == 1) {
							this.startPosition = new Vector2(x, y);
						}
						// Blocks and items case
						else if (id >= 2 && id <= 4) {
							assetName = getAssetName(id);
							sprite = new Sprite("img/Tiles/" + assetName + ".png");
							sprite.loadContent(content);
							sprite.setPosition(x * 40, y * 32);
							scene.add(sprite);
						
							if (id == 2) {
								sprite.setName("exit");
								this.items.add(sprite);
							}
							else {
								sprite.setName("block");
								this.blocks.add(sprite);
							}
						}
						else if (id == 5 || id == 6) { 
							sprite = new Gem(id);
							sprite.loadContent(content);
							sprite.setPosition(x * 40, y * 32);
							scene.add(sprite);
							this.items.add(sprite);
						}
						// Monsters case
						else if (id > 7 && id < 11) {
							assetName = getAssetName(id);
							sprite = new Sprite("img/Monsters/" + assetName + ".png");
							sprite.loadContent(content);
							sprite.setPosition(x * 32, y * 32);
							//this.monsters.add(sprite);
							// scene.add(sprite);
						}
					}
				}
				
				this.blocksSize = this.blocks.size();
				this.itemsSize = this.items.size();
				this.monstersSize = this.monsters.size();
			} 
			catch (IOException e) {
				System.out.println("[Level] Can't load level " + this.levelId);
			}
			this.loaded = true;
		}
	}
	
	public void reload(ContentManager content, SpriteGroup scene, int levelId) {
		scene.clear();
		this.levelId = levelId;
		this.loaded = false;
		this.loadLevel(content, scene);
	}
	
	/**
	 * Helper to gets the correct asset name.
	 * @param id
	 * @return
	 */
	private String getAssetName(int id) {
		String assetName = "";
		switch (id) {
			case 2: assetName = "Exit"; break;
			case 3: assetName = getRandomBlockName(); break;
			case 4: assetName = "Platform"; break;
			case 5: assetName = "Gem"; break;
			case 6: assetName = "YellowGem"; break;
			case 7: assetName = "MonsterA"; break;
			case 8: assetName = "MonsterB"; break;
			case 9: assetName = "MonsterC" ;break;
			case 10: assetName = "MonsterD"; break;
		}
		return assetName;
	}
	
	/**
	 * Gets a random block name.
	 * @return
	 */
	private String getRandomBlockName() {
		return ("BlockA" + random.nextInt(6)).toString();
	}
	
	// ---
	// --- Read only getters
	// ---
	public final int getWidth() {
		return width;
	}

	public final int getHeight() {
		return height;
	}

	public final int getLevelId() {
		return levelId;
	}

	public final ArrayList<Sprite> getBlocks() {
		return blocks;
	}

	public final ArrayList<Sprite> getItems() {
		return items;
	}

	public final ArrayList<Sprite> getMonsters() {
		return monsters;
	}

	public final Vector2 getStartPosition() {
		return startPosition;
	}

	// ---
	// --- Safe getters to gets collections size.
	// ---
	
	public final int getBlocksSize() {
		return blocksSize;
	}

	public final int getItemsSize() {
		return itemsSize;
	}

	public final int getMonstersSize() {
		return monstersSize;
	}
}
