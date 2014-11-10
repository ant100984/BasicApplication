/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.FormItemType;
import it.aesposito.forms.MyComboFormItem;
import it.aesposito.forms.MyCrudForm;
import it.aesposito.forms.MyTextInput;
import it.aesposito.forms.StandardButtons;
import it.aesposito.jstore.bean.ApplicationProperties;
import it.aesposito.jstore.dao.ApplicationPropertiesMapper;
import java.awt.Container;
/**
 *
 * @author Antonio
 */
public class PreferenzeForm extends MyCrudForm{
        
    public PreferenzeForm(Container father, MainApplicationForm parent_frame, String title, boolean resizable, boolean closable, boolean maximizable, boolean iconable,Integer recordId,Integer formId){
        super(father, title, recordId, parent_frame, ApplicationPropertiesMapper.class, ApplicationProperties.class, "applicationProperties", resizable, closable, maximizable, iconable);
        
        initComponents();
        
        if(recordId != null && recordId != 0){
            try {
                loadRecordFromDB();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            setReadOnlyMode();
        }else
            setInsertMode();
    }
    
    private void initComponents(){
        this.setSize(PREFERENZE_FORM_WIDTH,PREFERENZE_FORM_HEIGHT);
        
        standardButtons.add(StandardButtons.SAVE);
        standardButtons.add(StandardButtons.MODIFY);
        standardButtons.add(StandardButtons.ANNULLAMODIFICHE);
        standardButtons.add(StandardButtons.CLOSE);
        
        formItems.add(new MyTextInput("ragioneSociale", String.class, "Ragione Sociale Azienda", false, FormItemType.TEXTINPUT, 255,"",true));
        formItems.add(new MyTextInput("pivaCf", String.class, "P.iva/Cf", false, FormItemType.TEXTINPUT, 16,"",true));
        formItems.add(new MyTextInput("indirizzo", String.class, "Indirizzo", false, FormItemType.TEXTINPUT, 225,"",true));
        formItems.add(new MyTextInput("cap", String.class, "Cap", false, FormItemType.TEXTINPUT, 5,"",true));
        formItems.add(new MyTextInput("citta", String.class, "Citta'", false, FormItemType.TEXTINPUT, 255,"",true));
        formItems.add(new MyTextInput("telefono", String.class, "Telefono", false, FormItemType.TEXTINPUT, 50,"",true));
        formItems.add(new MyTextInput("email", String.class, "Email", false, FormItemType.TEXTINPUT, 100,"",true));
        
        formItems.add(new MyComboFormItem("ivaProdotti", Double.class, "Iva Prodotti default", true, FormItemType.COMBOBOX, "",true,Combos.getIvaComboModel()));
        formItems.add(new MyComboFormItem("ivaPrestazioni", Double.class, "Iva Prestazioni default", true, FormItemType.COMBOBOX, "",true,Combos.getIvaComboModel()));
        
        formItems.add(new MyComboFormItem("listinoProdotti", Integer.class, "Listino Prodotti default", true, FormItemType.COMBOBOX, "",true,Combos.getListiniComboModel(1,new Combos.Options[]{})));
        formItems.add(new MyComboFormItem("listinoPrestazioni", Integer.class, "Listino Prestazioni default", true, FormItemType.COMBOBOX, "",true,Combos.getListiniComboModel(2,new Combos.Options[]{})));
        
        formItems.add(new MyComboFormItem("theme", String.class, "Combinazione colori", true, FormItemType.COMBOBOX, "",true,Combos.getThemesComboModel()));
        
        super.createForm();
    }
    
}
