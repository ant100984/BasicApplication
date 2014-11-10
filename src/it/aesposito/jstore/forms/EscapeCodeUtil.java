/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

public class EscapeCodeUtil{
    public static String createEscapeCode(int ... codes){
        StringBuilder sb = new StringBuilder();
        for(int code : codes){
            sb.append((char)code);
        }
        return sb.toString();
    }
}