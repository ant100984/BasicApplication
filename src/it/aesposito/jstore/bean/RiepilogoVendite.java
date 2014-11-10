/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.bean;

/**
 *
 * @author Antonio
 */
public class RiepilogoVendite {
    private double iva;
    private double totale;
    private double imponibile;
    private Integer numResults;
    private Integer numRicevute;
    private double ivaNoRicevuta;
    private double totaleNoRicevuta;
    private double imponibileNoRicevuta;

    public double getIvaNoRicevuta() {
        return ivaNoRicevuta;
    }

    public void setIvaNoRicevuta(double ivaNoRicevuta) {
        this.ivaNoRicevuta = ivaNoRicevuta;
    }

    public double getTotaleNoRicevuta() {
        return totaleNoRicevuta;
    }

    public void setTotaleNoRicevuta(double totaleNoRicevuta) {
        this.totaleNoRicevuta = totaleNoRicevuta;
    }

    public double getImponibileNoRicevuta() {
        return imponibileNoRicevuta;
    }

    public void setImponibileNoRicevuta(double imponibileNoRicevuta) {
        this.imponibileNoRicevuta = imponibileNoRicevuta;
    }
    
    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public double getImponibile() {
        return imponibile;
    }

    public void setImponibile(double imponibile) {
        this.imponibile = imponibile;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public Integer getNumRicevute() {
        return numRicevute;
    }

    public void setNumRicevute(Integer numRicevute) {
        this.numRicevute = numRicevute;
    }
    
}
