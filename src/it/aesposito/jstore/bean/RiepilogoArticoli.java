/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.bean;

/**
 *
 * @author Antonio
 */
public class RiepilogoArticoli {
    private Integer numResults;
    private Integer giacenzaTotale;
    private Double ivaTotale;
    private Double imponibileTotale;
    private Double importoTotale;

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public Integer getGiacenzaTotale() {
        return giacenzaTotale;
    }

    public void setGiacenzaTotale(Integer giacenzaTotale) {
        this.giacenzaTotale = giacenzaTotale;
    }

    public Double getIvaTotale() {
        return ivaTotale;
    }

    public void setIvaTotale(Double ivaTotale) {
        this.ivaTotale = ivaTotale;
    }

    public Double getImponibileTotale() {
        return imponibileTotale;
    }

    public void setImponibileTotale(Double imponibileTotale) {
        this.imponibileTotale = imponibileTotale;
    }

    public Double getImportoTotale() {
        return importoTotale;
    }

    public void setImportoTotale(Double importoTotale) {
        this.importoTotale = importoTotale;
    }
    
}
