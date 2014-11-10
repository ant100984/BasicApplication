/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.application.Constants;
import it.aesposito.db.SQLMapClientFactory;
import it.aesposito.forms.ArticoliListUpdater;
import it.aesposito.forms.ButtonCellRenderer;
import it.aesposito.forms.FormButton;
import it.aesposito.forms.FormItem;
import it.aesposito.forms.FormModes;
import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.MyListItemsForm;
import it.aesposito.forms.StandardButtons;
import it.aesposito.forms.StandardCellRenderer;
import it.aesposito.jstore.bean.Articoli;
import it.aesposito.jstore.bean.ArticoloFattura;
import it.aesposito.jstore.bean.ArticoloFatturaExample;
import it.aesposito.jstore.bean.ArticoloFatturaForView;
import it.aesposito.jstore.bean.ArticoloForView;
import it.aesposito.jstore.bean.Fatture;
import it.aesposito.jstore.bean.Listino;
import it.aesposito.jstore.dao.ArticoloFatturaMapper;
import it.aesposito.jstore.dao.FattureMapper;
import it.aesposito.jstore.dao.ListinoMapper;
import it.aesposito.utils.Utils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import org.apache.ibatis.session.SqlSession;
import it.aesposito.jstore.forms.ListaArticoliForm.ListaArticoliOptions;
import it.aesposito.utils.CalculationsUtils;
import it.aesposito.utils.PdfUtils;
import java.awt.Color;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;
/**
 *
 * @author Antonio
 */
public class VenditaForm extends MyListItemsForm implements ArticoliListUpdater{
    private Integer idVendita;
    private Fatture fattura;
    
    private List<ArticoloFatturaForView> listaArticoli;
    private ListaArticoliForm articoliForm;
    private ListaArticoliForm prestazioniForm;
    private JLabel totImponibileLabel;
    private JLabel totIvaLabel;
    private JLabel totaleLabel;
    private Double totImponibile = 0.0;
    private Double totIva = 0.0;
    private JLabel dataEmissioneLabel;
    
    public VenditaForm(Container father, Integer formId, final MainApplicationForm parent_frame, String title, Integer p_idVendita){
        super(father, formId, parent_frame, title, false);
        this.form_mode = FormModes.DB_LIVE_MODE;
        this.idVendita = p_idVendita;
        this.setPreferredSize(new Dimension(Constants.VENDITA_FORM_WIDTH,Constants.VENDITA_FORM_HEIGHT));
        standardButtons.add(StandardButtons.CLOSE);
        
        nonStandardButtons.add(new FormButton("Ricevuta", new ImageIcon(getClass()
				.getClassLoader().getResource("print.png")), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(listaArticoli.size() == 0){
                    Utils.showWarningAlert("Includi almeno un articolo o una prestazione");
                    return;
                }
                
                if(JOptionPane.NO_OPTION == Utils.showConfirm("Vuoi stampare la ricevuta?")){
                    return;
                }
                
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setCopies(Constants.NUMERO_COPIE_RICEVUTA);
                PageFormat pf = job.defaultPage();
                Paper paper = new Paper();
                paper.setSize(800, 1500);
                paper.setImageableArea(0, 0, 800, 1500);
                pf.setPaper(paper);
                pf.setOrientation(PageFormat.PORTRAIT);
                PDDocument document = null;
                
                int res;
                int res1 = JOptionPane.YES_OPTION;
                
                SqlSession session = null;

                try {
                    session = SQLMapClientFactory.getSqlMapClient().openSession();
                } catch (IOException ex) {
                    Utils.logError(VenditaForm.class,ex);
                    Utils.showErrorAlert("Non è stato possibile accedere al Database");
                    return;
                }

                FattureMapper fm = session.getMapper(FattureMapper.class);
                
                Date oldDate = fattura.getDtmEmissione();
                
                do{
                    fattura.setDtmEmissione(new Date());
                    fm.updateByPrimaryKeySelective(fattura);
                    session.commit();
                    
                    InputStream in = null;
                    byte[] pdf = null;
                    try{
                        pdf = PdfUtils.getPdfRicevuta(idVendita, ".\\", "ricevuta.jasper");
                        in = new ByteArrayInputStream(pdf);
                    }catch(Exception ex){
                        Utils.showErrorAlert("Si è verificato un errore durante la stampa");
                        return;
                    }

                    try {
                        Utils.saveBytesAsFile(pdf, ".\\", "ricevuta.pdf");
                    } catch (IOException ex) {
                        Utils.showErrorAlert("Errore nel salvataggio del file di stampa");
                        return;
                    }
                    try{
                        document = PDDocument.load(in);
                        job.setPageable(new PDPageable(document, job));
                    }catch(IOException ex){
                        Utils.showErrorAlert("Si è verificato un errore durante la stampa");
                        return;
                    }catch(PrinterException ex){
                        Utils.showErrorAlert("Si è verificato un errore durante la stampa");
                        return;
                    }

                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        Logger.getLogger(VenditaForm.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    res = JOptionPane.showConfirmDialog(parent_frame, "La ricevuta è stata stampata correttamente?");
                    
                    if(res == JOptionPane.NO_OPTION)
                        res1 = JOptionPane.showConfirmDialog(parent_frame, "Vuoi ristamparla?");
                    
                }while((res == JOptionPane.NO_OPTION) && (res1 == JOptionPane.YES_OPTION));
                
                if(res == JOptionPane.CANCEL_OPTION || res1 == JOptionPane.CANCEL_OPTION || res1 == JOptionPane.NO_OPTION){
                    fattura.setDtmEmissione(oldDate);
                    fm.updateByPrimaryKey(fattura);
                    session.commit();
                }
                
                /*loadList();
                refreshInfoComponents();*/
                
                session.close();
                close();
                
            }

        }, null, new FormModes[]{FormModes.DB_LIVE_MODE}));
        
        nonStandardButtons.add(new FormButton("Annulla", new ImageIcon(getClass()
				.getClassLoader().getResource("trash.png")), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(listaArticoli.size() == 0){
                    Utils.showWarningAlert("La lista è vuota");
                    return;
                }
                
                int res = Utils.showConfirm("Sei sicuro di voler annullare la vendita? Non sarà possibile recuperarla");
                if(res == JOptionPane.YES_OPTION){
                    annullaVendita();
                    parent_frame.resetListaVenditeSelection();
                }
            }

        }, null, new FormModes[]{FormModes.DB_LIVE_MODE}));
        
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
                
                if(e.getClickCount() == 2 || e.getClickCount() == 1){
                    
                    Object value = table.getValueAt(r, c);
                    
                    if(value instanceof JButton){
                        ((JButton)value).doClick();
                        loadList();
                        refreshInfoComponents();
                        articoliForm.loadList();
                        articoliForm.refreshInfoComponents();
                    }else{
                        if(e.getClickCount() == 2){
                            ArticoloFatturaForView afv = ((ArticoloFatturaForView)table.getModel().getValueAt(r, table.getColumnModel().getColumnCount()));
                            Utils.showInfoAlert(afv.getDescrizione());
                        }
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
        
        super.initComponents();
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JTabbedPane tabs = new JTabbedPane();
        JPanel topAligned = new JPanel();
        
        topAligned.setLayout(new BoxLayout(topAligned,BoxLayout.PAGE_AXIS));
         topAligned.setAlignmentX(LEFT_ALIGNMENT);
         
        dataEmissioneLabel = new JLabel();
        dataEmissioneLabel.setForeground(Color.decode(Constants.DATA_EMISSIONE_COLOR));
        topAligned.add(dataEmissioneLabel);

        topAligned.add(tableContainer);
        
        totImponibileLabel = new JLabel("€ "+CalculationsUtils.round2Decimals(totImponibile));
        totIvaLabel = new JLabel("€ "+CalculationsUtils.round2Decimals(totIva));
        totaleLabel = new JLabel("€ "+CalculationsUtils.round2Decimals(totImponibile+totIva));
        
        totImponibileLabel.setFont(Constants.RIEPILOGO_FONT);
        totIvaLabel.setFont(Constants.RIEPILOGO_FONT);
        totaleLabel.setFont(Constants.RIEPILOGO_TOTALE_FONT);
        totaleLabel.setForeground(Color.decode(Constants.RIEPILOGO_TOTALE_COLOR));
                
        JLabel r1 = new JLabel("Imponibile :"); 
        JLabel r2 = new JLabel("Iva : ");
        JLabel r3 = new JLabel("TOTALE :");
        r1.setFont(Constants.RIEPILOGO_FONT);
        r2.setFont(Constants.RIEPILOGO_FONT);
        r3.setFont(Constants.RIEPILOGO_TOTALE_FONT);
        r3.setForeground(Color.decode(Constants.RIEPILOGO_TOTALE_COLOR));
        
        JPanel riepilogoPanel = new JPanel();
        riepilogoPanel.setLayout(new GridLayout(3,2));
        
        riepilogoPanel.add(r1);
        riepilogoPanel.add(totImponibileLabel);
        riepilogoPanel.add(r2);
        riepilogoPanel.add(totIvaLabel);
        riepilogoPanel.add(r3);
        riepilogoPanel.add(totaleLabel);
                
        topAligned.add(riepilogoPanel);
        
        articoliForm = new ListaArticoliForm(mainContainer, 0, parent_frame, "",false,this,Arrays.asList(new ListaArticoliOptions[]{ListaArticoliOptions.VENDITE_PANEL,ListaArticoliOptions.VENDITE_TABLE}),1);
        //prestazioniForm = new ListaPrestazioniForm(mainContainer, 0, parent_frame, "",false);
        prestazioniForm = new ListaArticoliForm(mainContainer, 0, parent_frame, "",false,this,Arrays.asList(new ListaArticoliOptions[]{ListaArticoliOptions.VENDITE_PANEL,ListaArticoliOptions.VENDITE_TABLE}),2);
        tabs.addTab("<html><body leftmargin=20 topmargin=10 marginwidth=20 marginheight=10><b>Prodotti</b></body></html>", articoliForm);
        tabs.addTab("<html><body leftmargin=20 topmargin=10 marginwidth=20 marginheight=10><b>Prestazioni</b></body></html>", prestazioniForm);
        
        container.add(topAligned);
        container.add(tabs);
        
        this.add(buttonsContainer);
        this.add(container);
        
        listaArticoli = new ArrayList<ArticoloFatturaForView>();
        if(idVendita > 0){
            loadList();
        }else
            fattura = new Fatture();
        refreshInfoComponents();
    }
    
    private void annullaVendita(){
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("idVendita", idVendita);
            session.update("custom_Articoli.ripristinaGiacenza",params);
        
            FattureMapper fm = session.getMapper(FattureMapper.class);
            fm.deleteByPrimaryKey(idVendita);
            
            ArticoloFatturaMapper afm = session.getMapper(ArticoloFatturaMapper.class);
            ArticoloFatturaExample afe = new ArticoloFatturaExample();
            afe.createCriteria().andFatturaEqualTo(idVendita);
            afm.deleteByExample(afe);
            
            session.commit();
        } catch (Exception ex) {
            Utils.logError(VenditaForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return;
        }finally{
            session.close();
            this.close();
        }
    }
    
    @Override
    public final void refreshInfoComponents() {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                totImponibile = 0.0;
                totIva = 0.0;
                for (ArticoloFatturaForView af : listaArticoli) {
                    totImponibile += af.getImponibile();
                    totIva += af.getIva();
                }
                
                totImponibileLabel.setText("€ "+CalculationsUtils.round2Decimals(totImponibile));
                totIvaLabel.setText("€ "+CalculationsUtils.round2Decimals(totIva));
                totaleLabel.setText("€ "+CalculationsUtils.round2Decimals(totImponibile+totIva));
                
                myTable.setModel(new VenditaTableModel(listaArticoli, parent_frame));
                
                myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                myTable.getColumnModel().getColumn(0).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(0).setPreferredWidth(10);
                myTable.getColumnModel().getColumn(1).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(1).setPreferredWidth(180);
                myTable.getColumnModel().getColumn(2).setCellRenderer(new StandardCellRenderer());
                myTable.getColumnModel().getColumn(2).setPreferredWidth(30);
                myTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonCellRenderer());
                myTable.getColumnModel().getColumn(3).setPreferredWidth(10);
                myTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonCellRenderer());
                myTable.getColumnModel().getColumn(4).setPreferredWidth(10);
                myTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonCellRenderer());
                myTable.getColumnModel().getColumn(5).setPreferredWidth(10);
                myTable.drawSelection();
                myTable.removeColumn(myTable.getColumnModel().getColumn(6));
                
                myTable.resetSelection();
                
                if(fattura != null && fattura.getDtmEmissione() != null)
                    dataEmissioneLabel.setText("Ricevuta emessa il " + DATETIME_SDF.format(fattura.getDtmEmissione()));
            }
        });
    }

    public List<ArticoloFatturaForView> loadList() {
        
        listaArticoli = new ArrayList<ArticoloFatturaForView>();
        
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(VenditaForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return listaArticoli;
        }
        
        FattureMapper fm = session.getMapper(FattureMapper.class);
        fattura = fm.selectByPrimaryKey(idVendita);
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("idVendita", idVendita);
        listaArticoli = session.selectList("custom_Articoli.getArticoliFattura", params);
        
        return listaArticoli;
    }
    
    @Override
    public List<FormItem> getFormItems() {
        return new ArrayList<FormItem>();
    }

    @Override
    public void openInsertDialog() {
    }

    @Override
    public void addArticolo(ArticoloForView articolo,Integer quantita, Integer idListino, double importo) {
        SqlSession session = null;
        
        try{
            session = SQLMapClientFactory.getSqlMapClient().openSession();
            
        }catch(IOException e){
            Utils.logError(VenditaForm.class, e);
            Utils.showErrorAlert("Non è stato possibile accedere ad Database.");
            return;
        }
        
        ListinoMapper lm = session.getMapper(ListinoMapper.class);
        
        Listino l = lm.selectByPrimaryKey(idListino);
        
        ArticoloFatturaForView af = new ArticoloFatturaForView();
        af.setArticolo(articolo.getId());
        af.setQuantita(quantita);
        af.setListino(idListino);
        af.setDescrizione(articolo.getDescrizione() + " - " + l.getDescrizione());
        
        if(importo < 0.0){
        
            af.setImponibile(articolo.getImportoListino() * quantita);
            af.setIva(CalculationsUtils.calcolaPerc(articolo.getImportoListino() * quantita, articolo.getIva()) );
            af.setImporto(CalculationsUtils.calcolaMaggiorazionePerc(articolo.getImportoListino() * quantita, articolo.getIva()));
            
        }else{
            
            af.setImporto(importo);
            af.setImponibile(importo / (1+(articolo.getIva() / 100)));
            af.setIva(importo - af.getImponibile());
            
        }
        
        FattureMapper fm = session.getMapper(FattureMapper.class);
        ArticoloFatturaMapper afm = session.getMapper(ArticoloFatturaMapper.class);
        
        if(listaArticoli.size() == 0){
            try{
                idVendita = Utils.getNextVal("fatture");
                
                fattura = new Fatture();
                fattura.setId(idVendita);
                fattura.setImponibile(0.0);
                fattura.setIva(0.0);
                fattura.setTotale(0.0);
                fattura.setDtmModifica(new Date());
                fm.insertSelective(fattura);
            }catch(IOException e){
                Utils.logError(VenditaForm.class, e);
                Utils.showErrorAlert("Non è stato possibile accedere ad Database.");
                return;
            }catch(SQLException e){
                Utils.logError(VenditaForm.class, e);
                Utils.showErrorAlert("Non è stato possibile accedere ad Database.");
                return;
            }
        }
        
        af.setFattura(idVendita);
        
        int idx = 0;
        for (ArticoloFattura a : listaArticoli) {
            
            if((a.getArticolo() == articolo.getId()) && (a.getListino() == idListino) && ((importo == (a.getImporto() / a.getQuantita())))){
                break;
            }
            idx++;
        }
        
        if(idx == listaArticoli.size()){
            listaArticoli.add(af);
            try{
                af.setId(Utils.getNextVal("articolo_fattura"));
            }catch(IOException e){
                Utils.logError(VenditaForm.class, e);
                Utils.showErrorAlert("Non è stato possibile accedere ad Database.");
                return;
            }catch(SQLException e){
                Utils.logError(VenditaForm.class, e);
                Utils.showErrorAlert("Non è stato possibile accedere ad Database.");
                return;
            }
            afm.insert(af);
        }else{
            listaArticoli.get(idx).setQuantita(listaArticoli.get(idx).getQuantita() + af.getQuantita());
            listaArticoli.get(idx).setImponibile(listaArticoli.get(idx).getImponibile() + af.getImponibile());
            listaArticoli.get(idx).setImporto(listaArticoli.get(idx).getImporto() + af.getImporto());
            listaArticoli.get(idx).setIva(listaArticoli.get(idx).getIva() + af.getIva());
            ArticoloFatturaExample afe = new ArticoloFatturaExample();
            afe.createCriteria().andArticoloEqualTo(af.getArticolo()).andFatturaEqualTo(idVendita).andListinoEqualTo(af.getListino());
            afm.updateByPrimaryKeySelective((ArticoloFattura)listaArticoli.get(idx));
        }
        
        fattura.setImponibile(fattura.getImponibile() + af.getImponibile());
        fattura.setIva(fattura.getIva() + af.getIva());
        fattura.setTotale(fattura.getTotale() + af.getImporto());
        fm.updateByPrimaryKeySelective(fattura);
        
        session.commit();
        session.close();
        
        refreshInfoComponents();
    }

    @Override
    public void updateListino(Integer idListino,Integer tipo) {
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(VenditaForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return;
        }
        
        ListinoMapper lm = session.getMapper(ListinoMapper.class);
        Listino l = lm.selectByPrimaryKey(idListino);
        
        if(tipo == 1){
            articoliForm.refreshInfoComponents(l);
        }else{
            prestazioniForm.refreshInfoComponents(l);
        }
    }
    
    @Override
    public void updateGiacenza(Integer idListino, Articoli articolo){
        articoliForm.updateGiacenza(idListino, articolo);
    }

    @Override
    public void resetTableSelection() {
        myTable.resetSelection();
    }
    
}
