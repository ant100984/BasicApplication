/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.db.SQLMapClientFactory;
import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.FormItemType;
import it.aesposito.forms.MyComboFormItem;
import it.aesposito.forms.MyCrudForm;
import it.aesposito.forms.MyTextArea;
import it.aesposito.forms.MyTextInput;
import it.aesposito.forms.MyTimestampFormItem;
import it.aesposito.forms.StandardButtons;
import it.aesposito.jstore.bean.ApplicationProperties;
import it.aesposito.jstore.bean.ApplicationPropertiesExample;
import it.aesposito.jstore.bean.Articoli;
import it.aesposito.jstore.dao.ApplicationPropertiesMapper;
import it.aesposito.jstore.dao.ArticoliMapper;
import java.awt.Container;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.session.SqlSession;
/**
 *
 * @author Antonio
 */
public class ArticoloForm extends MyCrudForm{
        
    public ArticoloForm(Container father, MainApplicationForm parent_frame, Integer articoloId){
        super(father, "Gestione Articolo", articoloId, parent_frame, ArticoliMapper.class, Articoli.class, "articoli", true, false, false,false);

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
        this.setSize(ARTICOLO_FORM_WIDTH,ARTICOLO_FORM_HEIGHT);
        
        SqlSession session = null;
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Logger.getLogger(ArticoloForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ApplicationPropertiesMapper apm = session.getMapper(ApplicationPropertiesMapper.class);
        ApplicationProperties ap = apm.selectByExample(new ApplicationPropertiesExample()).get(0);
        
        standardButtons.add(StandardButtons.INSERT);
        standardButtons.add(StandardButtons.SAVE);
        standardButtons.add(StandardButtons.MODIFY);
        standardButtons.add(StandardButtons.ANNULLAMODIFICHE);
        standardButtons.add(StandardButtons.CLOSE);
        
        formItems.add(new MyTextInput("codice", String.class, "Codice", false, FormItemType.TEXTINPUT, 255,"",true));
        formItems.add(new MyTextInput("descrizione", String.class, "Descrizione", true, FormItemType.TEXTINPUT, 255,"",true));
        formItems.add(new MyComboFormItem("categoria", Integer.class, "Categoria", true, FormItemType.COMBOBOX, -1,true,Combos.getCategorieComboModel(1)));
        
        formItems.add(new MyTextInput("giacenza", Integer.class, "Giacenza", true, FormItemType.TEXTINPUT, 5,"0",true));
        formItems.add(new MyTextInput("importoUnitario", Double.class, "Importo Unitario", true, FormItemType.TEXTINPUT, 10,"",true));
        formItems.add(new MyTextInput("scarsita", Integer.class, "Avvertimi se la giacenza è inferiore a", true, FormItemType.TEXTINPUT, 5,"5",true));
        
        formItems.add(new MyComboFormItem("iva", Double.class, "Iva", true, FormItemType.COMBOBOX, ap.getIvaProdotti(),true,Combos.getIvaComboModel()));
        formItems.add(new MyTextArea("note", String.class, "Note", false, FormItemType.TEXTAREA, 255,"",true));
        
        formItems.add(new MyComboFormItem("tipologiaArticolo",  Integer.class, "Tipo",      true, FormItemType.COMBOBOX, 1,false,Combos.getTipologiaArticoloComboModel(Combos.ComboTipoArticoloConf.CODICE)));
        formItems.add(new MyTimestampFormItem("dtmIns", Date.class, "dtmIns", true, FormItemType.TIMESTAMP, new Date(), false));
        formItems.add(new MyTimestampFormItem("dtmMod", Date.class, "dtmMod", true, FormItemType.TIMESTAMP, new Date(), false));
        
        super.createForm();
    }
    
}
