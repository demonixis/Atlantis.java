package atlantis.editor;

import java.awt.BorderLayout;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class SceneTreeComponent extends AbstractComponent {
	private static final long serialVersionUID = -4435784583641020050L;
	protected JTree sceneTree;

	public SceneTreeComponent() {
		super("Scene", 195, 200);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		this.sceneTree = new JTree(root);
		this.container.add(this.sceneTree, BorderLayout.CENTER);
	}
}
