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
import it.aesposito.forms.ArticoliCellRenderer;
import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.MyComboFormItem;
import it.aesposito.forms.MyListItemsForm;
import it.aesposito.forms.MyTextInput;
import it.aesposito.forms.StandardButtons;
import it.aesposito.forms.StandardCellRenderer;
import it.aesposito.jstore.bean.Listino;
import it.aesposito.jstore.bean.ListinoForView;
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
public class ListaListiniForm extends MyListItemsForm{
    private MyComboFormItem tipologia;
    private MyTextInput descrizione;
    private List<FormItem> filters;
    private int numResult;
    
    public ListaListiniForm(Container father, Integer formId, final MainApplicationForm parent_frame, String title){
        super(father, formId, parent_frame, title);
        this.form_mode = FormModes.READ_ONLY_MODE;
        
        standardButtons.add(StandardButtons.INSERT);
        standardButtons.add(StandardButtons.CLOSE);
        
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
                    Listino listino = (Listino)table.getModel().getValueAt(r, 4);
                    ((MainApplicationForm)parent_frame).openListinoForm(listino.getId(),listino.getTipologiaListino());
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
        tipologia = new MyComboFormItem("tipologiaListino",  Integer.class, "Tipologia Listino", false, FormItemType.COMBOBOX, -1,true,Combos.getTipologiaArticoloComboModel(Combos.ComboTipoArticoloConf.DESCRIZIONE));
        ((JComboBox)tipologia.getField()).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    myTable.resetSelection();
                    refreshInfoComponents();
                }
            }
        });
        filters.add(tipologia);
        super.initComponents();
        
        refreshInfoComponents();
    }
    
    @Override
    public final void refreshInfoComponents() {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                final List<ListinoForView> lista = loadList();
                
                myTable.setModel(new ListiniTableModel(lista, parent_frame));
                
                myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                myTable.getColumnModel().getColumn(0).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                myTable.getColumnModel().getColumn(1).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(1).setPreferredWidth(250);
                myTable.getColumnModel().getColumn(2).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(2).setPreferredWidth(50);
                myTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonCellRenderer());
                myTable.getColumnModel().getColumn(3).setPreferredWidth(10);
                myTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonCellRenderer());
                myTable.getColumnModel().getColumn(4).setPreferredWidth(10);
                myTable.drawSelection();
                myTable.removeColumn(myTable.getColumnModel().getColumn(5));
                
                if(lista.size() == 1)
                    myTable.setSelection(0);

                if(myTable.getMySelectedRows().size() == 1){
                    Integer sel = myTable.getMySelectedRows().get(0);
                    myTable.setRowSelectionInterval(sel, sel);
                }
                
                resultsLabel.setText("<html>Trovati <b>" + numResult + "</b> listini</html>");
                
                refreshTabPagesInfo();
            }
        });
    }

    public List<ListinoForView> loadList() {
        
        List<ListinoForView> listiniList = new ArrayList<ListinoForView>();
        
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(ListaListiniForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return listiniList;
        }
        
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("descrizione", "%" + descrizione.getStringValue() + "%");
        params.put("tipologia", tipologia.getIntegerValue());
        params.put("offset",(currPage-1) * Constants.TAB_NUM_ITEMS_PER_PAGE);
        params.put("limit", Constants.TAB_NUM_ITEMS_PER_PAGE);
        
        numResult = session.selectOne("custom_Listini.getCountListini", params);
        
        calculateNumPages(numResult);
        
        listiniList = session.selectList("custom_Listini.getListini", params);
        
        return listiniList;
    }
    
    @Override
    public List<FormItem> getFormItems() {
        return filters;
    }

    @Override
    public void openInsertDialog() {
        ((MainApplicationForm)parent_frame).openListinoForm(0,-1);
    }
    
}
