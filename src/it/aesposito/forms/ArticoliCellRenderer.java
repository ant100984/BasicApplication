package it.aesposito.forms;

import it.aesposito.application.Constants;
import it.aesposito.jstore.bean.ArticoloForView;
import it.aesposito.jstore.forms.MyArticoliTableModel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ArticoliCellRenderer extends Component implements TableCellRenderer {

        @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
            
            
            MyArticoliTableModel atm = (MyArticoliTableModel)table.getModel();
            ArticoloForView a = (ArticoloForView)atm.getRows().get(row);
            
            JLabel c = new JLabel();
            c.setOpaque(true);
            c.setText((String)value);
            c.setFont(Constants.TABLES_FONT);
            
            if(a.getTipologiaArticolo() == 1){
            
                if(a.getGiacenza() == 0)
                    c.setBackground(Color.decode(Constants.GIACENZA_ZERO_COLOR));
                else if(a.getGiacenza() < a.getScarsita())
                    c.setBackground(Color.decode(Constants.GIACENZA_SOTTOSCORTA_COLOR));
                else if(row % 2 != 0)
                    c.setBackground(Color.decode(Constants.ODD_ROW_BACKGROUND));
                else
                    c.setBackground(Color.white);
            }else{
                
                if(row % 2 != 0)
                    c.setBackground(Color.decode(Constants.ODD_ROW_BACKGROUND));
                else
                    c.setBackground(Color.white);
            }
            
            if(isSelected)
                c.setBackground(Color.decode(Constants.SELECTED_ROW_BACKGROUND));
            
            return c;
	}
}
