package atlantis.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LightComponent extends AbstractComponent {
	private static final long serialVersionUID = -5326443397655486409L;
	protected JTextField intensity;
	protected JPanel color;
	protected ILightChanged handler;
	
	public LightComponent() {
		super("Light", 195, 70);
	
		this.intensity = new JTextField("1.0");
	
		JPanel pl1 = new JPanel();
		pl1.setLayout(new FlowLayout());
		JLabel l1 = new JLabel("Intensity");
		pl1.add(l1);
		pl1.add(this.intensity);
		this.container.add(pl1);
		
		this.color = new JPanel();
		this.color.setBackground(Color.white);
		this.color.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		this.color.setPreferredSize(new Dimension(20, 20));
		
		JPanel pl2 = new JPanel();
		pl2.setLayout(new FlowLayout());
		JLabel l2 = new JLabel("Color");
		pl2.add(l2);
		pl2.add(color);
		this.container.add(pl2);
	}
	
	public void addLightChangedListener(ILightChanged handler) {
		this.handler = handler;
	}
	
	public void setup(float intensity, Color color) {
		this.intensity.setText(Float.toString(intensity));
		this.color.setBackground(color);
	}
}
