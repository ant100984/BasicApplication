/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.MyButtonTableColumn;
import it.aesposito.forms.MyTable;
import it.aesposito.forms.MyTableColumn;
import static it.aesposito.application.Constants.DATE_SDF;
import it.aesposito.db.SQLMapClientFactory;
import it.aesposito.forms.ArticoliListUpdater;
import it.aesposito.jstore.bean.Articoli;
import it.aesposito.jstore.bean.ArticoloFattura;
import it.aesposito.jstore.bean.ArticoloFatturaExample;
import it.aesposito.jstore.bean.ArticoloFatturaForView;
import it.aesposito.jstore.bean.Fatture;
import it.aesposito.jstore.dao.ArticoliMapper;
import it.aesposito.jstore.dao.ArticoloFatturaMapper;
import it.aesposito.jstore.dao.FattureMapper;
import it.aesposito.utils.CalculationsUtils;
import it.aesposito.utils.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Antonio
 */
public class VenditaTableModel extends AbstractTableModel{
    private List<MyTableColumn> columns;
    private List<ArticoloFatturaForView> rows;
    private MainApplicationForm parent_frame;
    
    public VenditaTableModel(List<ArticoloFatturaForView> rows, MainApplicationForm parent_frame){
        this.parent_frame = parent_frame;
        this.columns = new ArrayList<MyTableColumn>();
        
        columns.add(new MyTableColumn("Quant.", Integer.class, "quantita",20));
        columns.add(new MyTableColumn("Descrizione", String.class, "descrizione",200));
        columns.add(new MyTableColumn("Importo", String.class, "importo","€ ","",30));
        columns.add(new MyButtonTableColumn("", JButton.class,15));
        columns.add(new MyButtonTableColumn("", JButton.class,15));
        columns.add(new MyButtonTableColumn("", JButton.class,15));
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
            case 2: return getHumanReadable(rows.get(rowIndex),columns.get(columnIndex));
            case 3: JButton button = new JButton("",new ImageIcon(getClass()
				.getClassLoader().getResource("trash_black.png")));
                    button.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            updateGiacenza(rows.get(rowIndex), rows.get(rowIndex).getQuantita());
                            //rows.remove(rowIndex);
                        }
                    });
                    
                    return button;
            case 4: JButton button1 = new JButton("",new ImageIcon(getClass()
				.getClassLoader().getResource("minus_black.png")));
                    button1.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            updateGiacenza(rows.get(rowIndex), 1);
                            
                            /*if(rows.get(rowIndex).getQuantita() == 1)
                                rows.remove(rowIndex);
                            else
                                rows.get(rowIndex).setQuantita(rows.get(rowIndex).getQuantita() - 1);
                            */
                        }
                    });
                    
                    return button1;
            case 5: JButton button2 = new JButton("",new ImageIcon(getClass()
				.getClassLoader().getResource("add_small.png")));
                    button2.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            updateGiacenza(rows.get(rowIndex), -1);
                            //rows.get(rowIndex).setQuantita(rows.get(rowIndex).getQuantita() + 1);
                            
                        }
                    });
                    
                    return button2;
            case 6: return rows.get(rowIndex);
        }
        
        return "";
    }
    
    protected String getHumanReadable(Object obj, MyTableColumn field){
        try {
            Method m = obj.getClass().getMethod("get" + Utils.capitalizeFirstLetter(field.getBeanDataMapping()), null);
            Object result = m.invoke(obj, new Object[0]);
            if(result == null) return "";
            if(result instanceof String) return field.getPrefisso() + (String)result + field.getSuffisso();
            if(result instanceof Date) return field.getPrefisso() + DATE_SDF.format((Date)result) + field.getSuffisso();
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
    
    private boolean updateGiacenza(ArticoloFattura af,int quantita){
         SqlSession session = null;

        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(ListaArticoliForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return false;
        }

        ArticoliMapper am = session.getMapper(ArticoliMapper.class);
        ArticoloFatturaMapper afm = session.getMapper(ArticoloFatturaMapper.class);
        FattureMapper fm = session.getMapper(FattureMapper.class);
        
        Articoli a = am.selectByPrimaryKey(af.getArticolo());
        Fatture f = fm.selectByPrimaryKey(af.getFattura());
        
        if(a.getTipologiaArticolo() == 1 && (a.getGiacenza() + quantita < 0)){
            Utils.showInfoAlert("La giacenza non è sufficiente!");
            return false;
        }
        
        if(a.getTipologiaArticolo() == 1){
            a.setGiacenza(a.getGiacenza() + quantita);
            am.updateByPrimaryKeySelective(a);
        }
        
        double importoUnitario = af.getImporto() / af.getQuantita();
        
        af.setQuantita( af.getQuantita() + (-1*quantita));
        double importoDiff = importoUnitario * af.getQuantita() - af.getImporto();
        
        af.setImporto(importoUnitario * af.getQuantita());
        af.setImponibile(af.getImporto()/(1+(a.getIva()/100)));
        double ivaDiff = CalculationsUtils.calcolaPerc(af.getImponibile(), a.getIva()) - af.getIva();
        
        af.setIva(CalculationsUtils.calcolaPerc(af.getImponibile(), a.getIva()));
        
        f.setIva(f.getIva() + ivaDiff);
        f.setTotale(f.getTotale() + importoDiff);
        f.setImponibile(f.getTotale() - f.getIva());
        
        if(af.getQuantita() == 0)
            afm.deleteByPrimaryKey(af.getId());
        else afm.updateByPrimaryKeySelective(af);
        
        ArticoloFatturaExample afe = new ArticoloFatturaExample();
        afe.createCriteria().andFatturaEqualTo(f.getId());
        
        if(afm.countByExample(afe) == 0){
            fm.deleteByPrimaryKey(f.getId());
            parent_frame.resetListaVenditeSelection();
        }else fm.updateByPrimaryKeySelective(f);
        
        session.commit();
        session.close();
        
        return true;
    }
}
