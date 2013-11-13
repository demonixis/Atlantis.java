package atlantis.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LightComponent extends AbstractComponent {
	private static final long serialVersionUID = -5326443397655486409L;
	protected JTextField intensity;
	protected JPanel color;
	protected JCheckBox enableLight;
	protected JCheckBox enableFlatShading;
	protected ILightChanged handler;
	
	public LightComponent() {
		super("Light", 195, 150);
	
		Dimension dim = new Dimension(this.getWidth() - 2, 20);
		Dimension labelDim = new Dimension(50, 20);
		Dimension textDim = new Dimension(100, 20);
		Dimension cbDim = new Dimension(150, 20);
		
		JPanel pl1 = new JPanel();
		pl1.setPreferredSize(dim);
		pl1.setLayout(new FlowLayout());
		JLabel l1 = new JLabel("Intensity");
		l1.setPreferredSize(labelDim);
		pl1.add(l1);
		
		this.intensity = new JTextField("1.0");
		this.intensity.setPreferredSize(textDim);
		pl1.add(this.intensity);
		this.container.add(pl1);
		
		this.color = new JPanel();
		this.color.setBackground(Color.white);
		this.color.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		this.color.setPreferredSize(textDim);
		
		JPanel pl2 = new JPanel();
		pl2.setPreferredSize(dim);
		pl2.setLayout(new FlowLayout());
		JLabel l2 = new JLabel("Color");
		l2.setPreferredSize(labelDim);
		pl2.add(l2);
		pl2.add(color);
		this.container.add(pl2);
		
		JPanel pl3 = new JPanel();
		pl3.setPreferredSize(new Dimension(this.getWidth() - 2, 50));
		pl3.setLayout(new FlowLayout());
		this.enableLight = new JCheckBox("Enable");
		this.enableLight.setPreferredSize(cbDim);
		pl3.add(this.enableLight);
		this.enableFlatShading = new JCheckBox("Flat Shading");
		this.enableFlatShading.setPreferredSize(cbDim);
		pl3.add(this.enableFlatShading);
		this.container.add(pl3);
	}
	
	public void addLightChangedListener(ILightChanged handler) {
		this.handler = handler;
	}
	
	public void setup(float intensity, Color color) {
		this.intensity.setText(Float.toString(intensity));
		this.color.setBackground(color);
	}
}
