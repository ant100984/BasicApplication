/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import it.aesposito.application.Constants;
import static it.aesposito.application.Constants.DEFAULT_FIELD_BACKGROUND;
import static it.aesposito.application.Constants.DEFAULT_FIELD_FOREGROUND;
import java.awt.Color;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JCheckBox;

/**
 *
 * @author Antonio
 */
public class MyCheckFormItem extends FormItem implements Constants{
    
    public MyCheckFormItem(String name, Class dataMappingType, String caption, boolean required, FormItemType type, Object defaultValue, boolean visible){
        super(name,dataMappingType,caption,required,type,defaultValue,visible);
        createField();
    }
    
    @Override
    protected final void createField() {
        JCheckBox c = new JCheckBox();
        c.setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
        c.setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
        c.setFont(FIELDS_FONT);
        c.setSelected((Boolean)defaultValue);
        this.field = c;
    }
    
    @Override
    public void reset(){
        ((JCheckBox)field).setSelected((Boolean)defaultValue);
    }

    @Override
    public String getStringValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getIntegerValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getDoubleValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getDateValue() throws ParseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean isSelected(){
        return ((JCheckBox)field).isSelected();
    }
}