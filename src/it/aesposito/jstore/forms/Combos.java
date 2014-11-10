/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.application.Themes;
import it.aesposito.db.SQLMapClientFactory;
import it.aesposito.forms.ComboItem;
import it.aesposito.jstore.bean.Categorie;
import it.aesposito.jstore.bean.CategorieExample;
import it.aesposito.jstore.bean.Iva;
import it.aesposito.jstore.bean.IvaExample;
import it.aesposito.jstore.bean.Listino;
import it.aesposito.jstore.bean.ListinoExample;
import it.aesposito.jstore.bean.TipologiaArticolo;
import it.aesposito.jstore.bean.TipologiaArticoloExample;
import it.aesposito.jstore.dao.CategorieMapper;
import it.aesposito.jstore.dao.IvaMapper;
import it.aesposito.jstore.dao.ListinoMapper;
import it.aesposito.jstore.dao.TipologiaArticoloMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Antonio
 */
public class Combos {
    
    public static Vector<ComboItem> getIvaComboModel(){
        SqlSession session = null;
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Logger.getLogger(Combos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        IvaMapper im = session.getMapper(IvaMapper.class);
        List<Iva> il = im.selectByExample(new IvaExample());
        
        Vector<ComboItem> comboIva = new Vector<ComboItem>();
        comboIva.add(new ComboItem(-1,""));
        for (Iva iva : il) {
            comboIva.add(new ComboItem(iva.getIva(), iva.getDescrizione() + " : " + iva.getIva()+"%"));
        }
        
        return comboIva;
    }
    
    public static Vector<ComboItem> getTipologiaArticoloComboModel(ComboTipoArticoloConf conf){
        SqlSession session = null;
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Logger.getLogger(Combos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TipologiaArticoloMapper tam = session.getMapper(TipologiaArticoloMapper.class);
        List<TipologiaArticolo> lta = tam.selectByExample(new TipologiaArticoloExample());
        
        Vector<ComboItem> combo = new Vector<ComboItem>();
        combo.add(new ComboItem(-1,""));
        for (TipologiaArticolo tipologiaArticolo : lta) {
            switch(conf){
                case CODICE: combo.add(new ComboItem(tipologiaArticolo.getId(),tipologiaArticolo.getCodice()));
                             break;
                case DESCRIZIONE: combo.add(new ComboItem(tipologiaArticolo.getId(),tipologiaArticolo.getDescrizione()));
                             break;    
            }
        }
        
        return combo;
    }
    
    public static Vector<ComboItem> getThemesComboModel(){
                
        Vector<ComboItem> combo = new Vector<ComboItem>();
        for (Object theme : Themes.getThemes().keySet()) {
            combo.add(new ComboItem(theme, (String) theme));
        }
        
        return combo;
    }
    
    public static Vector<ComboItem> getCategorieComboModel(Integer tipologiaArticolo){
        SqlSession session = null;
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Logger.getLogger(Combos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CategorieMapper tam = session.getMapper(CategorieMapper.class);
        CategorieExample ce = new CategorieExample();
        ce.createCriteria().andTipologiaArticoloEqualTo(tipologiaArticolo);
        List<Categorie> lta = tam.selectByExample(ce);
        
        Vector<ComboItem> combo = new Vector<ComboItem>();
        combo.add(new ComboItem(-1,""));
        for (Categorie categoria : lta) {
            combo.add(new ComboItem(categoria.getId(),categoria.getDescrizione()));
        }
        
        return combo;
    }
    
    public static Vector<ComboItem> getCategorieComboModel(){
        SqlSession session = null;
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Logger.getLogger(Combos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CategorieMapper tam = session.getMapper(CategorieMapper.class);
        CategorieExample ce = new CategorieExample();
        List<Categorie> lta = tam.selectByExample(ce);
        
        Vector<ComboItem> combo = new Vector<ComboItem>();
        combo.add(new ComboItem(-1,""));
        for (Categorie categoria : lta) {
            combo.add(new ComboItem(categoria.getId(),categoria.getDescrizione()));
        }
        
        return combo;
    }
    
    public static Vector<ComboItem> getListiniComboModel(Integer tipologiaArticolo,Options[] opts){
        SqlSession session = null;
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Logger.getLogger(Combos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ListinoMapper tam = session.getMapper(ListinoMapper.class);
        ListinoExample ce = new ListinoExample();
        ce.createCriteria().andTipologiaListinoEqualTo(tipologiaArticolo);
        List<Listino> lta = tam.selectByExample(ce);
        
        Vector<ComboItem> combo = new Vector<ComboItem>();
        
        if(!Arrays.asList(opts).contains(Options.NO_DEFAULT))
            combo.add(new ComboItem(-1,""));
        
        for (Listino listino : lta) {
            combo.add(new ComboItem(listino.getId(),listino.getDescrizione()));
        }
        
        return combo;
    }
    
    public enum ComboTipoArticoloConf {
        CODICE,DESCRIZIONE
    }
    
    public enum Options{
        NO_DEFAULT
    }
}
