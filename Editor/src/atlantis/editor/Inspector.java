package atlantis.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import atlantis.framework.Vector3;

public class Inspector extends JPanel {
	private static final long serialVersionUID = -4807953315895255574L;
	protected TransformComponent transformComponent;
	protected JTree sceneTree;
	
	public Inspector() {
		this.setPreferredSize(new Dimension(200, 600));
		
		this.transformComponent = new TransformComponent();
		this.add(this.transformComponent);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Scene");
		this.sceneTree = new JTree(root);
		this.add(this.sceneTree, BorderLayout.SOUTH);
	}
	
	public void SetupTransform(Vector3 position, Vector3 rotation, Vector3 scale) {
		
	}
}