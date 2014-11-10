/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.application;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.text.SimpleDateFormat;

/**
 *
 * @author Antonio
 */
public interface Constants {
    public static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat DATETIME_SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public static final Dimension LONG_FIELD_DIM = new Dimension(250,50);
    public static final Dimension MEDIUM_FIELD_DIM = new Dimension(150,50);
    public static final Dimension SHORT_FIELD_DIM = new Dimension(100,50);
    
    public static final Dimension STANDARD_FIELD_DIM = new Dimension(250,30);
    public static final Dimension SMALL_FIELD_DIM = new Dimension(200,25);
    
    public static final int PREFERENZE_FORM_WIDTH = 900;
    public static final int PREFERENZE_FORM_HEIGHT = 500;
    
    public static final int ARTICOLO_FORM_WIDTH = 900;
    public static final int ARTICOLO_FORM_HEIGHT = 500;
    
    public static final int LISTA_ARTICOLI_FORM_WIDTH = 1100;
    public static final int LISTA_ARTICOLI_FORM_HEIGHT = 900;
    
    public static final int LISTA_ARTICOLI_VENDITA_FORM_WIDTH = 1000;
    public static final int LISTA_ARTICOLI_VENDITA_FORM_HEIGHT = 780;
    
    public static final int LISTA_VENDITE_FORM_WIDTH = 800;
    public static final int LISTA_VENDITE_FORM_HEIGHT = 750;
    
    public static final int VENDITA_FORM_WIDTH = 1580;
    public static final int VENDITA_FORM_HEIGHT = 935;
    
    public static final int RIEPILOGO_VENDUTO_FORM_WIDTH = 1300;
    public static final int RIEPILOGO_VENDUTO_FORM_HEIGHT = 850;
    
    public static final String DEFAULT_FIELD_BACKGROUND = "0xFFFFCC";
    public static final String DEFAULT_FIELD_FOREGROUND = "0x007FFF";
    
    public static final String ERROR_FIELD_BACKGROUND = "0xFFCCCC";
    public static final String ERROR_FIELD_FOREGROUND = "0x000000";
    
    public static final String ODD_ROW_BACKGROUND = "0xE5E5E5";
    public static final String SELECTED_ROW_BACKGROUND = "0xFFFFB2";
    
    public static final String FORM_BORDER_COLOR = "0xBFBFFE";
    
    public static final int FORM_BUTTON_HEIGHT = 80;
    public static final int FORM_BUTTON_WIDTH = 130;
    
    public static final int MISC_BUTTON_HEIGHT = 50;
    public static final int MISC_BUTTON_WIDTH = 50;
    
    public static final Font FIELDS_FONT = new Font("Verdana",Font.BOLD,12);
    
    public static final Font TABLES_FONT = new Font("Verdana",Font.BOLD,12);
    
    public static final Integer TABLE_ROW_HEIGHT = 40;
    
    public static final Point DIALOG_POSITION = new Point(82, 53);
    
    public static final String GIACENZA_ZERO_COLOR = "0xFFCCCC";
    public static final String GIACENZA_SOTTOSCORTA_COLOR = "0xFFD280";
    
    public static final Font RIEPILOGO_FONT = new Font("Verdana",Font.BOLD,14);
    public static final Font RIEPILOGO_TOTALE_FONT = new Font("Verdana",Font.BOLD,16);
    public static final String RIEPILOGO_TOTALE_COLOR = "0x008000";
    
    public static final int FORM_VERTICAL_GAP = 10;
    public static final int FORM_HORIZONTAL_GAP = 10;
    
    public static final int NUMERO_COPIE_RICEVUTA = 2;
    
    public static final String RICEVUTA_NON_EMESSA_COLOR = "0xFFCCCC";
    public static final String RICEVUTA_EMESSA_COLOR = "0xCCE5CC";
    
    public static final String DATA_EMISSIONE_COLOR = "0x0017FF";
    
    public static final int TAB_NUM_ITEMS_PER_PAGE = 10;
}
