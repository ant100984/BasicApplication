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
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;
import javax.swing.JComboBox;
import org.jdesktop.swingx.JXTextField;

/**
 *
 * @author Antonio
 */
public class MyComboFormItem extends FormItem implements Constants{
    protected Vector<ComboItem> combo;
    
    public MyComboFormItem(String name, Class dataMappingType, String caption, boolean required, FormItemType type, Object defaultValue, boolean visible, Vector<ComboItem> combo){
        super(name,dataMappingType,caption,required,type,defaultValue,visible);
        this.combo = combo;
        createField();
    }
    
    @Override
    protected final void createField() {
        JComboBox c = new JComboBox(combo);
        c.setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
        c.setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
        c.setFont(FIELDS_FONT);
        for (int i = 0; i < c.getItemCount(); i++) {
            ComboItem ci = (ComboItem)c.getModel().getElementAt(i);
            if(ci.getValore().equals(defaultValue)){
                c.setSelectedIndex(i);
                break;
            }
        }
        c.setPreferredSize(STANDARD_FIELD_DIM);
        this.field = c;
    }
    
    @Override
    public void reset(){
        ((JComboBox)field).setSelectedIndex(0);
    }
    
    @Override
    public String getStringValue() {
        return (String)((ComboItem)((JComboBox)field).getSelectedItem()).getValore();
    }

    @Override
    public Integer getIntegerValue() {
        return (Integer)((ComboItem)((JComboBox)field).getSelectedItem()).getValore();
    }

    @Override
    public Double getDoubleValue() {
        return (Double)((ComboItem)((JComboBox)field).getSelectedItem()).getValore();
    }

    @Override
    public Date getDateValue() throws ParseException {
        return (Date)((ComboItem)((JComboBox)field).getSelectedItem()).getValore();
        
    }
    
}