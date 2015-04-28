package utilities;


import java.awt.Color;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import renderer.MyHeaderRenderer;
import renderer.MyTableCellRenderer;
import utilities.SwingUtility;

public class MyTable extends JTable {

	private static final long serialVersionUID = 1L;

	public MyTable(){
		super();
		init();
	}
	
	public MyTable(AbstractTableModel model){
		super(model);
		init();
	}
	
	public void init(){
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setFont(SwingUtility.fnt_label2);
		getTableHeader().setForeground(Color.red);
		getTableHeader().setDefaultRenderer(new MyHeaderRenderer());
		setOpaque(true);
		setRowHeight(36);
		//setFont(SwingUtility.fnt_data);
		setFont(SwingUtility.fnt_label3);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setDefaultRenderer(Object.class, new MyTableCellRenderer());
	}
	
}
