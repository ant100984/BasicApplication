/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.application.Constants;
import it.aesposito.forms.ArticoliListUpdater;
import it.aesposito.jstore.bean.Articoli;
import it.aesposito.jstore.bean.ArticoloForView;
import it.aesposito.utils.CalculationsUtils;
import it.aesposito.utils.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 *
 * @author Antonio
 */
public class VenditePanel extends JPanel implements ActionListener, VenditaManager{
    private JSpinner quantita;
    private JButton vendi;
    private ArticoloForView articolo;
    private double importo = -1;
    private Integer idListino;
    private int idx;
    
    private JLabel riepilogoArticolo;
    
    private JPanel venditeContainer;
    private JPanel riepilogoContainer;
    private ArticoliListUpdater listUpdater;
    private JButton minus;
    private JButton plus;
    
    public VenditePanel(ArticoliListUpdater listUpdater){
        this.listUpdater = listUpdater;
        initComponents();
    }
    
    public final void initComponents(){
        
        this.setBorder(BorderFactory.createTitledBorder("<html><b> Articolo </b></html>"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        venditeContainer = new JPanel();
        riepilogoContainer = new JPanel();
        venditeContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        riepilogoContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 1000, 1);
        quantita = new JSpinner(model);
        quantita.setPreferredSize(Constants.STANDARD_FIELD_DIM);
        
        ((JSpinner.DefaultEditor)quantita.getEditor()).getTextField().setForeground(Color.decode(Constants.DEFAULT_FIELD_FOREGROUND));
        ((JSpinner.DefaultEditor)quantita.getEditor()).getTextField().setBackground(Color.decode(Constants.DEFAULT_FIELD_BACKGROUND));
        ((JSpinner.DefaultEditor)quantita.getEditor()).getTextField().setFont(Constants.FIELDS_FONT);
        
        minus = new JButton("",new ImageIcon(getClass()
				.getClassLoader().getResource("minus_black.png")));
        minus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if( ((Integer)quantita.getValue()) > 1 )
                    quantita.setValue(((Integer)quantita.getValue()) - 1);
            }
        });
        
        plus = new JButton("",new ImageIcon(getClass()
				.getClassLoader().getResource("add_small.png")));
        plus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    quantita.setValue(((Integer)quantita.getValue()) + 1);
            }
        });
        
        minus.setPreferredSize(new Dimension(Constants.MISC_BUTTON_WIDTH,Constants.MISC_BUTTON_HEIGHT));
        plus.setPreferredSize(new Dimension(Constants.MISC_BUTTON_WIDTH,Constants.MISC_BUTTON_HEIGHT));
        
        quantita.setValue(1);
        venditeContainer.add(minus);
        venditeContainer.add(plus);
        venditeContainer.add(quantita);
        vendi = new JButton("Ok",new ImageIcon(getClass()
				.getClassLoader().getResource("ok.png")));
        vendi.setHorizontalTextPosition(SwingConstants.CENTER);
        vendi.setVerticalTextPosition(SwingConstants.BOTTOM);
        vendi.setPreferredSize(new Dimension(Constants.MISC_BUTTON_WIDTH,Constants.MISC_BUTTON_HEIGHT));
        vendi.addActionListener(this);
        venditeContainer.add(vendi);
        
        riepilogoArticolo = new JLabel(" - ");
        riepilogoContainer.add(riepilogoArticolo);
        
        this.add(venditeContainer);
        this.add(riepilogoContainer);
        
    }
    
    @Override
    public void setEnabled(boolean enabled){
        this.quantita.setEnabled(enabled);
        this.vendi.setEnabled(enabled);
        this.plus.setEnabled(enabled);
        this.minus.setEnabled(enabled);
    }
    
    public void reset(){
        quantita.setValue(1);
        importo = -1;
        riepilogoArticolo.setText("");
        articolo = new ArticoloForView();
    }
    
    public void setRiepilogoArticolo(String riepilogo) {
        this.riepilogoArticolo.setText(riepilogo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addToVendita();
    }
    
    @Override
    public void addToVendita(){
        boolean good;
        do{
            good = true;
            try{
                String result = JOptionPane.showInputDialog("Conferma importo", CalculationsUtils.round2Decimals(articolo.getImportoVendita() * (Integer)quantita.getValue()));
                if(result==null) return;

                importo = Double.parseDouble(result.replace(",", "."));
            }catch(Exception ex){
                good = false;
            }

            if(importo <= 0.0) good = false;

            if(!good) Utils.showWarningAlert("Immettere un importo corretto");
        }while(!good);

        if(articolo.getTipologiaArticolo() == 1){
            if((articolo.getGiacenza()-((Integer)quantita.getValue())) >= 0){
                articolo.setGiacenza(articolo.getGiacenza()-((Integer)quantita.getValue()));
                listUpdater.updateGiacenza(idListino, articolo);
                listUpdater.addArticolo(articolo, (Integer)quantita.getValue(), idListino, importo);
            }else
                Utils.showInfoAlert("La giacenza non è sufficiente!");
        }else if(articolo.getTipologiaArticolo() == 2){
            listUpdater.addArticolo(articolo, (Integer)quantita.getValue(), idListino, importo);
        }
    }
    
    public Articoli getArticolo() {
        return articolo;
    }

    public void setArticolo(ArticoloForView articolo) {
        this.articolo = articolo;
        if(articolo.getTipologiaArticolo() == 1){
            riepilogoArticolo.setText("<html><b>" + articolo.getDescrizione() + "</b>" +
                                " - Numero pezzi in giacenza <b>" + articolo.getGiacenza() + "</b></html>");
        }else if(articolo.getTipologiaArticolo() == 2){
            riepilogoArticolo.setText("<html><b>" + articolo.getDescrizione() + "</b></html>");
        }
    }
    
    public Integer getIdListino() {
        return idListino;
    }

    public void setIdListino(Integer idListino) {
        this.idListino = idListino;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
    
    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }
}
