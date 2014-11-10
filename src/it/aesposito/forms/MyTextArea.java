/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import it.aesposito.application.Constants;
import java.awt.Color;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Antonio
 */
public class MyTextArea extends TextFormItem implements Constants{
    private JTextArea textArea;
    
    public MyTextArea(String name, Class dataMappingType, String caption, boolean required, FormItemType type, int textLimit, Object defaultValue, boolean visible){
        super(name,dataMappingType,caption,required,type,textLimit,defaultValue,visible);
        createField();
    }
    
    @Override
    protected final void createField() {
        
        textArea = new JTextArea();
        textArea.setDocument(new JTextFieldLimit(textLimit));
        textArea.setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
        textArea.setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
        textArea.setFont(FIELDS_FONT);
        textArea.setText((String)defaultValue);
        JScrollPane c = new JScrollPane(textArea);
        c.setPreferredSize(STANDARD_FIELD_DIM);
        this.field = c;
    }
    
    @Override
    public void reset() {
        textArea.setText("");
    }
    
    @Override
    public String getStringValue() {
        return textArea.getText();
    }

    @Override
    public Integer getIntegerValue() {
        return Integer.parseInt(textArea.getText());
    }

    @Override
    public Double getDoubleValue() {
        return Double.parseDouble(textArea.getText());
    }

    @Override
    public Date getDateValue() throws ParseException{
        return Constants.DATE_SDF.parse(textArea.getText());
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
    
}
