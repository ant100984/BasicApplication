/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.FormItemType;
import it.aesposito.forms.MyCrudForm;
import it.aesposito.forms.MyTextInput;
import it.aesposito.forms.StandardButtons;
import it.aesposito.jstore.bean.Categorie;
import it.aesposito.jstore.dao.CategorieMapper;
import java.awt.Container;
/**
 *
 * @author Antonio
 */
public class CategorieForm extends MyCrudForm{
    private Integer tipologiaArticolo;
    
    public CategorieForm(Container father, MainApplicationForm parent_frame, String title, boolean resizable, boolean closable, boolean maximizable, boolean iconable,Integer recordId,Integer tipologiaArticolo){
        super(father, title, recordId, parent_frame, CategorieMapper.class, Categorie.class, "categorie", resizable, closable, maximizable, iconable);
        this.tipologiaArticolo = tipologiaArticolo;
        
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
        
        standardButtons.add(StandardButtons.INSERT);
        standardButtons.add(StandardButtons.SAVE);
        standardButtons.add(StandardButtons.MODIFY);
        standardButtons.add(StandardButtons.ANNULLAMODIFICHE);
        standardButtons.add(StandardButtons.CLOSE);
        
        formItems.add(new MyTextInput("descrizione", String.class, "Descrizione", true, FormItemType.TEXTINPUT, 255,"",true));
        formItems.add(new MyTextInput("tipologiaArticolo", Integer.class, "", true, FormItemType.TEXTINPUT,1,tipologiaArticolo.toString(),false));
        
        super.createForm();
    }
    
}
