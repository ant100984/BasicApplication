/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.errors;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Antonio
 */
public abstract class ReturnCodes {
    
    public static final Map<Integer, String> errorDescription;
    
    public static final Integer OK = 0;
    public static final Integer DB_CONNECT_ERROR = -1;
    public static final Integer DB_ALREADY_EXISTS = -2;
    public static final Integer DB_CREATION_ERROR = -3;
    
    static{
        
         errorDescription = new HashMap<Integer, String>();
         
         errorDescription.put(DB_CONNECT_ERROR, "Errore nella connessione al Database");
         errorDescription.put(DB_ALREADY_EXISTS, "Il Database è già presente");
         errorDescription.put(DB_CREATION_ERROR, "Errore nella creazione del Database");
         
    }
    
}
