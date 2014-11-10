/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import it.aesposito.jstore.bean.Articoli;
import it.aesposito.jstore.bean.ArticoloForView;

/**
 *
 * @author Antonio
 */
public interface ArticoliListUpdater {
    public void addArticolo(ArticoloForView articolo, Integer quantita, Integer idListino,double importo);
    
    public void updateListino(Integer idListino, Integer tipo);
    
    public void updateGiacenza(Integer idListino, Articoli articolo);
    
    public void resetTableSelection();
}
