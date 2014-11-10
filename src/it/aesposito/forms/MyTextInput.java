/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import it.aesposito.application.Constants;
import static it.aesposito.application.Constants.STANDARD_FIELD_DIM;
import java.awt.Color;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author Antonio
 */
public class MyTextInput extends TextFormItem implements Constants{
    
    public MyTextInput(String name, Class dataMappingType, String caption, boolean required, FormItemType type, int textLimit, Object defaultValue, boolean visible){
        super(name,dataMappingType,caption,required,type,textLimit,defaultValue,visible);
        createField();
    }
    
    @Override
    protected final void createField() {
        JTextField c = new JTextField();
        c.setDocument(new JTextFieldLimit(textLimit));
        c.setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
        c.setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
        c.setFont(FIELDS_FONT);
        c.setText((String)defaultValue);
        c.setPreferredSize(STANDARD_FIELD_DIM);
        this.field = c;
    }
    
    @Override
    public void reset(){
        ((JTextField)field).setText("");
    }
    
    @Override
    public String getStringValue() {
        return ((JTextField)field).getText();
    }

    @Override
    public Integer getIntegerValue() {
        return Integer.parseInt(((JTextField)field).getText());
    }

    @Override
    public Double getDoubleValue() {
        return Double.parseDouble(((JTextField)field).getText());
    }

    @Override
    public Date getDateValue() throws ParseException {
        return Constants.DATE_SDF.parse(((JTextField)field).getText());
    }
    
}
