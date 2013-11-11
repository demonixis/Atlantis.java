package atlantis.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Inspector extends JPanel {
	protected TransformComponent transformComponent;
	
	public Inspector() {
		this.setPreferredSize(new Dimension(200, 600));
		
		this.transformComponent = new TransformComponent();
		this.add(this.transformComponent);
	}
}