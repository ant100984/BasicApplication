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
import it.aesposito.forms.MyComboFormItem;
import it.aesposito.forms.MyListItemsForm;
import it.aesposito.forms.MyTextInput;
import it.aesposito.forms.StandardButtons;
import it.aesposito.forms.StandardCellRenderer;
import it.aesposito.jstore.bean.Articoli;
import it.aesposito.jstore.bean.ArticoloForView;
import it.aesposito.jstore.bean.RiepilogoArticoli;
import it.aesposito.utils.Utils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.ibatis.session.SqlSession;
/**
 *
 * @author Antonio
 */
public class ListaPrestazioniForm extends MyListItemsForm{
    private MyTextInput descrizione;
    private MyComboFormItem categoria;
    private List<FormItem> filters;
    private boolean showButtons = true; 
    private RiepilogoArticoli riepilogo;
    
    public ListaPrestazioniForm(Container father, Integer formId,final MainApplicationForm parent_frame, String title,boolean showButtons){
        super(father, formId, parent_frame, title);
        this.form_mode = FormModes.READ_ONLY_MODE;
        this.showButtons = showButtons;
        
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
                    ((MainApplicationForm)parent_frame).openPrestazioneForm(((Articoli)table.getModel().getValueAt(r, table.getColumnCount())).getId());
                }
                
                if(e.getClickCount() == 1){
                    myTable.addAllSelection();
                    
                    Object value = table.getValueAt(r, c);
                    
                    if(value instanceof JButton){
                        ((JButton)value).doClick();
                        myTable.resetSelection();
                        refreshInfoComponents();
                    }
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
        descrizione = new MyTextInput("descrizione", String.class, "Descrizione", false, FormItemType.TEXTINPUT, 50, "", true);
        ((JTextField)descrizione.getField()).getDocument().addDocumentListener(dl);
        filters.add(descrizione);
        categoria = new MyComboFormItem("categoria", Integer.class, "Categoria", false, FormItemType.COMBOBOX, 0,true,Combos.getCategorieComboModel(2));
        ((JComboBox)categoria.getField()).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    myTable.resetSelection();
                    refreshInfoComponents();
                }
            }
        });
        
        filters.add(categoria);
        
        super.initComponents();
        
        refreshInfoComponents();
    }
    
    public ListaPrestazioniForm(Container father, Integer formId,final MainApplicationForm parent_frame, String title){
        this(father,formId,parent_frame,title,true);
    }
    
    @Override
    public final void refreshInfoComponents() {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                final List<ArticoloForView> lista = loadList();
                
                myTable.setModel(new PrestazioniTableModel(lista, parent_frame));
                
                myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                myTable.getColumnModel().getColumn(0).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(0).setPreferredWidth(200);
                myTable.getColumnModel().getColumn(1).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(1).setPreferredWidth(100);
                myTable.getColumnModel().getColumn(2).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(2).setPreferredWidth(30);
                myTable.getColumnModel().getColumn(3).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(3).setPreferredWidth(20);
                myTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonCellRenderer());
                myTable.getColumnModel().getColumn(4).setPreferredWidth(10);
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

                resultsLabel.setText("<html>Trovate <b>" + riepilogo.getNumResults() + "</b> prestazioni</html>");
                
                refreshTabPagesInfo();
                }
            });
    }

    public List<ArticoloForView> loadList() {
        
        List<ArticoloForView> articoliList = new ArrayList<ArticoloForView>();
        
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(ListaPrestazioniForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return articoliList;
        }
        
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("descrizione", "%" + descrizione.getStringValue() + "%");
        params.put("categoria", categoria.getIntegerValue());
        params.put("tipologiaArticolo", 2);
        params.put("offset",(currPage-1) * Constants.TAB_NUM_ITEMS_PER_PAGE);
        params.put("limit", Constants.TAB_NUM_ITEMS_PER_PAGE);
        
        riepilogo = session.selectOne("custom_Articoli.getRiepilogoArticoli",params);
        
        calculateNumPages(riepilogo.getNumResults());
        
        articoliList = session.selectList("custom_Articoli.getArticoli",params);
        
        return articoliList;
    }
    
    @Override
    public List<FormItem> getFormItems() {
        return filters;
    }

    @Override
    public void openInsertDialog() {
        ((MainApplicationForm)parent_frame).openPrestazioneForm(0);
    }
    
}
