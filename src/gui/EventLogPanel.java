package gui;

import graph.dependencyGraph.DependencyGraph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import petri_nets.PetriNet;
import main.Global;
import main.LogAnalyzer;
import main.Trace;
import utilities.FileUtility;
import utilities.SwingUtility;

import com.google.common.collect.HashBasedTable;

public class EventLogPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
		
	SquareTable      footprintTable       = new SquareTable();
	SquareTableModel footprintTableModel  = new SquareTableModel();
	SquareTable      directSuccTable      = new SquareTable();
	SquareTableModel directSuccTableModel = new SquareTableModel();
	SquareTable      dependencyTable      = new SquareTable();
	SquareTableModel dependencyTableModel = new SquareTableModel();
	
	JTabbedPane tabbedPane = new JTabbedPane();
	
	TextPanel textPanel = new TextPanel("Event Log", TextPanel.VERTICAL_LAYOUT);
	
	JButton btnAlpha;
	JButton btnHrstc = new JButton("Heuristic Miner");
	JTextField txtThrDirSucc  = new JTextField(2);
	JTextField txtThrDependcy = new JTextField(2);
	
	LogAnalyzer analyzer = null;
	
	public EventLogPanel(){
		super(new BorderLayout());
		
		add(textPanel, BorderLayout.CENTER);		
		add(bottomPanel(), BorderLayout.PAGE_END);
		init();
	}
	
	public JPanel toolBar(){
		
		btnAlpha = new JButton("Mining a Petri Net using Alpha Algorithm");
		btnAlpha.setPreferredSize(new Dimension(360, 36));
		btnAlpha.addActionListener(this);
		btnAlpha.setFont(SwingUtility.fnt_label1);
		
		btnHrstc = new JButton("Mining a Dependency Graph");
		btnHrstc.setPreferredSize(new Dimension(360, 36));
		btnHrstc.addActionListener(this);
		btnHrstc.setFont(SwingUtility.fnt_label1);
		
		JLabel lblThrDirSucc = new JLabel("Threshold (Direct Succesion)");
		JLabel lblThrDependency = new JLabel("Threshold (Dependency)");
		lblThrDirSucc.setFont(SwingUtility.fnt_label1);
		lblThrDependency.setFont(SwingUtility.fnt_label1);
		txtThrDirSucc.setFont(SwingUtility.fnt_label1);
		txtThrDirSucc.setText("2");
		txtThrDependcy.setFont(SwingUtility.fnt_label1);
		txtThrDependcy.setText("0.7");		
		
		JPanel p1 = new JPanel(new GridBagLayout());			
		p1.add(btnAlpha, SwingUtility.myC(0, 0));
		
		JPanel p2 = new JPanel(new GridBagLayout());
		int i = 0; int j = 0;		 
		p2.add(btnHrstc,        SwingUtility.myC(i++, j, 4, 1));
		
		i = 0; j++;
		p2.add(lblThrDirSucc,   SwingUtility.myC(i++, j));
		p2.add(txtThrDirSucc,   SwingUtility.myC(i++, j));
		p2.add(lblThrDependency,SwingUtility.myC(i++, j));
		p2.add(txtThrDependcy,  SwingUtility.myC(i++, j));
		
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(SwingUtility.myBorder("Operations"));
		
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setFont(SwingUtility.fnt_label1);
		
		tabPane.addTab("Alpha Miner", p1);
		tabPane.addTab("Heuristic Miner", p2);
		
		p.add(tabPane, BorderLayout.CENTER);
		
		return p;
	}
	
	public JPanel bottomPanel(){
		JPanel p = new JPanel(new BorderLayout());		
		
		JScrollPane footprintSP = new JScrollPane(footprintTable);
		footprintSP.setPreferredSize(new Dimension(640, 320));	
		JScrollPane directSuccSP = new JScrollPane(directSuccTable);
		directSuccSP.setPreferredSize(new Dimension(640, 320));
		JScrollPane dependencySP = new JScrollPane(dependencyTable);
		dependencySP.setPreferredSize(new Dimension(640, 320));
		
		tabbedPane.setFont(SwingUtility.fnt_label1);
		
		tabbedPane.addTab("Footprint", 		   footprintSP);
		tabbedPane.addTab("Direct Succession", directSuccSP);
		tabbedPane.addTab("Dependency",		   dependencySP);
		
		p.add(tabbedPane, BorderLayout.CENTER);		 
		p.add(toolBar(),  BorderLayout.PAGE_END);		 
		return p;
	}
	
	public void loadFootprint(HashBasedTable<Character, Character, String> footprint){
		footprintTableModel.loadFootprint(footprint);
		footprintTable.setModel(footprintTableModel);
	}
	
	public void loadDirectSucc(HashBasedTable<Character, Character, Integer> directSucc){
		directSuccTableModel.loadDirectSucc(directSucc);
		directSuccTable.setModel(directSuccTableModel);
	}
	
	public void loadDependency(HashBasedTable<Character, Character, Double> dependency){
		dependencyTableModel.loadDependency(dependency);
		dependencyTable.setModel(dependencyTableModel);
	}
	
	public void init(){		
		String text = FileUtility.readUTFFile(new File("data/sample1.txt"));		 
		textPanel.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src==btnAlpha){
			if(analyzer!=null){
				PetriNet petriNet = analyzer.makeWorkflowNet();
				Global.getInstance().getMainFrame().loadPetriNet(petriNet);
			}
		} else if(src==btnHrstc){
			int tauDS = Integer.parseInt(txtThrDirSucc.getText()); 
			double tauDP = Double.parseDouble(txtThrDependcy.getText());
			if(analyzer!=null){
				DependencyGraph graph =analyzer.makeDependencyGraph(tauDS, tauDP);
				Global.getInstance().getMainFrame().loadDependencyGraph(graph);
			}
		}		
	}
	
	public void analyze(){
		String log = textPanel.getText();
		ArrayList<Trace> traces = Trace.parse(log);
		
		analyzer = new LogAnalyzer(traces);
		
		HashBasedTable<Character, Character, String> footprint = analyzer.getFootprint();
		loadFootprint(footprint);
		
		HashBasedTable<Character, Character, Integer> directSucc = analyzer.getDirectSuccessionTable();
		loadDirectSucc(directSucc);
		
		HashBasedTable<Character, Character, Double> dependency = analyzer.getDependencyTable();
		loadDependency(dependency);
		
		System.out.println(traces);
	}
	
	
}
