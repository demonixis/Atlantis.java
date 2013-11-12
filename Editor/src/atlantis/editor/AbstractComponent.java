package atlantis.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractComponent extends JPanel {
	private static final long serialVersionUID = -5509008304558721571L;
	protected JLabel title;
	protected JPanel container;
	
	public AbstractComponent(String name, int width, int height) {
		Dimension dim = new Dimension(width, height);
		this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		this.setPreferredSize(dim);
		this.setSize(dim);
		this.title = new JLabel(name);
		this.add(this.title, BorderLayout.NORTH);
		
		this.container = new JPanel();
		this.container.setPreferredSize(new Dimension(width - 2, height - this.title.getHeight()));
		this.add(this.container, BorderLayout.CENTER);
	}
}
