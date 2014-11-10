/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.utils;

/**
 *
 * @author Antonio
 */
public class CalculationsUtils {
    public static Double calcolaMaggiorazionePerc(Double importo, double perc){
        return importo + calcolaPerc(importo, perc);
    }

    public static Double calcolaPerc(Double importo, double perc){
        return ((importo / 100) * perc);
    }
    
    public static String round2Decimals(Double input){
       return String.format("%.2f",input);
    }
}
