package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import main.AnalyzerUtility;

import com.google.common.collect.HashBasedTable;

public class SquareTableModel extends AbstractTableModel {

	static final long serialVersionUID = 1l;	
	
	private String[] columnNames = { };
	private Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
 	
	public SquareTableModel(){
		super();
	}
	
	public void loadFootprint(HashBasedTable<Character, Character, String> footprint){
		ArrayList<Character> activities = new ArrayList<Character>(footprint.rowKeySet());
		Collections.sort(activities);
		int n = activities.size();				
		this.columnNames = new String[n+1];
		columnNames[0] = "";
		for (int i = 0; i < n; i++) {
			columnNames[i+1] = activities.get(i)+"";
		}		
		rows.removeAllElements();
		for (int i = 0; i < activities.size(); i++) {
			Vector<Object> newRow = new Vector<Object>();			
			Character x = activities.get(i);
			newRow.addElement(x);
			for (int j = 0; j < activities.size(); j++) {
				Character y = activities.get(j);
				String v = footprint.get(x, y);
				if(v.equals(AnalyzerUtility._to))   v = Character.toString((char)0x2192); //  
				if(v.equals(AnalyzerUtility._from)) v = Character.toString((char)0x2190); // 
				newRow.addElement(v);
			}			
			rows.addElement(newRow);
		}	
		fireTableChanged(null);
	}
	
	public void loadDirectSucc(HashBasedTable<Character, Character, Integer> directSucc){
		ArrayList<Character> activities = new ArrayList<Character>(directSucc.rowKeySet());
		Collections.sort(activities);
		int n = activities.size();				
		this.columnNames = new String[n+1];
		columnNames[0] = "";
		for (int i = 0; i < n; i++) {
			columnNames[i+1] = activities.get(i)+"";
		}		
		rows.removeAllElements();
		for (int i = 0; i < activities.size(); i++) {
			Vector<Object> newRow = new Vector<Object>();			
			Character x = activities.get(i);
			newRow.addElement(x);
			for (int j = 0; j < activities.size(); j++) {
				Character y = activities.get(j);
				Integer v = directSucc.get(x, y); 
				newRow.addElement(v);
			}			
			rows.addElement(newRow);
		}	
		fireTableChanged(null);
	}
	
	public void loadDependency(HashBasedTable<Character, Character, Double> dependency){
		ArrayList<Character> activities = new ArrayList<Character>(dependency.rowKeySet());
		Collections.sort(activities);
		int n = activities.size();				
		this.columnNames = new String[n+1];
		columnNames[0] = "";
		for (int i = 0; i < n; i++) {
			columnNames[i+1] = activities.get(i)+"";
		}		
		rows.removeAllElements();
		for (int i = 0; i < activities.size(); i++) {
			Vector<Object> newRow = new Vector<Object>();			
			Character x = activities.get(i);
			newRow.addElement(x);
			for (int j = 0; j < activities.size(); j++) {
				Character y = activities.get(j);
				Double v = dependency.get(x, y); 
				newRow.addElement(v);
			}			
			rows.addElement(newRow);
		}	
		fireTableChanged(null);
	}
		
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vector<Object> row = (Vector<Object>) rows.elementAt(rowIndex);
		return row.elementAt(columnIndex);				
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public Class<?> getColumnClass(int column) {
		return String.class;
	}

	public Object getSelectedObject(int line) {
		return rows.get(line);
	}

	public void setData(Vector<Vector<Object>> newData) {
		rows = newData;
	}
	
	public String getColumnName(int columnIndex) {
		if (columnNames[columnIndex] != null)
			return columnNames[columnIndex];
		else
			return "";
	}

	public int getRowCount() {
		return rows.size();
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public void clear(){
		rows = new Vector<Vector<Object>>();
		fireTableChanged(null);
	}
	
	
}
