/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.forms.ArticoliListUpdater;
import it.aesposito.forms.FormItem;
import it.aesposito.forms.FormItemType;
import it.aesposito.forms.MyComboFormItem;
import it.aesposito.forms.MyFormHelper;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
/**
 *
 * @author Antonio
 */
public class ListiniPanel extends JPanel{
    private Integer tipologiaArticolo;
    private Integer selectedValue;
    private ArticoliListUpdater listUpdater;
    private MyComboFormItem listino;

    public ListiniPanel(Integer tipologiaArticolo,ArticoliListUpdater listUpdater, Integer selectedValue){
        this.tipologiaArticolo = tipologiaArticolo;
        this.selectedValue = selectedValue;
        this.listUpdater = listUpdater;
        
        initComponents();
    }
    
    private void initComponents(){
        this.setBorder(BorderFactory.createTitledBorder("<html><b> Listino </b></html>"));
        List<FormItem> formItems = new ArrayList<FormItem>();
        listino = new MyComboFormItem("listino",  Integer.class, "", true, FormItemType.COMBOBOX, selectedValue,true,Combos.getListiniComboModel(tipologiaArticolo,new Combos.Options[]{Combos.Options.NO_DEFAULT}));
        formItems.add(listino);
        
        ((JComboBox)listino.getField()).addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    listUpdater.updateListino(listino.getIntegerValue(),tipologiaArticolo);
                }
            }
        });
        
        MyFormHelper formHelper = new MyFormHelper();
        this.add(formHelper.packForm(formItems));
                
    }
    
    public MyComboFormItem getListino() {
        return listino;
    }

    public void setListino(MyComboFormItem listino) {
        this.listino = listino;
    }
    
    @Override
    public void setEnabled(boolean enabled){
        this.listino.getField().setEnabled(enabled);
    }
}
