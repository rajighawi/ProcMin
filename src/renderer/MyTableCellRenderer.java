package renderer;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import utilities.DateUtility;
import utilities.SwingUtility;
import utilities.Utility;

public class MyTableCellRenderer extends DefaultTableCellRenderer {
	static final long serialVersionUID = 0l;

	Border selectedBorder   = new MatteBorder(1, 1, 1, -1, Color.RED);
	Border unselectedBorder = new MatteBorder(-1, 1, -1, -1, Color.BLUE);
	
	public MyTableCellRenderer(){
		setOpaque(true); 
	}
	
	public Component getTableCellRendererComponent( JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {			
		setHorizontalAlignment(JLabel.CENTER);		
		if(column == 0){
			setFont(SwingUtility.fnt_label2);
			setBackground(new Color(210,240,120));
			setBorder(BorderFactory.createRaisedBevelBorder());		
		} else {
			setFont(SwingUtility.fnt_label2); 
			if (isSelected) {
				setBackground(SwingUtility.lightCyan3);
				setBorder(selectedBorder);
			} else {
				setBackground(row%2==0?SwingUtility.lightGrey:Color.WHITE);
				setBorder(unselectedBorder);
			}
		}		
		if(value instanceof String) {
			setText("  "+value.toString());	
			setIcon(null);
		} else if(value instanceof Integer) {
			Integer integer = (Integer) value;
			setText("  "+Utility.formatINT(integer));	
			setIcon(null);
		} else if(value instanceof Double) {
			Double d = (Double) value;
			setText(d==0?"0":String.format("%-7.4f", d));	
			setIcon(null);
		} else if(value instanceof Date) {
			setText("  "+DateUtility.formatDate((Date)value));	
			setIcon(null);
		} else if(value instanceof ImageIcon) {
			ImageIcon uri = (ImageIcon) value;
			setText("");	
			setIcon(uri);	
		} else {
			if(value==null){
				setText("");
			} else {
				setText(value.toString());
			}
			setIcon(null);
		}				
		return this;
	}
	
	

}
