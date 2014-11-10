/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import it.aesposito.application.Constants;
import static it.aesposito.application.Constants.DEFAULT_FIELD_BACKGROUND;
import static it.aesposito.application.Constants.DEFAULT_FIELD_FOREGROUND;
import static it.aesposito.application.Constants.STANDARD_FIELD_DIM;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.util.Date;
import org.jdesktop.swingx.JXTextField;

/**
 *
 * @author Antonio
 */
public class MyAutoCompleteFormItem extends TextFormItem{
    
    public MyAutoCompleteFormItem(String name, Class dataMappingType, String caption, boolean required, FormItemType type, int textLimit, Object defaultValue, boolean visible){
        super(name,dataMappingType,caption,required,type,textLimit,defaultValue,visible);
        createField();
    }
    
    @Override
    protected final void createField() {
        JXTextField c = new JXTextField();
        ((JXTextField)c).setDocument(new JTextFieldLimit(textLimit));
        c.setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
        c.setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
        c.setFont(Constants.FIELDS_FONT);
        ((JXTextField)c).setText((String)defaultValue);
        c.setPreferredSize(STANDARD_FIELD_DIM);
        this.field = c;
    }
    
    @Override
    public void reset(){
        ((JXTextField)field).setText("");
    }
    
    @Override
    public String getStringValue() {
        return ((JXTextField)field).getText();
    }

    @Override
    public Integer getIntegerValue() {
        return Integer.parseInt(((JXTextField)field).getText());
    }

    @Override
    public Double getDoubleValue() {
        return Double.parseDouble(((JXTextField)field).getText());
    }

    @Override
    public Date getDateValue() throws ParseException {
        return Constants.DATE_SDF.parse(((JXTextField)field).getText());
    }
    
}
