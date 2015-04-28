package gui;

import graph.dependencyGraph.DependencyGraph;
import graph.dependencyGraph.DependencyGraphPanel;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import petri_nets.PetriNet;

import com.google.common.collect.HashBasedTable;

import main.Global;
import utilities.IconUtility;
import utilities.SwingUtility;

public class MainFrame extends JFrame{

	static final long serialVersionUID = 1l;
	
	public static final String version = "0.0.5";
	  
	TransSysPanel transSysPanel = new TransSysPanel();
	PetriNetPanel petriNetPanel = new PetriNetPanel();
	DependencyGraphPanel dependencyGraphPanel = new DependencyGraphPanel();
	
	EventLogPanel eventLogPanel = new EventLogPanel();
	
	JTabbedPane tabPane = new JTabbedPane();
	
	public MainFrame() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private void createAndShowGUI() {
		Global global = Global.getInstance();
		global.setMainFrame(this);	
		global.setFileChooser(new JFileChooser("src/data/"));
		
		SwingUtilities.updateComponentTreeUI(this);
		validate();
		
		JFrame frame = new JFrame("ProcMin " + version);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(IconUtility.logo.getImage());			
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
						 
		pane.add(centerPanel(), BorderLayout.CENTER);
		pane.add(leftPanel(),  BorderLayout.LINE_START);
				 
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		SwingUtility.center(frame);
		frame.setVisible(true);		
		 
	}
	
	public JPanel centerPanel(){
		JPanel panel = new JPanel(new BorderLayout());		
		
		tabPane.setFont(SwingUtility.fnt_label1);
		 
		tabPane.addTab("Transition Systems", transSysPanel); 
		tabPane.addTab("Petri Nets",         petriNetPanel);
		tabPane.addTab("Dependency Graph",   dependencyGraphPanel); 
		
		panel.add(tabPane,BorderLayout.CENTER);		
		return panel;
	}
	
	public JPanel leftPanel(){
		JPanel panel = new JPanel(new BorderLayout());		
		JTabbedPane tabPane2 = new JTabbedPane();
		tabPane2.setFont(SwingUtility.fnt_label1);
		 
		tabPane2.addTab("Event Log", eventLogPanel); 		 
		
		panel.add(tabPane2,BorderLayout.CENTER);		
		return panel;
	}
	
	// --------------------------------------------------------------------------
	
	public void loadFootprint(HashBasedTable<Character, Character, String> footprint){
		eventLogPanel.loadFootprint(footprint);
	}
	
	public void analyze(){
		eventLogPanel.analyze();
	}
	
	public void loadDependencyGraph(DependencyGraph graph){
		dependencyGraphPanel.showGraph(graph);
		tabPane.setSelectedComponent(dependencyGraphPanel);
	}
	
	public void loadPetriNet(PetriNet petriNet){
		petriNetPanel.go(petriNet);
		tabPane.setSelectedComponent(petriNetPanel);
	}
	
}
