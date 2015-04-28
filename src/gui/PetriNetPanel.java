package gui;

import graph.petriNet.PetriNetGraphPanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import petri_nets.PetriNet;

public class PetriNetPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	PetriNetGraphPanel graphPanel = new PetriNetGraphPanel();
	 
	public PetriNetPanel(){
		super(new BorderLayout());
		
		add(graphPanel, BorderLayout.CENTER); 
	}
		
	public void go(PetriNet petriNet) {
		graphPanel.go(petriNet);
	}
	
}
