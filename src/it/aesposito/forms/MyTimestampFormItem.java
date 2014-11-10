/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import it.aesposito.application.Constants;
import static it.aesposito.application.Constants.DEFAULT_FIELD_BACKGROUND;
import static it.aesposito.application.Constants.DEFAULT_FIELD_FOREGROUND;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author Antonio
 */
public class MyTimestampFormItem extends FormItem implements Constants{
    
    public MyTimestampFormItem(String name, Class dataMappingType, String caption, boolean required, FormItemType type, Object defaultValue, boolean visible){
        super(name,dataMappingType,caption,required,type,defaultValue,visible);
        createField();
    }
    
    @Override
    protected final void createField() {
        JTextField c = new JTextField();
        c.setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
        c.setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
        c.setText(DATETIME_SDF.format((Date)defaultValue));
        this.field = c;
    }
    
    @Override
    public void reset(){
        ((JTextField)field).setText(DATETIME_SDF.format(new Date()));
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
