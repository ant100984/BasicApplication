/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import java.awt.Component;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JComponent;

/**
 *
 * @author Antonio
 */
public abstract class FormItem {
    protected JComponent field;
    protected boolean required;
    protected FormItemType type;
    protected String label;
    protected String name;
    protected Class dataMappingType;
    protected Object defaultValue;
    protected boolean visible;

    public FormItem(String name, Class dataMappingType, String label, boolean required, FormItemType type, Object defaultValue, boolean visible){
        this.label = label;
        this.required = required;
        this.type = type;
        this.name = name;
        this.dataMappingType = dataMappingType;
        this.defaultValue = defaultValue;
        this.visible = visible;
    }
    
    protected abstract void createField();
    
    public abstract String getStringValue();
    
    public abstract Integer getIntegerValue();
    
    public abstract Double getDoubleValue();
    
    public abstract Date getDateValue() throws ParseException;
    
    public abstract void reset();
    
    public Component getField() {
        return field;
    }
    
    public void setField(JComponent field) {
        this.field = field;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public FormItemType getType() {
        return type;
    }

    public void setType(FormItemType type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }
    
    public String getShowableLabel(){
        return label==null || "".equals(label)?"":(required ? "<html><b>" + label + " <span style='color: red;'>*</span></b></html>" : "<html><b>" + label + "</b></html>");
    }
    
    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getDataMappingType() {
        return dataMappingType;
    }

    public void setDataMappingType(Class dataMappingType) {
        this.dataMappingType = dataMappingType;
    }
    
    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
   
}
