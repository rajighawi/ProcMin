package gui;
 

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import renderer.MyTableCellRenderer;
import utilities.MyTable;
import utilities.SwingUtility;


public class SquareTable extends MyTable {

	private static final long serialVersionUID = 1L;

	public SquareTable(){		
		setDefaultRenderer(Object.class, new MyTableCellRenderer());
		setFont(SwingUtility.fnt_label2);
	//	setAutoCreateRowSorter(true);		
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(true);
		addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				Point p = arg0.getPoint();
				boolean dblClick = arg0.getClickCount()==2;
			//	int col  = TablesTable.this.columnAtPoint(p);
				int line = SquareTable.this.rowAtPoint(p);
				if (line != -1) {					 
					if(dblClick){
						//
					}					
				}
			}
		});
		//updateColumnWidth();
	}

}
