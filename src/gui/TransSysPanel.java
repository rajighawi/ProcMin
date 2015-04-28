package gui;

import graph.transSys.TransSysGraphPanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class TransSysPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	TransSysGraphPanel graphPanel = new TransSysGraphPanel();
	
	public TransSysPanel(){
		super(new BorderLayout());
		
		add(graphPanel, BorderLayout.CENTER);
		
	}
	
}
