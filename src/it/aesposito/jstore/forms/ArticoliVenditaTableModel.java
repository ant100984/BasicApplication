/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.MyTable;
import it.aesposito.forms.MyTableColumn;
import static it.aesposito.application.Constants.DATE_SDF;
import it.aesposito.forms.MyButtonTableColumn;
import it.aesposito.jstore.bean.ArticoloForView;
import it.aesposito.utils.CalculationsUtils;
import it.aesposito.utils.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Antonio
 */
public class ArticoliVenditaTableModel extends MyArticoliTableModel{
    private List<MyTableColumn> columns;
    private MainApplicationForm parent_frame;
    private VenditaManager vm;
    
    public ArticoliVenditaTableModel(List<ArticoloForView> rows, MainApplicationForm parent_frame,VenditaManager vm){
        this.parent_frame = parent_frame;
        this.columns = new ArrayList<MyTableColumn>();
        this.vm = vm;
        columns.add(new MyTableColumn("Codice", String.class, "codice",100));
        columns.add(new MyTableColumn("Descrizione", String.class, "descrizione",400));
        columns.add(new MyTableColumn("Categoria", String.class, "categoriaForView",100));
        columns.add(new MyTableColumn("Importo Unitario", String.class, "importoVendita","€ ","",20));
        columns.add(new MyTableColumn("Giacenza", String.class, "giacenza","","pz",20));
        columns.add(new MyButtonTableColumn("", JButton.class,15));
        columns.add(new MyTableColumn("", String.class, "",0));
        
        super.rows = rows;
    }
    
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return columns.get(columnIndex).getName();
    }
    
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
            case 1:
            case 2:
            case 3: 
            case 4: return getHumanReadable(rows.get(rowIndex),columns.get(columnIndex));
            case 5: JButton button = new JButton("",new ImageIcon(getClass()
                            .getClassLoader().getResource("ok.png")));
                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        vm.addToVendita();
                    }
                });

                return button;
            case 6: return rows.get(rowIndex);
        }
        
        return "";
    }
    
    protected String getHumanReadable(Object obj, MyTableColumn field){
        try {
            Method m = obj.getClass().getMethod("get" + Utils.capitalizeFirstLetter(field.getBeanDataMapping()), null);
            Object result = m.invoke(obj, new Object[0]);
            if(result == null) return "";
            if(result instanceof String) return field.getPrefisso() + (String)result + field.getSuffisso();
            if(result instanceof Date) return field.getPrefisso() + DATE_SDF.format((Date)result) + field.getSuffisso();
            if(result instanceof Integer) return field.getPrefisso() + (Integer)result + field.getSuffisso();
            if(result instanceof Double) return field.getPrefisso() + CalculationsUtils.round2Decimals((Double)result) + field.getSuffisso();
            return result.toString();
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(MyTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(MyTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MyTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MyTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MyTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "####";
    }
}
