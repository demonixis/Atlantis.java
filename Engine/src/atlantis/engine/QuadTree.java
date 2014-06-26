// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.engine;

import java.util.ArrayList;
import atlantis.engine.graphics.ICollidable2;
import atlantis.framework.Rectangle;

/**
 * A QuadTree class for an optimised collision detection
 */
public class QuadTree {
	private int level;
	private int maxObjectsPerNode;
	private int maxSubLevels;
	private ArrayList<ICollidable2> objects;
	private Rectangle quadBounds;
	private QuadTree[] nodes;

	/**
	 * Create a new QuadTree
	 * @param level Start level
	 * @param quadBounds The size to use for the QuadTree
	 */
	public QuadTree(int level, Rectangle quadBounds) {
		this.maxSubLevels = 5;
		this.maxObjectsPerNode = 10;
		this.level = level;
		this.objects = new ArrayList<ICollidable2>();
		this.quadBounds = quadBounds;
		this.nodes = new QuadTree[4];

		for (int i = 0; i < 4; i++) {
			this.nodes[i] = null;
		}
	}

	/**
	 * Clear the quadtree and add collidables objects on it
	 * @param collidables An array of collidable objects
	 */
	public void begin(ICollidable2[] collidables) {
		this.clear();
		for(ICollidable2 collidable : collidables) {
			this.add(collidable);
		}
	}

	/**
	 * Test if a collidable object is in collision with collidable objects contained in the quadtree.
	 * @param collidable A collidable object.
	 * @return Return true if a collision exists, otherwise return false.
	 */
	public boolean testCandidate(ICollidable2 collidable) {
		return this.getCandidates(collidable).size() > 0 ? true : false;
	}

	/**
	 * Get all that can potentially collide with this collidableObject
	 * @param collidableObjectA collidable collidableObject to test
	 * @return An array of collidable elements
	 */
	public ArrayList<ICollidable2> getCandidates(ICollidable2 collidableObject) {
		ArrayList<ICollidable2> candidates = new ArrayList<ICollidable2>();

		int index = this.getNodeIndex(collidableObject.getBounds());

		// If the space is already splited we get node objects that can potentially collide with this collidableObject
		if (index > -1 && this.nodes[0] != null)
			candidates.addAll(this.nodes[index].getCandidates(collidableObject));

		// All remaining objects can potentially collide with this collidableObject
		candidates.addAll(this.objects);

		return candidates;
	}

	/**
	 * Clear all nodes of the Quadtree
	 */
	public void clear() {
		this.objects.clear();

		for (int i = 0; i < 4; i++) {
			if (this.nodes[i] != null) {
				this.nodes[i].clear();
				this.nodes[i] = null;
			}
		}
	}

	/**
	 * Split the Quadtree
	 */
	protected void split() {
		int subWidth = this.quadBounds.getWidth() / 2;
		int subHeight = this.quadBounds.getHeight() / 2;

		/* ----------------------- 
		 * | this.node[1] | this.node[0] |
		 * |----------------------
		 * | this.node[2] | this.node[3] |
		 * -----------------------
		 */

		this.nodes[0] = new QuadTree(this.level + 1, new Rectangle(this.quadBounds.x + subWidth, this.quadBounds.y, subWidth, subHeight));
		this.nodes[1] = new QuadTree(this.level + 1, new Rectangle(this.quadBounds.x, this.quadBounds.y, subWidth, subHeight));
		this.nodes[2] = new QuadTree(this.level + 1, new Rectangle(this.quadBounds.x, this.quadBounds.y + subHeight, subWidth, subHeight));
		this.nodes[3] = new QuadTree(this.level + 1, new Rectangle(this.quadBounds.x + subWidth, this.quadBounds.y + subHeight, subWidth, subHeight));
	}

	/**
	 * Gets the node index of the object. 
	 * @param boundingRectangle Rectangle of the object
	 * @return -1 if the object cannot completly fit whithin a child node then the node index between 0 and 3
	 */
	protected int getNodeIndex(Rectangle boundingRectangle) {
		int index = -1;

		float verticalMidPoint = this.quadBounds.x + (this.quadBounds.getWidth() / 2);
		float horizontalMidPoint = this.quadBounds.y + (this.quadBounds.getHeight() / 2);

		// Object can  fit within the top/bottom quadrants
		boolean topQuadrant = (boundingRectangle.y < horizontalMidPoint && boundingRectangle.y + boundingRectangle.getHeight() < horizontalMidPoint);
		boolean bottomQuadrant = boundingRectangle.y > horizontalMidPoint;

		if (boundingRectangle.x < verticalMidPoint && boundingRectangle.x + boundingRectangle.getWidth() < verticalMidPoint) {
			if (topQuadrant) {
				index = 1;
			}
			else if (bottomQuadrant) {
				index = 2;
			}
		}
		else if (boundingRectangle.x > verticalMidPoint)
		{
			if (topQuadrant) {
				index = 0;
			}
			else if (bottomQuadrant) {
				index = 3;
			}
		}

		return index;
	}

	/**
	 * Add a bounding rectangle of a collidable object.
	 * @param collidableObject The bounding rectangle of a collidable object.
	 */
	public void add(ICollidable2 collidableObject) {
		// If the Quadtree is already splited
		if (this.nodes[0] != null) {
			int index = this.getNodeIndex(collidableObject.getBounds());

			if (index > -1) {
				this.nodes[index].add(collidableObject);
				return;
			}
		}

		this.objects.add(collidableObject);

		// Split the space if we have too many objects. The limit is MaxLevels
		if (this.objects.size() > this.maxObjectsPerNode && this.level < this.maxSubLevels) {
			if (this.nodes[0] == null) {
				this.split();
			}

			int i = 0;
			int size = this.objects.size();

			while (i < size) {
				int index = this.getNodeIndex(this.objects.get(i).getBounds());
				if (index > -1) {
					// Add the object to the correct node en remove it from the its parent
					this.nodes[index].add(this.objects.get(i));
					this.objects.remove(i);
				}
				else {
					i++;
				}
			}
		}
	}
	
	
	// ---
	// --- Getters and setters
	// ---
	
	/**
	 * Gets or sets the number of object per node
	 */
	public int getMaxObjectsPerNode()
	{
		return this.maxObjectsPerNode;
	}

	/**
	 * Sets the maximum of objects per node.
	 * @param value The maximum objects per node.
	 */
	public void setMaxObjectsPerNode(int value) {
		if (value > 0) {
			this.maxObjectsPerNode = value;
		}
	}

	/**
	 * Gets or sets the number of sub level
	 */
	public int getMaxLevels()       {
		return this.maxSubLevels; 
	}

	/**
	 * Sets the maximum depth of a node.
	 * @param value
	 */
	public void setMaxLevels(int value) {
		if (value > 0) {
			this.maxSubLevels = value;
		}
	}
}
