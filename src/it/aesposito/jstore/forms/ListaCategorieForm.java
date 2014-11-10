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
import it.aesposito.forms.MyListItemsForm;
import it.aesposito.forms.MyTextInput;
import it.aesposito.forms.StandardButtons;
import it.aesposito.forms.StandardCellRenderer;
import it.aesposito.jstore.bean.Categorie;
import it.aesposito.jstore.bean.CategorieExample;
import it.aesposito.jstore.dao.CategorieMapper;
import it.aesposito.utils.Utils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.ibatis.session.SqlSession;
/**
 *
 * @author Antonio
 */
public class ListaCategorieForm extends MyListItemsForm{
    private MyTextInput categoria;
    private List<FormItem> filters;
    private Integer tipo;
    private int numResults;
    
    public ListaCategorieForm(Container father, Integer formId, final MainApplicationForm parent_frame, String title, final Integer tipo){
        super(father, formId, parent_frame, title);
        this.form_mode = FormModes.READ_ONLY_MODE;
        this.tipo = tipo;
        
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
                    ((MainApplicationForm)parent_frame).openCategoriaForm(((Categorie)table.getModel().getValueAt(r, 2)).getId(),tipo);
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
        categoria = new MyTextInput("categoria", String.class, "Categoria", false, FormItemType.TEXTINPUT, 50, "", true);
        ((JTextField)categoria.getField()).getDocument().addDocumentListener(dl);
        filters.add(categoria);
        
        super.initComponents();
        
        refreshInfoComponents();
    }
    
    @Override
    public final void refreshInfoComponents() {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                final List<Categorie> lista = loadList();
                
                myTable.setModel(new CategorieTableModel(lista, parent_frame,tipo));
                
                myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                myTable.getColumnModel().getColumn(0).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(0).setPreferredWidth(200);
                myTable.getColumnModel().getColumn(1).setCellRenderer(new ButtonCellRenderer());
                myTable.getColumnModel().getColumn(1).setPreferredWidth(10);
                myTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonCellRenderer());
                myTable.getColumnModel().getColumn(2).setPreferredWidth(10);
                myTable.drawSelection();
                myTable.removeColumn(myTable.getColumnModel().getColumn(3));
                
                if(lista.size() == 1)
                    myTable.setSelection(0);

                if(myTable.getMySelectedRows().size() == 1){
                    Integer sel = myTable.getMySelectedRows().get(0);
                    myTable.setRowSelectionInterval(sel, sel);
                }
                
                resultsLabel.setText("<html>Trovati <b>" + numResults + "</b> elementi</html>");
                
                refreshTabPagesInfo();
            }
        });
    }

    public List<Categorie> loadList() {
        
        List<Categorie> categorieList = new ArrayList<Categorie>();
        
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(ListaCategorieForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return categorieList;
        }
        
	CategorieMapper cm = session.getMapper(CategorieMapper.class);
        CategorieExample ce = new CategorieExample();
        CategorieExample.Criteria cr = ce.createCriteria();
        
        cr.andTipologiaArticoloEqualTo(tipo);
        
        if( !"".equals(categoria.getStringValue())){
           cr.andDescrizioneLikeInsensitive("%" + categoria.getStringValue() + "%");
        }
        
        numResults = cm.countByExample(ce);
        
        calculateNumPages(numResults);
        
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("descrizione", "%" + categoria.getStringValue() + "%");
        params.put("tipologia_articolo",tipo);
        params.put("offset",(currPage-1) * Constants.TAB_NUM_ITEMS_PER_PAGE);
        params.put("limit", Constants.TAB_NUM_ITEMS_PER_PAGE);
        
        categorieList = session.selectList("custom_Categorie.getCategorie", params);
        
        return categorieList;
    }
    
    @Override
    public List<FormItem> getFormItems() {
        return filters;
    }

    @Override
    public void openInsertDialog() {
        ((MainApplicationForm)parent_frame).openCategoriaForm(0,tipo);
    }
    
}
