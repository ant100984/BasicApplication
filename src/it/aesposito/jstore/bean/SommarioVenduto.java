/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.bean;

/**
 *
 * @author Antonio
 */

public class SommarioVenduto {
    private Integer numeroPrestazioni;
    private Integer numeroProdotti;
    private Double costoAcquistoTotale;
    private Double prezzoVenditaTotale;
    private Double guadagnoTotale;
    private Double diffIvaTotale;
    private Double guadagnoNettoTotale;
    private Integer numResults;

    public Double getPrezzoVenditaTotale() {
        return prezzoVenditaTotale;
    }

    public void setPrezzoVenditaTotale(Double prezzoVenditaTotale) {
        this.prezzoVenditaTotale = prezzoVenditaTotale;
    }

    public Integer getNumeroPrestazioni() {
        return numeroPrestazioni;
    }

    public void setNumeroPrestazioni(Integer numeroPrestazioni) {
        this.numeroPrestazioni = numeroPrestazioni;
    }

    public Integer getNumeroProdotti() {
        return numeroProdotti;
    }

    public void setNumeroProdotti(Integer numeroProdotti) {
        this.numeroProdotti = numeroProdotti;
    }

    public Double getCostoAcquistoTotale() {
        return costoAcquistoTotale;
    }

    public void setCostoAcquistoTotale(Double costoAcquistoTotale) {
        this.costoAcquistoTotale = costoAcquistoTotale;
    }

    public Double getGuadagnoTotale() {
        return guadagnoTotale;
    }

    public void setGuadagnoTotale(Double guadagnoTotale) {
        this.guadagnoTotale = guadagnoTotale;
    }

    public Double getDiffIvaTotale() {
        return diffIvaTotale;
    }

    public void setDiffIvaTotale(Double diffIvaTotale) {
        this.diffIvaTotale = diffIvaTotale;
    }

    public Double getGuadagnoNettoTotale() {
        return guadagnoNettoTotale;
    }

    public void setGuadagnoNettoTotale(Double guadagnoNettoTotale) {
        this.guadagnoNettoTotale = guadagnoNettoTotale;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }
    
}
