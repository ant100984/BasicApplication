/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.MyTable;
import it.aesposito.forms.MyTableColumn;
import static it.aesposito.application.Constants.DATETIME_SDF;
import it.aesposito.jstore.bean.RiepilogoVenduto;
import it.aesposito.utils.CalculationsUtils;
import it.aesposito.utils.Utils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Antonio
 */
public class RiepilogoVendutoTableModel extends AbstractTableModel{
    private List<MyTableColumn> columns;
    private List<RiepilogoVenduto> rows;
    private MainApplicationForm parent_frame;
    
    public RiepilogoVendutoTableModel(List<RiepilogoVenduto> rows, MainApplicationForm parent_frame){
        this.parent_frame = parent_frame;
        this.columns = new ArrayList<MyTableColumn>();
        columns.add(new MyTableColumn("Data/Ora Vendita", String.class, "dtmVendita",180));
        columns.add(new MyTableColumn("Tipologia", String.class, "tipologia","","",180));
        columns.add(new MyTableColumn("Quantità", Integer.class, "quantita","","",15));
        columns.add(new MyTableColumn("Descrizione", String.class, "descrizione","","",180));
        columns.add(new MyTableColumn("Costo acquisto", Double.class, "costoAcquisto","€ ","",15));
        columns.add(new MyTableColumn("Prezzo venduto", Double.class, "importo","€ ","",15));
        columns.add(new MyTableColumn("Guadagno", Double.class, "guadagno","€ ","",15));
        /*columns.add(new MyTableColumn("Diff. Iva", Double.class, "diffIva","€ ","",15));
        columns.add(new MyTableColumn("Guadagno netto", Double.class, "guadagnoNetto","€ ","",15));*/
        columns.add(new MyTableColumn("", String.class, "",0));
        
        this.rows = rows;
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
            case 4:
            case 5:
            case 6: return getHumanReadable(rows.get(rowIndex),columns.get(columnIndex));
            case 7: return rows.get(rowIndex);
        }
        
        return "";
    }
    
    protected String getHumanReadable(Object obj, MyTableColumn field){
        try {
            Method m = obj.getClass().getMethod("get" + Utils.capitalizeFirstLetter(field.getBeanDataMapping()), null);
            Object result = m.invoke(obj, new Object[0]);
            if(result == null) return "";
            if(result instanceof String) return field.getPrefisso() + (String)result + field.getSuffisso();
            if(result instanceof Date) return field.getPrefisso() + DATETIME_SDF.format((Date)result) + field.getSuffisso();
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

    public List<RiepilogoVenduto> getRows() {
        return rows;
    }

    public void setRows(List<RiepilogoVenduto> rows) {
        this.rows = rows;
    }
    
}
