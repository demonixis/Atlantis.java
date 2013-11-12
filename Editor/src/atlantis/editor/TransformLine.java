package atlantis.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TransformLine extends JPanel implements ActionListener {
	private static final long serialVersionUID = -5143179592272375443L;
	protected JLabel title;
	protected JTextField x;
	protected JTextField y;
	protected JTextField z;
	protected boolean lock;
	protected ITransformChanged eventHandler;
	
	public TransformLine(String title) {
		Dimension titleDim = new Dimension(50, 20);
		Dimension textFieldDim = new Dimension(40, 20);
		
		this.setPreferredSize(new Dimension(190, 30));
		this.title = new JLabel(title);
		this.title.setPreferredSize(titleDim);
		this.x = new JTextField("0");
		this.x.setPreferredSize(textFieldDim);
		this.x.addActionListener(this);
		this.y = new JTextField("0");
		this.y.setPreferredSize(textFieldDim);
		this.y.addActionListener(this);
		this.z = new JTextField("0");
		this.z.setPreferredSize(textFieldDim);
		this.z.addActionListener(this);
		
		this.setLayout(new FlowLayout());
		this.add(this.title);
		this.add(this.x);
		this.add(this.y);
		this.add(this.z);
		
		this.lock = false;
	}
	
	public void setup(float x, float y, float z) {
		this.lock = true;
		this.x.setText(Float.toString(x));
		this.y.setText(Float.toString(y));
		this.z.setText(Float.toString(z));
		this.lock = false;
	}
	
	public void addTransformChangedListener(ITransformChanged handler) {
		this.eventHandler = handler;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!this.lock && this.eventHandler != null) {
			this.eventHandler.OnPropertyChanged(this.title.getText(), Float.parseFloat(this.x.getText()), Float.parseFloat(this.y.getText()), Float.parseFloat(this.z.getText()));
		}
	}
}
