/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

/**
 *
 * @author Antonio
 */
public class ComboItem {
    private Object valore;
    private String descrizione;

    public ComboItem(Object valore, String descrizione){
        this.valore = valore;
        this.descrizione = descrizione;
    }

    public Object getValore() {
        return valore;
    }

    public void setValore(Object valore) {
        this.valore = valore;
    }
    
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    @Override
    public String toString(){
        return descrizione;
    }
}
