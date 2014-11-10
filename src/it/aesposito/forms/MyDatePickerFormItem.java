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
import java.util.Locale;
import javax.swing.JComboBox;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Antonio
 */
public class MyDatePickerFormItem extends FormItem implements Constants{
    
    public MyDatePickerFormItem(String name, Class dataMappingType, String caption, boolean required, FormItemType type, Object defaultValue, boolean visible){
        super(name,dataMappingType,caption,required,type,defaultValue,visible);
        createField();
    }
    
    @Override
    protected final void createField() {
        JXDatePicker c = new JXDatePicker(Locale.ITALY);
        ((JXDatePicker)c).getEditor().setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
        ((JXDatePicker)c).getEditor().setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
        ((JXDatePicker)c).getEditor().setFont(FIELDS_FONT);
        ((JXDatePicker)c).setDate((Date)defaultValue);
        c.setPreferredSize(STANDARD_FIELD_DIM);
        this.field = c;
    }
    
    @Override
    public void reset(){
        ((JXDatePicker)field).setDate(null);
    }
    
    @Override
    public String getStringValue() {
        return Constants.DATE_SDF.format(((JXDatePicker)field).getDate());
    }

    @Override
    public Integer getIntegerValue() {
        return 0;
    }

    @Override
    public Double getDoubleValue() {
        return 0.0;
    }

    @Override
    public Date getDateValue() throws ParseException {
        return ((JXDatePicker)field).getDate();
    }
    
}
