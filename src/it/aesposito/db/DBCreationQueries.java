/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.db;

/**
 *
 * @author Antonio
 */
public interface DBCreationQueries {
    
    final static String TEST_CONNECTION_QUERY = "VALUES 1";
    
    final static String CREATE_COMUNI_TABLE = "CREATE TABLE COMUNI(ID INTEGER NOT NULL PRIMARY KEY, "+
                                            "NOME VARCHAR(255),"+
                                            "PROVINCIA VARCHAR(2),"+
                                            "CAP VARCHAR(5),"+
                                            "PREFISSOTEL VARCHAR(10),"+
                                            "CODICEISTAT VARCHAR(10),"+
                                            "CODICECATASTALE VARCHAR(10)"+
                                            ")";
    
    final static String CREATE_RITIRI_TABLE = "CREATE TABLE RITIRI (ID INTEGER NOT NULL PRIMARY KEY, "+
                                            "NOME VARCHAR(255)," +
                                            "COGNOME VARCHAR(255)," +
                                            "INDIRIZZO VARCHAR(255)," +
                                            "CAP VARCHAR(10)," +
                                            "COMUNE VARCHAR(255)," +
                                            "PROVINCIA VARCHAR(2)," +
                                            "NUM_PROT VARCHAR(10)," +
                                            "DATA_PROT TIMESTAMP," +
                                            "TARGA VARCHAR(20)," +
                                            "NUM_NOTA VARCHAR(10)," +
                                            "DATA_NOTA TIMESTAMP," +
                                            "COMUNE_COMANDO VARCHAR(255)," +
                                            "DTM_COMPILAZIONE TIMESTAMP"+
                                            ")";
    
    final static String CREATE_APP_PROPS_TABLE = "CREATE TABLE APPLICATION_PROPERTIES(ID INTEGER NOT NULL PRIMARY KEY, "+
                                            "NOME VARCHAR(10) NOT NULL,"+
                                            "VALORE VARCHAR(255)"+
                                            ")";
}