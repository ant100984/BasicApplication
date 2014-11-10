/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.application.Constants;
import it.aesposito.db.SQLMapClientFactory;
import it.aesposito.forms.MyListItemsForm;
import it.aesposito.jstore.bean.Articoli;
import it.aesposito.jstore.dao.ArticoliMapper;
import it.aesposito.utils.CalculationsUtils;
import it.aesposito.utils.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Antonio
 */
public class ScortePanel extends JPanel implements ActionListener{
    private JSpinner giacenza;
    private JButton carica;
    private Articoli articolo;

    private JLabel riepilogoArticolo;
    
    private MyListItemsForm parent;
    
    private JPanel scorteContainer;
    private JPanel riepilogoContainer;
    private JButton plus;
    private JButton minus;
    
    public ScortePanel(MyListItemsForm parent){
        this.parent = parent;
        initComponents();
    }
    
    public final void initComponents(){
        
        this.setBorder(BorderFactory.createTitledBorder("<html><b> Articolo </b></html>"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        scorteContainer = new JPanel();
        riepilogoContainer = new JPanel();
        scorteContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        riepilogoContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 1000, 1);
        giacenza = new JSpinner(model);
        giacenza.setPreferredSize(Constants.STANDARD_FIELD_DIM);
        
        ((JSpinner.DefaultEditor)giacenza.getEditor()).getTextField().setForeground(Color.decode(Constants.DEFAULT_FIELD_FOREGROUND));
        ((JSpinner.DefaultEditor)giacenza.getEditor()).getTextField().setBackground(Color.decode(Constants.DEFAULT_FIELD_BACKGROUND));
        ((JSpinner.DefaultEditor)giacenza.getEditor()).getTextField().setFont(Constants.FIELDS_FONT);
        
        minus = new JButton("",new ImageIcon(getClass()
				.getClassLoader().getResource("minus_black.png")));
        minus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if( ((Integer)giacenza.getValue()) > 1 )
                    giacenza.setValue(((Integer)giacenza.getValue()) - 1);
            }
        });
        
        plus = new JButton("",new ImageIcon(getClass()
				.getClassLoader().getResource("add_small.png")));
        plus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    giacenza.setValue(((Integer)giacenza.getValue()) + 1);
            }
        });
        
        minus.setPreferredSize(new Dimension(Constants.MISC_BUTTON_WIDTH,Constants.MISC_BUTTON_HEIGHT));
        plus.setPreferredSize(new Dimension(Constants.MISC_BUTTON_WIDTH,Constants.MISC_BUTTON_HEIGHT));
        
        scorteContainer.add(minus);
        
        giacenza.setValue(1);
        scorteContainer.add(plus);
        scorteContainer.add(giacenza);
        
        carica = new JButton("Ok",new ImageIcon(getClass()
				.getClassLoader().getResource("ok.png")));
        carica.setHorizontalTextPosition(SwingConstants.CENTER);
        carica.setVerticalTextPosition(SwingConstants.BOTTOM);
        carica.setPreferredSize(new Dimension(Constants.MISC_BUTTON_WIDTH,Constants.MISC_BUTTON_HEIGHT));
        carica.addActionListener(this);
        scorteContainer.add(carica);
        
        riepilogoArticolo = new JLabel(" - ");
        riepilogoContainer.add(riepilogoArticolo);
        
        this.add(scorteContainer);
        this.add(riepilogoContainer);
        
    }
    
    @Override
    public void setEnabled(boolean enabled){
        this.giacenza.setEnabled(enabled);
        this.carica.setEnabled(enabled);
        this.plus.setEnabled(enabled);
        this.minus.setEnabled(enabled);
    }
    
    public void reset(){
        giacenza.setValue(1);
        riepilogoArticolo.setText("");
        articolo = new Articoli();
    }
    
    public void setRiepilogoArticolo(String riepilogo) {
        this.riepilogoArticolo.setText(riepilogo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(ScortePanel.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
        }
        
        ArticoliMapper rm = (ArticoliMapper)session.getMapper(ArticoliMapper.class);
        articolo.setGiacenza(articolo.getGiacenza() + (Integer)giacenza.getValue());
        rm.updateByPrimaryKeySelective(articolo);
        
        session.commit();
        session.close();
        
        parent.refreshInfoComponents();
    }
    
    public Articoli getArticolo() {
        return articolo;
    }

    public void setArticolo(Articoli articolo) {
        this.articolo = articolo;
        Double totImporto = articolo.getImportoUnitario() * articolo.getGiacenza();
        Double totIva = CalculationsUtils.calcolaPerc(totImporto, articolo.getIva());
        riepilogoArticolo.setText("<html><b>" + articolo.getDescrizione() + "</b>" +
                                " - Numero pezzi in giacenza <b>" + articolo.getGiacenza() + "</b>" +
                                " , Importo in giacenza <b>€ " + CalculationsUtils.round2Decimals(totImporto) + " </b>" +
                                " + Iva <b>€ " + CalculationsUtils.round2Decimals(totIva) + "</b>" +
                                " Totale <b>€ " + CalculationsUtils.round2Decimals(totImporto + totIva) + "</b></html>");
    }
    
}
