package atlantis.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import atlantis.engine.graphics3d.Camera;
import atlantis.engine.graphics3d.Mesh;
import atlantis.engine.graphics3d.Renderer;
import atlantis.engine.graphics3d.geometry.CubeGeometry;
import atlantis.engine.graphics3d.geometry.MeshGeometry;
import atlantis.engine.graphics3d.geometry.PlaneGeometry;
import atlantis.engine.graphics3d.geometry.PyramidGeometry;
import atlantis.framework.Vector3;
import atlantis.framework.graphics.RenderTarget2D;
import atlantis.framework.platform.IGameWindow;
import atlantis.framework.platform.IWindowRenderer;
import atlantis.framework.platform.JPanelRenderer;

public class EditorWindow extends JFrame implements IGameWindow, Runnable, ActionListener {
	private static final long serialVersionUID = 2755458791729206299L;
	private Thread gameThread;
	private RenderTarget2D renderTarget;
	private boolean isRunning;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newItemMenu;
	private JMenuItem openItemMenu;
	private JMenuItem saveItemMenu;
	private JMenuItem exitItemMenu;
	private JMenu sceneMenu;
	private JMenuItem cubeAddItem;
	private JMenuItem planeAddItem;
	private JMenuItem pyramidAddItem;
	private JMenuItem sceneSettingsItem;
	private JMenu aboutMenu;
	private JMenuItem aboutItemMenu;
	private JMenuItem aboutAtlantisMenu;
	private JPanelRenderer jpanelRenderer;
	private Inspector inspector;
	
	protected Renderer renderer;
	protected Camera camera;
	protected Mesh[] meshes;
	
	public EditorWindow() {
		this.isRunning = true;
		this.setTitle("Atlantis 3D Editor");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setRenderer(new JPanelRenderer());

		this.newItemMenu = new JMenuItem("New");
		this.openItemMenu = new JMenuItem("Open");
		this.saveItemMenu = new JMenuItem("Save");
		this.exitItemMenu = new JMenuItem("Exit");
		
		this.menuBar = new JMenuBar();
		this.fileMenu = new JMenu("File");
		this.fileMenu.add(this.newItemMenu);
		this.fileMenu.add(new JSeparator());
		this.fileMenu.add(this.openItemMenu);
		this.fileMenu.add(this.saveItemMenu);
		this.fileMenu.add(new JSeparator());
		this.fileMenu.add(this.exitItemMenu);
		this.menuBar.add(this.fileMenu);
		
		this.sceneMenu = new JMenu("Scene");
		this.cubeAddItem = new JMenuItem("Cube");
		this.cubeAddItem.addActionListener(this);
		this.planeAddItem = new JMenuItem("Plane");
		this.pyramidAddItem = new JMenuItem("Pyramid");
		this.sceneSettingsItem = new JMenuItem("Settings");
		this.sceneMenu.add(this.cubeAddItem);
		this.sceneMenu.add(this.planeAddItem);
		this.sceneMenu.add(this.pyramidAddItem);
		this.sceneMenu.add(new JSeparator());
		this.sceneMenu.add(this.sceneSettingsItem);
		this.menuBar.add(this.sceneMenu);
		
		this.aboutMenu = new JMenu("Help");
		this.aboutItemMenu = new JMenuItem("About the editor");
		this.aboutAtlantisMenu = new JMenuItem("About Atlantis Engine");
		this.aboutMenu.add(this.aboutItemMenu);
		this.aboutMenu.add(this.aboutAtlantisMenu);
		this.menuBar.add(this.aboutMenu);
		
		this.setJMenuBar(this.menuBar);
		
		this.setLayout(new BorderLayout());
		this.setRenderer(new JPanelRenderer(600, 600));
		this.renderTarget = new RenderTarget2D(600,  600);
		this.jpanelRenderer.addRenderTarget(this.renderTarget);
		
		this.inspector = new Inspector();
		this.add(inspector, BorderLayout.EAST);
	
		this.renderer = new Renderer(this.jpanelRenderer.getPreferredSize().width, this.jpanelRenderer.getPreferredSize().height);
		this.renderer.getLight().setPosition(new Vector3(0, -50, -50));
		//this.renderer.getLight().setEnabled(false);
		this.camera = new Camera();
		this.camera.position.z = 20;
		this.meshes = new Mesh[0];
		
		this.gameThread = new Thread(this);
		this.gameThread.start();
		this.setVisible(true);
	}

	public void run() {
		while(this.isRunning) {
			this.renderer.clear(Color.black);
			for (Mesh m : this.meshes) {
				m.rotate(0.005f, 0.005f, 0);
			}
			this.renderer.render(this.renderTarget.getGraphics(), this.camera, this.meshes);
			
			this.jpanelRenderer.repaint();
			
			try {
				Thread.sleep(10);
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleFullscreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRenderer(IWindowRenderer renderer) {
		this.jpanelRenderer = (JPanelRenderer) renderer;
		this.add(this.jpanelRenderer, BorderLayout.CENTER);		
	}

	@Override
	public JPanelRenderer getRenderer() {
		
		return this.jpanelRenderer;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MeshGeometry geometry = null;
		
		if (arg0.getSource() == this.cubeAddItem) {
			geometry = new CubeGeometry();
		}
		else if (arg0.getSource() == this.planeAddItem) {
			geometry = new PlaneGeometry();
		}
		else if (arg0.getSource() == this.pyramidAddItem) {
			geometry = new PyramidGeometry();
		}
		
		Mesh mesh = new Mesh("mesh", geometry);
		
		addMesh(mesh);
	}
	
	private void addMesh(Mesh mesh) {
		int currentSize = this.meshes.length;
		Mesh [] newArray = new Mesh[currentSize + 1];
		
		for (int i = 0, l = currentSize; i < l; i++) {
			newArray[i] = this.meshes[i];
		}
		
		newArray[currentSize] = mesh;
		this.meshes = newArray;
	}
}
