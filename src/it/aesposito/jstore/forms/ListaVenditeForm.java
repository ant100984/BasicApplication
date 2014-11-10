/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.application.Constants;
import it.aesposito.db.SQLMapClientFactory;
import it.aesposito.forms.ButtonCellRenderer;
import it.aesposito.forms.FormItem;
import it.aesposito.forms.FormItemType;
import it.aesposito.forms.FormModes;
import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.MyDatePickerFormItem;
import it.aesposito.forms.MyListItemsForm;
import it.aesposito.forms.StandardButtons;
import it.aesposito.forms.VenditeCellRenderer;
import it.aesposito.jstore.bean.Fatture;
import it.aesposito.jstore.bean.FattureExample;
import it.aesposito.jstore.bean.RiepilogoVendite;
import it.aesposito.jstore.dao.FattureMapper;
import it.aesposito.utils.CalculationsUtils;
import it.aesposito.utils.Utils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.ibatis.session.SqlSession;
import org.jdesktop.swingx.JXDatePicker;
/**
 *
 * @author Antonio
 */
public class ListaVenditeForm extends MyListItemsForm{
    private MyDatePickerFormItem dataDa;
    private MyDatePickerFormItem dataA;
    private List<FormItem> filters;
    private boolean showButtons = true; 
    private int numResults;
    private RiepilogoVendite riepilogo;
    
    public ListaVenditeForm(Container father, Integer formId,final MainApplicationForm parent_frame, String title,boolean showButtons){
        super(father, formId, parent_frame, title);
        this.form_mode = FormModes.READ_ONLY_MODE;
        this.showButtons = showButtons;
        this.setPreferredSize(new Dimension(Constants.LISTA_VENDITE_FORM_WIDTH,Constants.LISTA_VENDITE_FORM_HEIGHT));
        
        if(showButtons){
            standardButtons.add(StandardButtons.INSERT);
            standardButtons.add(StandardButtons.CLOSE);
        }
        
        MouseListener ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JTable table = (JTable)e.getSource();
                int r = table.getSelectedRow();
                
                int c = table.getColumnModel().getColumnIndexAtX(e.getX());
                
                if(e.getClickCount() == 2){
                    ((MainApplicationForm)parent_frame).openVenditaForm(((Fatture)table.getModel().getValueAt(r, table.getColumnModel().getColumnCount())).getId());
                }
                
                if(e.getClickCount() == 1){
                    myTable.addAllSelection();
                    
                    Object value = table.getValueAt(r, c);
                    
                    if(value instanceof JButton)
                        ((JButton)value).doClick();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        };
        
        myTable.addMouseListener(ml);
        
        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                myTable.resetSelection();
                refreshInfoComponents();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                myTable.resetSelection();
                refreshInfoComponents();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                myTable.resetSelection();
                refreshInfoComponents();
            }
        };
        
        filters = new ArrayList<FormItem>();
        dataDa = new MyDatePickerFormItem("dataDa", Date.class, "Data da", false, FormItemType.DATEPICKER, null, true);
        ((JXDatePicker)dataDa.getField()).getEditor().getDocument().addDocumentListener(dl);
        filters.add(dataDa);
        
        dataA = new MyDatePickerFormItem("dataA", Date.class, "Data a", false, FormItemType.DATEPICKER, null, true);
        ((JXDatePicker)dataA.getField()).getEditor().getDocument().addDocumentListener(dl);
        filters.add(dataA);
        
        super.initComponents();
        refreshInfoComponents();
    }
    
    public ListaVenditeForm(Container father, Integer formId,final MainApplicationForm parent_frame, String title){
        this(father,formId,parent_frame,title,true);
    }
    
    @Override
    public final void refreshInfoComponents() {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                final List<Fatture> lista = loadList();
                
                myTable.setModel(new VenditeTableModel(lista, parent_frame));
                
                myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                myTable.getColumnModel().getColumn(0).setCellRenderer(new VenditeCellRenderer());
                myTable.getColumnModel().getColumn(0).setPreferredWidth(180);
                myTable.getColumnModel().getColumn(1).setCellRenderer(new VenditeCellRenderer());
                myTable.getColumnModel().getColumn(1).setPreferredWidth(180);
                myTable.getColumnModel().getColumn(2).setCellRenderer(new VenditeCellRenderer());
                myTable.getColumnModel().getColumn(2).setPreferredWidth(30);
                myTable.getColumnModel().getColumn(3).setCellRenderer(new VenditeCellRenderer());
                myTable.getColumnModel().getColumn(3).setPreferredWidth(30);
                myTable.getColumnModel().getColumn(4).setCellRenderer(new VenditeCellRenderer());
                myTable.getColumnModel().getColumn(4).setPreferredWidth(30);
                myTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonCellRenderer());
                myTable.getColumnModel().getColumn(5).setPreferredWidth(10);
                myTable.drawSelection();
                myTable.removeColumn(myTable.getColumnModel().getColumn(6));
                
                if(lista.size() == 1)
                    myTable.setSelection(0);

                if(myTable.getMySelectedRows().size() == 1){
                    Integer sel = myTable.getMySelectedRows().get(0);
                    myTable.setRowSelectionInterval(sel, sel);
                }

                refreshTabPagesInfo();
                
                resultsLabel.setText("<html><b>" + riepilogo.getNumRicevute() + "</b> ricevute emesse Imponibile <b>€ " + CalculationsUtils.round2Decimals(riepilogo.getImponibile()) + "</b> Iva <b>€ " + CalculationsUtils.round2Decimals(riepilogo.getIva()) + "</b> Totale <b>€ " + CalculationsUtils.round2Decimals(riepilogo.getTotale()) + "</b><br>" +
                                           "<b>" + (riepilogo.getNumResults() - riepilogo.getNumRicevute()) + "</b> ricevute non emesse Imponibile <b>€ " + CalculationsUtils.round2Decimals(riepilogo.getImponibileNoRicevuta()) + "</b> Iva <b>€ " + CalculationsUtils.round2Decimals(riepilogo.getIvaNoRicevuta()) + "</b> Totale <b>€ " + CalculationsUtils.round2Decimals(riepilogo.getTotaleNoRicevuta()) + "</b><br>" +
                                           "<b>" + riepilogo.getNumResults() + "</b> vendite "+
                                           "Totale imponibile <b>€ " + (CalculationsUtils.round2Decimals(riepilogo.getImponibile()+riepilogo.getImponibileNoRicevuta())) + "</b> " + 
                                           "Totale iva <b>€ " + (CalculationsUtils.round2Decimals(riepilogo.getIva()+riepilogo.getIvaNoRicevuta())) + "</b> " +
                                           "Totale <b>€ " + (CalculationsUtils.round2Decimals(riepilogo.getTotale()+riepilogo.getTotaleNoRicevuta())) + "</b></html>");
                }
            });
    }

    public List<Fatture> loadList() {
        
        List<Fatture> fattureList = new ArrayList<Fatture>();
        
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(ListaVenditeForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return fattureList;
        }
        
        Map<String,Object> params = new HashMap<String,Object>();
        
        try{
            params.put("dataDa", dataDa.getDateValue());
            params.put("dataA", dataA.getDateValue());
        }catch(ParseException ex){
            Utils.showErrorAlert("Si è verificato un errore");
        }
        
        params.put("offset",(currPage-1) * Constants.TAB_NUM_ITEMS_PER_PAGE);
        params.put("limit", Constants.TAB_NUM_ITEMS_PER_PAGE);
        
        riepilogo = session.selectOne("custom_Vendite.getRiepilogoVendite", params);
        calculateNumPages(riepilogo.getNumResults());
        
        fattureList = session.selectList("custom_Vendite.getVendite", params);
        
        return fattureList;
    }
    
    @Override
    public List<FormItem> getFormItems() {
        return filters;
    }

    @Override
    public void openInsertDialog() {
        ((MainApplicationForm)parent_frame).openVenditaForm(0);
    }

    public void resetSelection() {
        myTable.resetSelection();
    }
    
}
