package it.aesposito.forms;

import it.aesposito.application.Constants;
import it.aesposito.jstore.bean.ArticoloForView;
import it.aesposito.jstore.bean.Fatture;
import it.aesposito.jstore.forms.MyArticoliTableModel;
import it.aesposito.jstore.forms.VenditeTableModel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class VenditeCellRenderer extends Component implements TableCellRenderer {

        @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
            
            
            VenditeTableModel vtm = (VenditeTableModel)table.getModel();
            Fatture f = (Fatture)vtm.getRows().get(row);
            
            JLabel c = new JLabel();
            c.setOpaque(true);
            c.setText((String)value);
            c.setFont(Constants.TABLES_FONT);
            
            if(f.getDtmEmissione() == null)
                c.setBackground(Color.decode(Constants.RICEVUTA_NON_EMESSA_COLOR));
            else 
                c.setBackground(Color.decode(Constants.RICEVUTA_EMESSA_COLOR));
                        
            if(isSelected)
                c.setBackground(Color.decode(Constants.SELECTED_ROW_BACKGROUND));
            
            return c;
	}
}
