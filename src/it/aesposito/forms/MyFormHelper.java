/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import it.aesposito.application.Constants;
import static it.aesposito.application.Constants.DEFAULT_FIELD_BACKGROUND;
import static it.aesposito.application.Constants.DEFAULT_FIELD_FOREGROUND;
import static it.aesposito.application.Constants.ERROR_FIELD_BACKGROUND;
import static it.aesposito.application.Constants.ERROR_FIELD_FOREGROUND;
import static it.aesposito.application.Constants.FORM_BORDER_COLOR;
import static it.aesposito.forms.FormItemType.DATEPICKER;
import static it.aesposito.forms.FormItemType.TEXTAUTOCOMPLETE;
import static it.aesposito.forms.FormItemType.TEXTINPUT;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Antonio
 */
public class MyFormHelper {
    
    public MyFormHelper(){
        
    }
    
    public JPanel packForm(List<FormItem> formItems){
        JPanel formContainer = new JPanel();
        JPanel innerFormContainer = packInnerForm(formItems);
        formContainer.add(innerFormContainer);
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(BorderFactory.createLineBorder(Color.decode(FORM_BORDER_COLOR)));
        return formContainer;
    }
    
    public JPanel packForm(List<FormItem> formItems, LayoutManager lm){
        JPanel formContainer = new JPanel();
        JPanel innerFormContainer = packInnerForm(formItems,lm);
        innerFormContainer.setLayout(lm);
        formContainer.add(innerFormContainer);
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(BorderFactory.createLineBorder(Color.decode(FORM_BORDER_COLOR)));
        return formContainer;
    }
    
    public JPanel packFiltersForm(List<FormItem> formItems,JButton reset){
        JPanel formContainer = new JPanel();
        JPanel innerFormContainer = packInnerForm(formItems,new FlowLayout(FlowLayout.LEFT));
        innerFormContainer.add(reset);
        formContainer.add(innerFormContainer);
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(BorderFactory.createLineBorder(Color.decode(FORM_BORDER_COLOR)));
        return formContainer;
    }
    
    public JPanel packFiltersForm(List<FormItem> formItems, LayoutManager lm,JButton reset,int cols){
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.PAGE_AXIS));
        for (int i = 0; i < formItems.size();) {
            List<FormItem> workList = new ArrayList<FormItem>();
            for (int j = 0; j < cols && i < formItems.size(); j++, i++) {
                workList.add(formItems.get(i));
            }
            formContainer.add(packInnerForm(workList,lm));
        }
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(reset);
        formContainer.add(buttonsPanel);
        
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(BorderFactory.createLineBorder(Color.decode(FORM_BORDER_COLOR)));
        return formContainer;
    }
    
    private JPanel packInnerForm(List<FormItem> formItems,LayoutManager lm){
        JPanel innerFormContainer = new JPanel();
        
        int visibleItems = 0;
        for (FormItem formItem : formItems) {
            
            if(formItem.isVisible()){
                if(!"".equals(formItem.getShowableLabel())){
                    JLabel label = new JLabel(formItem.getShowableLabel());
                    innerFormContainer.add(label);
                }
                
                innerFormContainer.add(formItem.getField());
                visibleItems++;
            }
        }
        
        innerFormContainer.setBorder(new EmptyBorder(10,10,10,10));
        innerFormContainer.setBackground(Color.WHITE);
        innerFormContainer.setLayout(lm);
        return innerFormContainer;
    }
    
    private JPanel packInnerForm(List<FormItem> formItems){
        JPanel innerFormContainer = new JPanel();
        
        int visibleItems = 0;
        for (FormItem formItem : formItems) {
            
            if(formItem.isVisible()){
                if(!"".equals(formItem.getShowableLabel())){
                    JLabel label = new JLabel(formItem.getShowableLabel());
                    innerFormContainer.add(label);
                }
                
                innerFormContainer.add(formItem.getField());
                visibleItems++;
            }
        }
        
        innerFormContainer.setBorder(new EmptyBorder(10,10,10,10));
        innerFormContainer.setBackground(Color.WHITE);
        innerFormContainer.setLayout(new GridLayout(visibleItems,2,Constants.FORM_HORIZONTAL_GAP,Constants.FORM_VERTICAL_GAP));
        return innerFormContainer;
    }
    
    public int validateForm(List<FormItem> formItems){
        
        for (FormItem item : formItems) {
            if(item.isRequired()){
            
                switch(item.getType()){
                    case TEXTAUTOCOMPLETE:
                    case TEXTINPUT:
                        if(((JTextField)item.getField()).getText().trim().equals("")){
                            item.getField().setBackground(Color.decode(ERROR_FIELD_BACKGROUND));
                            item.getField().setForeground(Color.decode(ERROR_FIELD_FOREGROUND));
                            return -1;
                        }
                        item.getField().setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
                        item.getField().setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
                        break;
                    case COMBOBOX:
                        ComboItem selected = (ComboItem)((JComboBox)item.getField()).getSelectedItem();
                        if(selected.getValore().equals(-1)){
                            item.getField().setBackground(Color.decode(ERROR_FIELD_BACKGROUND));
                            item.getField().setForeground(Color.decode(ERROR_FIELD_FOREGROUND));
                            return -1;
                        }
                        item.getField().setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
                        item.getField().setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
                        break;
                    case DATEPICKER: 
                        if(((JXDatePicker)item.getField()).getDate() == null){
                            ((JXDatePicker)item.getField()).getEditor().setBackground(Color.decode(ERROR_FIELD_BACKGROUND));
                            ((JXDatePicker)item.getField()).getEditor().setForeground(Color.decode(ERROR_FIELD_FOREGROUND));
                            return -1;
                        }
                        ((JXDatePicker)item.getField()).getEditor().setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
                        ((JXDatePicker)item.getField()).getEditor().setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
                }
                
            }
            
            if(item.getType() == TEXTINPUT){
                if(item.getDataMappingType() == Integer.class){
                    try{
                        Integer.parseInt(((JTextField)item.getField()).getText());
                    }catch(NumberFormatException ex){
                        item.getField().setBackground(Color.decode(ERROR_FIELD_BACKGROUND));
                        item.getField().setForeground(Color.decode(ERROR_FIELD_FOREGROUND));
                        return -2;
                    }
                }

                if(item.getDataMappingType() == Double.class){
                    try{
                        Double.parseDouble(((JTextField)item.getField()).getText());
                    }catch(NumberFormatException ex){
                        item.getField().setBackground(Color.decode(ERROR_FIELD_BACKGROUND));
                        item.getField().setForeground(Color.decode(ERROR_FIELD_FOREGROUND));
                        return -2;
                    }
                }
                item.getField().setBackground(Color.decode(DEFAULT_FIELD_BACKGROUND));
                item.getField().setForeground(Color.decode(DEFAULT_FIELD_FOREGROUND));
            }
        }
        
        return 0;
    }
}
