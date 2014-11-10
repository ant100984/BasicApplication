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
import it.aesposito.forms.MyCheckFormItem;
import it.aesposito.forms.MyComboFormItem;
import it.aesposito.forms.MyDatePickerFormItem;
import it.aesposito.forms.MyListItemsForm;
import it.aesposito.forms.RiepilogoVendutoCellRenderer;
import it.aesposito.forms.StandardButtons;
import it.aesposito.forms.VenditeCellRenderer;
import it.aesposito.jstore.bean.Fatture;
import it.aesposito.jstore.bean.RiepilogoVendite;
import it.aesposito.jstore.bean.RiepilogoVenduto;
import it.aesposito.jstore.bean.SommarioVenduto;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.ibatis.session.SqlSession;
import org.jdesktop.swingx.JXDatePicker;
/**
 *
 * @author Antonio
 */
public class RiepilogoVendutoForm extends MyListItemsForm{
    private MyDatePickerFormItem dataDa;
    private MyDatePickerFormItem dataA;
    private MyComboFormItem categoria;
    private List<FormItem> filters;
    private boolean showButtons = true; 
    private int numResults;
    private SommarioVenduto riepilogo;
    private List<RiepilogoVenduto> riepilogoVendutoList;
    private MyCheckFormItem checkProdotti;
    private MyCheckFormItem checkPrestazioni;
    
    public RiepilogoVendutoForm(Container father, Integer formId,final MainApplicationForm parent_frame, String title,boolean showButtons){
        super(father, formId, parent_frame, title);
        this.form_mode = FormModes.READ_ONLY_MODE;
        this.showButtons = showButtons;
        this.setPreferredSize(new Dimension(Constants.RIEPILOGO_VENDUTO_FORM_WIDTH,Constants.RIEPILOGO_VENDUTO_FORM_HEIGHT));
        
        if(showButtons){
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
                loadList();
                refreshInfoComponents();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                myTable.resetSelection();
                loadList();
                refreshInfoComponents();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                myTable.resetSelection();
                loadList();
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
        
        categoria = new MyComboFormItem("categoria", Integer.class, "Categorie", false, FormItemType.COMBOBOX, 0,true,Combos.getCategorieComboModel());
        ((JComboBox)categoria.getField()).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    myTable.resetSelection();
                    loadList();
                    refreshInfoComponents();
                }
            }
        });
        
        filters.add(categoria);
        
        checkProdotti = new MyCheckFormItem("prodotti", Boolean.class, "Prodotti", false, FormItemType.CHECKBOX, true, true);
        ((JCheckBox)checkProdotti.getField()).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                myTable.resetSelection();
                loadList();
                refreshInfoComponents();
            }
        });
        checkPrestazioni = new MyCheckFormItem("prestazioni", Boolean.class, "Prestazioni", false, FormItemType.CHECKBOX, true, true);
        ((JCheckBox)checkPrestazioni.getField()).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                myTable.resetSelection();
                loadList();
                refreshInfoComponents();
            }
        });
        
        filters.add(checkPrestazioni);
        filters.add(checkProdotti);
        
        super.initComponents();
        loadList();
        refreshInfoComponents();
    }
    
    public RiepilogoVendutoForm(Container father, Integer formId,final MainApplicationForm parent_frame, String title){
        this(father,formId,parent_frame,title,true);
    }
    
    @Override
    public final void refreshInfoComponents() {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                myTable.setModel(new RiepilogoVendutoTableModel(riepilogoVendutoList, parent_frame));
                
                myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                myTable.getColumnModel().getColumn(0).setCellRenderer(new RiepilogoVendutoCellRenderer());
                myTable.getColumnModel().getColumn(0).setPreferredWidth(130);
                myTable.getColumnModel().getColumn(1).setCellRenderer(new RiepilogoVendutoCellRenderer());
                myTable.getColumnModel().getColumn(1).setPreferredWidth(80);
                myTable.getColumnModel().getColumn(2).setCellRenderer(new RiepilogoVendutoCellRenderer());
                myTable.getColumnModel().getColumn(2).setPreferredWidth(50);
                myTable.getColumnModel().getColumn(3).setCellRenderer(new RiepilogoVendutoCellRenderer());
                myTable.getColumnModel().getColumn(3).setPreferredWidth(200);
                myTable.getColumnModel().getColumn(4).setCellRenderer(new RiepilogoVendutoCellRenderer());
                myTable.getColumnModel().getColumn(4).setPreferredWidth(80);
                myTable.getColumnModel().getColumn(5).setCellRenderer(new RiepilogoVendutoCellRenderer());
                myTable.getColumnModel().getColumn(5).setPreferredWidth(80);
                myTable.getColumnModel().getColumn(6).setCellRenderer(new RiepilogoVendutoCellRenderer());
                myTable.getColumnModel().getColumn(6).setPreferredWidth(80);
                /*myTable.getColumnModel().getColumn(7).setCellRenderer(new RiepilogoVendutoCellRenderer());
                myTable.getColumnModel().getColumn(7).setPreferredWidth(80);
                myTable.getColumnModel().getColumn(8).setCellRenderer(new RiepilogoVendutoCellRenderer());
                myTable.getColumnModel().getColumn(8).setPreferredWidth(100);*/
                myTable.drawSelection();
                myTable.removeColumn(myTable.getColumnModel().getColumn(7));
                
                if(riepilogoVendutoList.size() == 1)
                    myTable.setSelection(0);

                if(myTable.getMySelectedRows().size() == 1){
                    Integer sel = myTable.getMySelectedRows().get(0);
                    myTable.setRowSelectionInterval(sel, sel);
                }

                refreshTabPagesInfo();
                
                resultsLabel.setText("<html>Prodotti <b>"+riepilogo.getNumeroProdotti()+"</b> Prestazioni <b>"+riepilogo.getNumeroPrestazioni()+"</b> "+
                                     "Costo acquisto totale <b>€ " + CalculationsUtils.round2Decimals(riepilogo.getCostoAcquistoTotale()) + "</b> Prezzo vendita totale <b>€ "+CalculationsUtils.round2Decimals(riepilogo.getPrezzoVenditaTotale())+"</b> "+
                                     "Guadagno totale <b>€ "+CalculationsUtils.round2Decimals(riepilogo.getGuadagnoTotale())+"</b></html>");/*+
                                     "Diff. Iva totale <b>€ "+CalculationsUtils.round2Decimals(riepilogo.getDiffIvaTotale())+"</b> Guadagno netto totale <b>€ "+CalculationsUtils.round2Decimals(riepilogo.getGuadagnoNettoTotale())+"</b></html>");*/
                }
            });
    }

    @Override
    public final List<RiepilogoVenduto> loadList() {
        
        riepilogoVendutoList = new ArrayList<RiepilogoVenduto>();
        
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(RiepilogoVendutoForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return riepilogoVendutoList;
        }
        
        Map<String,Object> params = new HashMap<String,Object>();
        
        try{
            params.put("dataDa", dataDa.getDateValue());
            params.put("dataA", dataA.getDateValue());
            params.put("categoria", categoria.getIntegerValue());
            params.put("prodotti", checkProdotti.isSelected());
            params.put("prestazioni", checkPrestazioni.isSelected());
        }catch(ParseException ex){
            Utils.showErrorAlert("Si è verificato un errore");
        }
        
        params.put("offset",(currPage-1) * Constants.TAB_NUM_ITEMS_PER_PAGE);
        params.put("limit", Constants.TAB_NUM_ITEMS_PER_PAGE);
        
        riepilogo = session.selectOne("custom_Vendite.getSommarioVenduto", params);
        calculateNumPages(riepilogo.getNumResults());
        
        riepilogoVendutoList = session.selectList("custom_Vendite.getRiepilogoVenduto", params);
        
        return riepilogoVendutoList;
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
