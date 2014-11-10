/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.bean;

import java.util.Date;

/**
 *
 * @author Antonio
 */
public class RiepilogoVenduto extends ArticoloFatturaForView {
    private Date dtmVendita;
    private double costoAcquisto;
    private double guadagno;
    private double guadagnoNetto;
    private String tipologia;

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
    
    public double getGuadagnoNetto() {
        return guadagnoNetto;
    }

    public void setGuadagnoNetto(double guadagnoNetto) {
        this.guadagnoNetto = guadagnoNetto;
    }

    public double getGuadagno() {
        return guadagno;
    }

    public void setGuadagno(double guadagno) {
        this.guadagno = guadagno;
    }

    public double getDiffIva() {
        return diffIva;
    }

    public void setDiffIva(double diffIva) {
        this.diffIva = diffIva;
    }
    private double diffIva;
    
    public double getCostoAcquisto() {
        return costoAcquisto;
    }

    public void setCostoAcquisto(double costoAcquisto) {
        this.costoAcquisto = costoAcquisto;
    }

    public Date getDtmVendita() {
        return dtmVendita;
    }

    public void setDtmVendita(Date dtmVendita) {
        this.dtmVendita = dtmVendita;
    }
    
}
