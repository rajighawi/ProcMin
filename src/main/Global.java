package main;

import gui.MainFrame;

import java.util.HashMap;

import javax.swing.JFileChooser;

public class Global {
	
	private static Global global;
	
	private MainFrame mainFrame;

	private JFileChooser fileChooser;
	
	private HashMap<String, String> properties;
	
	private String report;
		
	private Global() {
		global = this;
	}

	public static Global getInstance() {
		if (global == null)
			return new Global();
		return global;
	}

	public void close() {
		global = null;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	 
	public HashMap<String, String> getProperties() {
		return properties;
	}

	public void setProperties(HashMap<String, String> properties) {
		this.properties = properties;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	
	public void appendToReport(String msg) {
		this.report += msg;
	}
	
}
