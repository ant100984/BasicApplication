package it.aesposito.forms;

import it.aesposito.application.Constants;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class StandardCellRenderer extends Component implements TableCellRenderer {

        @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
            
            
            JLabel c = new JLabel();
            c.setOpaque(true);
            c.setText((String)value);
            c.setFont(Constants.TABLES_FONT);
            
            if(row % 2 != 0)
                c.setBackground(Color.decode(Constants.ODD_ROW_BACKGROUND));
            else
                c.setBackground(Color.white);
            
            if(isSelected)
                c.setBackground(Color.decode(Constants.SELECTED_ROW_BACKGROUND));
            
            return c;
	}
}
