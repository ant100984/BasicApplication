/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Antonio
 */
public class Themes {
    public static final Map<String,String> themes = new HashMap<String,String>();

    static{ 
        themes.put("Acrylic", "com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        themes.put("Hi fi", "com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        themes.put("Noire","com.jtattoo.plaf.noire.NoireLookAndFeel");
        themes.put("Texture","com.jtattoo.plaf.texture.TextureLookAndFeel");
        themes.put("Smart", "com.jtattoo.plaf.smart.SmartLookAndFeel");
        themes.put("Aero", "com.jtattoo.plaf.aero.AeroLookAndFeel");
        themes.put("Aluminium", "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        themes.put("Bernstein", "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
        themes.put("Fast", "com.jtattoo.plaf.fast.FastLookAndFeel");
        themes.put("Graphite", "com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        themes.put("Luna", "com.jtattoo.plaf.luna.LunaLookAndFeel");
        themes.put("McWin", "com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        themes.put("Mint", "com.jtattoo.plaf.mint.MintLookAndFeel");
    }
    
    public static Map getThemes(){
        return themes;
    }
    
    public static String getDefaultThemeName(){
        return "Acrylic";
    }
    
    public static String getDefaultThemeClass(){
        return "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
    }
    
    public static String[] namesToStringArray(){
        String[] result = new String[themes.size()];
        Iterator i = themes.keySet().iterator();
        
        int idx = 0;
        while(i.hasNext()){
            result[idx++] = (String) i.next();
        }
        return result;
    }
    
    public static String[] classesToStringArray(){
        String[] result = new String[themes.size()];
        Iterator i = themes.values().iterator();
        
        int idx = 0;
        while(i.hasNext()){
            result[idx++] = (String) i.next();
        }
        return result;
    }
}
