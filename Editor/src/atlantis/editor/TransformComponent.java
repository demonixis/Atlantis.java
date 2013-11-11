package atlantis.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atlantis.framework.Vector3;

public class TransformComponent extends JPanel {
	private static final long serialVersionUID = 3820593234844534864L;
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
	
	public void Setup(Vector3 position, Vector3 rotation, Vector3 scale) {
		this.transformProperties[0].x.setText(String.valueOf(position.x));
		this.transformProperties[0].y.setText(String.valueOf(position.y));
		this.transformProperties[0].z.setText(String.valueOf(position.z));
		
		this.transformProperties[1].x.setText(String.valueOf(rotation.x));
		this.transformProperties[1].y.setText(String.valueOf(rotation.y));
		this.transformProperties[1].z.setText(String.valueOf(rotation.z));
		
		this.transformProperties[2].x.setText(String.valueOf(scale.x));
		this.transformProperties[2].y.setText(String.valueOf(scale.y));
		this.transformProperties[2].z.setText(String.valueOf(scale.z));
	}
	
	class TransformLine extends JPanel {
		private static final long serialVersionUID = -5143179592272375443L;
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
