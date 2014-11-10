/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.bean;

/**
 *
 * @author Antonio
 */
public class ArticoloForView extends Articoli{
    private String categoriaForView;
    private Double importoListino;
    private Double importoVendita;

    public Double getImportoVendita() {
        return importoVendita;
    }

    public void setImportoVendita(Double importoVendita) {
        this.importoVendita = importoVendita;
    }

    public String getCategoriaForView() {
        return categoriaForView;
    }

    public void setCategoriaForView(String categoriaForView) {
        this.categoriaForView = categoriaForView;
    }

    public Double getImportoListino() {
        return importoListino;
    }

    public void setImportoListino(Double importoListino) {
        this.importoListino = importoListino;
    }
    
}
