package atlantis.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TransformComponent extends JPanel {
	protected TransformLine[] transformProperties;
	
	public TransformComponent() {
		this.setPreferredSize(new Dimension(200, 220));
		JLabel title = new JLabel("Transform");
		this.add(title, BorderLayout.NORTH);
		
		String[] names = { "Position", "Rotation", "Scale" };
		transformProperties = new TransformLine[3];
		for (int i = 0; i < 3; i++) {
			transformProperties[i] = new TransformLine(names[i]);
			this.add(transformProperties[i]);
		}
	}
	
	class TransformLine extends JPanel {
		protected JLabel title;
		protected JTextField x;
		protected JTextField y;
		protected JTextField z;
		
		public TransformLine(String title) {
			Dimension titleDim = new Dimension(50, 20);
			Dimension textFieldDim = new Dimension(40, 20);
			
			this.setPreferredSize(new Dimension(200, 30));
			this.title = new JLabel(title);
			this.title.setPreferredSize(titleDim);
			this.x = new JTextField("0");
			this.x.setPreferredSize(textFieldDim);
			this.y = new JTextField("0");
			this.y.setPreferredSize(textFieldDim);
			this.z = new JTextField("0");
			this.z.setPreferredSize(textFieldDim);
			
			this.setLayout(new FlowLayout());
			this.add(this.title);
			this.add(this.x);
			this.add(this.y);
			this.add(this.z);
		}
	}

}
