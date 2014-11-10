/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.application.Constants;
import static it.aesposito.application.Constants.LISTA_ARTICOLI_FORM_HEIGHT;
import static it.aesposito.application.Constants.LISTA_ARTICOLI_FORM_WIDTH;
import static it.aesposito.application.Constants.LISTA_ARTICOLI_VENDITA_FORM_HEIGHT;
import static it.aesposito.application.Constants.LISTA_ARTICOLI_VENDITA_FORM_WIDTH;
import it.aesposito.db.SQLMapClientFactory;
import it.aesposito.forms.ArticoliListUpdater;
import it.aesposito.forms.ButtonCellRenderer;
import it.aesposito.forms.FormItem;
import it.aesposito.forms.FormItemType;
import it.aesposito.forms.FormModes;
import it.aesposito.forms.ArticoliCellRenderer;
import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.MyCheckFormItem;
import it.aesposito.forms.MyComboFormItem;
import it.aesposito.forms.MyListItemsForm;
import it.aesposito.forms.MyTextInput;
import it.aesposito.forms.StandardButtons;
import it.aesposito.jstore.bean.ApplicationProperties;
import it.aesposito.jstore.bean.Articoli;
import it.aesposito.jstore.bean.ArticoloForView;
import it.aesposito.jstore.bean.Listino;
import it.aesposito.jstore.bean.RiepilogoArticoli;
import it.aesposito.jstore.dao.ArticoliMapper;
import it.aesposito.jstore.dao.ListinoMapper;
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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.ibatis.session.SqlSession;
/**
 *
 * @author Antonio
 */
public class ListaArticoliForm extends MyListItemsForm{
    private ScortePanel scortePanel;
    private JPanel detailsPanelContainer;
    
    private VenditePanel venditePanel;
    private ListiniPanel listiniPanel;
    
    private MyTextInput codice;
    private MyTextInput descrizione;
    private MyComboFormItem categoria;
    private MyCheckFormItem sottoScorta;
    private MyCheckFormItem terminati;
    
    private List<FormItem> filters;
    private boolean showButtons = true;
    private List<ListaArticoliOptions> options;
    
    private ArticoliListUpdater listUpdater;
    
    private List<ArticoloForView> lista;
    private int tipo;
    private RiepilogoArticoli riepilogo;
    
    public ListaArticoliForm(Container father, Integer formId,final MainApplicationForm parent_frame, String title,boolean showButtons,ArticoliListUpdater listUpdater,List<ListaArticoliOptions> p_options,int tipo){
        super(father, formId, parent_frame, title);
        this.form_mode = FormModes.READ_ONLY_MODE;
        this.showButtons = showButtons;
        this.options = p_options;
        this.listUpdater = listUpdater;
        this.tipo = tipo;
        
        if(options.contains(ListaArticoliOptions.VENDITE_PANEL))
            this.setPreferredSize(new Dimension(LISTA_ARTICOLI_VENDITA_FORM_WIDTH, LISTA_ARTICOLI_VENDITA_FORM_HEIGHT));
        else
            this.setPreferredSize(new Dimension(LISTA_ARTICOLI_FORM_WIDTH, LISTA_ARTICOLI_FORM_HEIGHT));
        
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
                    if(options.contains(ListaArticoliOptions.SCORTE_PANEL))
                        ((MainApplicationForm)parent_frame).openArticoloForm(((Articoli)table.getModel().getValueAt(r, table.getColumnCount())).getId());
                    else if(options.contains(ListaArticoliOptions.VENDITE_PANEL)){
                            //venditePanel.addToVendita();
                    }
                    
                }
                
                if(e.getClickCount() == 1){
                    manageRowSelection(table,r,c);
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
        
        myTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"Enter");
        myTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),"Up");
        myTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),"Down");
        
        AbstractAction manageRowSelection = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int r = table.getSelectedRow();
                manageRowSelection(table,r,0);
            }
        };
        
        myTable.getActionMap().put("Enter", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int r = table.getSelectedRow();
                if(options.contains(ListaArticoliOptions.SCORTE_PANEL))
                    ((MainApplicationForm)parent_frame).openArticoloForm(((Articoli)table.getModel().getValueAt(r, table.getColumnCount())).getId());
                else if(options.contains(ListaArticoliOptions.VENDITE_PANEL))
                    venditePanel.addToVendita();
            }
        });

        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                myTable.resetSelection();
                lista = loadList();
                refreshInfoComponents();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                myTable.resetSelection();
                lista = loadList();
                refreshInfoComponents();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                myTable.resetSelection();
                lista = loadList();
                refreshInfoComponents();
            }
        };
        
        filters = new ArrayList<FormItem>();
        
        codice = new MyTextInput("codice", String.class, "Codice", false, FormItemType.TEXTINPUT, 50, "", true);
        ((JTextField)codice.getField()).getDocument().addDocumentListener(dl);
        if(tipo == 1){
            filters.add(codice);
        }
        
        descrizione = new MyTextInput("descrizione", String.class, "Descrizione", false, FormItemType.TEXTINPUT, 50, "", true);
        ((JTextField)descrizione.getField()).getDocument().addDocumentListener(dl);
        filters.add(descrizione);
        categoria = new MyComboFormItem("categoria", Integer.class, "Categoria", false, FormItemType.COMBOBOX, 0,true,Combos.getCategorieComboModel(tipo));
        ((JComboBox)categoria.getField()).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    myTable.resetSelection();
                    lista = loadList();
                    refreshInfoComponents();
                }
            }
        });
        
        filters.add(categoria);
        
        sottoScorta = new MyCheckFormItem("sottoScorta", Boolean.class, "Articoli sotto scorta", false, FormItemType.CHECKBOX, false, true);
        ((JCheckBox)sottoScorta.getField()).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                myTable.resetSelection();
                lista = loadList();
                refreshInfoComponents();
            }
        });

        terminati = new MyCheckFormItem("terminati", Boolean.class, "Articoli terminati", false, FormItemType.CHECKBOX, false, true);
        ((JCheckBox)terminati.getField()).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                myTable.resetSelection();
                lista = loadList();
                refreshInfoComponents();
            }
        });
        if(tipo == 1){
            if(options.contains(ListaArticoliOptions.FILTRI_GIACENZA)){
                filters.add(sottoScorta);
                filters.add(terminati);
            }
        }
        super.initComponents();
        
        ApplicationProperties prefs = new ApplicationProperties();
        try{
            prefs = Utils.getPreferences();
        }catch(IOException ex){
            
        }
        
        detailsPanelContainer = new JPanel();
        detailsPanelContainer.setLayout(new GridLayout(1,2));
        
        if(options.contains(ListaArticoliOptions.SCORTE_PANEL)){
            scortePanel = new ScortePanel(this);
            scortePanel.setEnabled(false);
            detailsPanelContainer.add(scortePanel);
        }
        
        if(options.contains(ListaArticoliOptions.VENDITE_PANEL)){
            venditePanel = new VenditePanel(listUpdater);
            venditePanel.setEnabled(false);
            
            listiniPanel = new ListiniPanel(tipo,listUpdater,tipo == 1 ? prefs.getListinoProdotti() : prefs.getListinoPrestazioni());
            
            detailsPanelContainer.add(venditePanel);
            detailsPanelContainer.add(listiniPanel);
        }
        
        mainContainer.add(detailsPanelContainer);

        loadList();
        
        refreshInfoComponents();
    }
    
    public void manageRowSelection(JTable table,int r,int c){
        ArticoloForView articolo = (ArticoloForView)table.getModel().getValueAt(r, table.getColumnCount());
                    
        if(options.contains(ListaArticoliOptions.SCORTE_PANEL)){
            scortePanel.setEnabled(true);
            scortePanel.reset();

            scortePanel.setArticolo(articolo);
        }

        if(options.contains(ListaArticoliOptions.VENDITE_PANEL)){
            venditePanel.setEnabled(true);
            venditePanel.reset();
            venditePanel.setArticolo(articolo);
            venditePanel.setIdx(r);
            venditePanel.setIdListino(listiniPanel.getListino().getIntegerValue());
            
            listiniPanel.setEnabled(true);
        }

        myTable.addAllSelection();

        Object value = table.getValueAt(r, c);

        if(value instanceof JButton){
            ((JButton)value).doClick();
            myTable.resetSelection();
            lista = loadList();
            refreshInfoComponents();
        }
    }
    
    public ListaArticoliForm(Container father, Integer formId,final MainApplicationForm parent_frame, String title,ArticoliListUpdater listUpdater,List<ListaArticoliOptions> options,int tipo){
        this(father,formId,parent_frame,title,true,listUpdater,options,tipo);
    }    
    
    @Override
    public final void refreshInfoComponents() {
        Listino l = new Listino();
        
        if(options.contains(ListaArticoliOptions.VENDITE_PANEL)){
            SqlSession session = null;

            try {
                session = SQLMapClientFactory.getSqlMapClient().openSession();
            } catch (IOException ex) {
                Utils.logError(ListaArticoliForm.class,ex);
                Utils.showErrorAlert("Non è stato possibile accedere al Database");
                return;
            }
            ListinoMapper lm = session.getMapper(ListinoMapper.class);
            l = lm.selectByPrimaryKey(listiniPanel.getListino().getIntegerValue());
        }else{
            l.setRicarico(0.0);
        }
        
        refreshInfoComponents(l);
        
        refreshTabPagesInfo();
    }
    
    public final void refreshInfoComponents(final Listino l) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                //lista = loadList();
                
                int totGiacenza = 0;
                Double totImporto = 0.0;
                Double totIva = 0.0;

                for (ArticoloForView articolo : lista) {
                    articolo.setImportoListino(CalculationsUtils.calcolaMaggiorazionePerc(articolo.getImportoUnitario(),l.getRicarico()));
                    articolo.setImportoVendita(CalculationsUtils.calcolaMaggiorazionePerc(articolo.getImportoListino(),articolo.getIva()));
                    
                    totGiacenza += articolo.getGiacenza();
                    totImporto += (CalculationsUtils.calcolaMaggiorazionePerc(articolo.getImportoUnitario()*articolo.getGiacenza(), articolo.getIva()));
                    totIva += CalculationsUtils.calcolaPerc(articolo.getImportoUnitario()*articolo.getGiacenza(), articolo.getIva());
                }
                
                if(options.contains(ListaArticoliOptions.VENDITE_TABLE)){
                    if(tipo == 1){
                        myTable.setModel(new ArticoliVenditaTableModel(lista, parent_frame, venditePanel));

                        myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        myTable.getColumnModel().getColumn(0).setCellRenderer(new ArticoliCellRenderer());
                        myTable.getColumnModel().getColumn(0).setPreferredWidth(80);
                        myTable.getColumnModel().getColumn(1).setCellRenderer(new ArticoliCellRenderer());
                        myTable.getColumnModel().getColumn(1).setPreferredWidth(280);
                        myTable.getColumnModel().getColumn(2).setCellRenderer(new ArticoliCellRenderer());
                        myTable.getColumnModel().getColumn(2).setPreferredWidth(80);
                        myTable.getColumnModel().getColumn(3).setCellRenderer(new ArticoliCellRenderer());
                        myTable.getColumnModel().getColumn(3).setPreferredWidth(40);
                        myTable.getColumnModel().getColumn(4).setCellRenderer(new ArticoliCellRenderer());
                        myTable.getColumnModel().getColumn(4).setPreferredWidth(40);
                        myTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonCellRenderer());
                        myTable.getColumnModel().getColumn(5).setPreferredWidth(40);
                        myTable.drawSelection();
                        myTable.removeColumn(myTable.getColumnModel().getColumn(6));
                        
                    }else if(tipo == 2){
                        
                        myTable.setModel(new PrestazioniVenditaTableModel(lista, parent_frame, venditePanel));

                        myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        myTable.getColumnModel().getColumn(0).setCellRenderer(new ArticoliCellRenderer());
                        myTable.getColumnModel().getColumn(0).setPreferredWidth(80);
                        myTable.getColumnModel().getColumn(1).setCellRenderer(new ArticoliCellRenderer());
                        myTable.getColumnModel().getColumn(1).setPreferredWidth(280);
                        myTable.getColumnModel().getColumn(2).setCellRenderer(new ArticoliCellRenderer());
                        myTable.getColumnModel().getColumn(2).setPreferredWidth(80);
                        myTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonCellRenderer());
                        myTable.getColumnModel().getColumn(3).setPreferredWidth(40);
                        myTable.drawSelection();
                        myTable.removeColumn(myTable.getColumnModel().getColumn(4));
                        
                    }
                }else{
                    
                    myTable.setModel(new ArticoliTableModel(lista, parent_frame));

                    myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    myTable.getColumnModel().getColumn(0).setCellRenderer(new ArticoliCellRenderer());
                    myTable.getColumnModel().getColumn(0).setPreferredWidth(80);
                    myTable.getColumnModel().getColumn(1).setCellRenderer(new ArticoliCellRenderer());
                    myTable.getColumnModel().getColumn(1).setPreferredWidth(280);
                    myTable.getColumnModel().getColumn(2).setCellRenderer(new ArticoliCellRenderer());
                    myTable.getColumnModel().getColumn(2).setPreferredWidth(130);
                    myTable.getColumnModel().getColumn(3).setCellRenderer(new ArticoliCellRenderer());
                    myTable.getColumnModel().getColumn(3).setPreferredWidth(50);
                    myTable.getColumnModel().getColumn(4).setCellRenderer(new ArticoliCellRenderer());
                    myTable.getColumnModel().getColumn(4).setPreferredWidth(30);
                    myTable.getColumnModel().getColumn(5).setCellRenderer(new ArticoliCellRenderer());
                    myTable.getColumnModel().getColumn(5).setPreferredWidth(30);
                    myTable.getColumnModel().getColumn(6).setCellRenderer(new ArticoliCellRenderer());
                    myTable.getColumnModel().getColumn(6).setPreferredWidth(50);
                    myTable.getColumnModel().getColumn(7).setCellRenderer(new ButtonCellRenderer());
                    myTable.getColumnModel().getColumn(7).setPreferredWidth(20);
                    myTable.getColumnModel().getColumn(8).setCellRenderer(new ButtonCellRenderer());
                    myTable.getColumnModel().getColumn(8).setPreferredWidth(20);
                    myTable.drawSelection();
                    myTable.removeColumn(myTable.getColumnModel().getColumn(9));
                    
                }
                if(options.contains(ListaArticoliOptions.SCORTE_PANEL)){
                    scortePanel.reset();
                    scortePanel.setEnabled(false);
                }
                
                if(options.contains(ListaArticoliOptions.VENDITE_PANEL)){
                    venditePanel.reset();
                    venditePanel.setEnabled(false);
                    venditePanel.setIdListino(l.getId());
                }
                
                if(lista.size() == 1)
                    myTable.setSelection(0);

                if(myTable.getMySelectedRows().size() == 1){
                    Integer sel = myTable.getMySelectedRows().get(0);
                    myTable.setRowSelectionInterval(sel, sel);
                    
                    if(options.contains(ListaArticoliOptions.SCORTE_PANEL)){
                        scortePanel.setEnabled(true);
                        scortePanel.setArticolo(lista.get(sel));
                    }
                    
                    if(options.contains(ListaArticoliOptions.VENDITE_PANEL)){
                        venditePanel.setEnabled(true);
                        venditePanel.setArticolo(lista.get(sel));
                        listiniPanel.setEnabled(true);
                    }
                }
                
                if(options.contains(ListaArticoliOptions.RESULT_INFO))
                    resultsLabel.setText("<html>Trovati <b>" + riepilogo.getNumResults() + "</b> articoli, Numero pezzi in giacenza <b>" + riepilogo.getGiacenzaTotale() + "</b> , Importo in giacenza <b>€ " + CalculationsUtils.round2Decimals(riepilogo.getImponibileTotale()) + " </b> + Iva <b>€ " + CalculationsUtils.round2Decimals(riepilogo.getIvaTotale()) + "</b> Totale <b>€ " + CalculationsUtils.round2Decimals(riepilogo.getImportoTotale()) + "</b></html>");
                }
            
            });
    }

    public List<ArticoloForView> loadList() {
        
        lista = new ArrayList<ArticoloForView>();
        
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(ListaArticoliForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return lista;
        }
        
	Map<String,Object> params = new HashMap<String,Object>();
        params.put("codice", codice.getStringValue()+"%");
        params.put("descrizione", "%" + descrizione.getStringValue() + "%");
        params.put("categoria", categoria.getIntegerValue());
        params.put("tipologiaArticolo", tipo);
        params.put("sottoScorta", sottoScorta.isSelected());
        params.put("terminati", terminati.isSelected());
        params.put("offset",(currPage-1) * Constants.TAB_NUM_ITEMS_PER_PAGE);
        params.put("limit", Constants.TAB_NUM_ITEMS_PER_PAGE);
        
        riepilogo = session.selectOne("custom_Articoli.getRiepilogoArticoli",params);
        
        calculateNumPages(riepilogo.getNumResults());
        
        lista = session.selectList("custom_Articoli.getArticoli",params);
        
        return lista;
    }
    
    @Override
    public List<FormItem> getFormItems() {
        return filters;
    }

    @Override
    public void openInsertDialog() {
        ((MainApplicationForm)parent_frame).openArticoloForm(0);
    }

    void updateGiacenza(Integer idListino, Articoli articolo) {
        //lista.get(idx).setGiacenza(lista.get(idx).getGiacenza() - quantita);
                
        SqlSession session = null;

        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(ListaArticoliForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return;
        }
        ListinoMapper lm = session.getMapper(ListinoMapper.class);
        ArticoliMapper am = session.getMapper(ArticoliMapper.class);
        
        Listino l = lm.selectByPrimaryKey(listiniPanel.getListino().getIntegerValue());

        am.updateByPrimaryKeySelective(articolo);
        session.commit();
        session.close();
        
        loadList();
        refreshInfoComponents(l);
    }
    
    public enum ListaArticoliOptions{
        SCORTE_PANEL, VENDITE_PANEL, RESULT_INFO, VENDITE_TABLE,FILTRI_GIACENZA
    }
}
