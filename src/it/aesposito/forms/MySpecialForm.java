/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import static it.aesposito.forms.FormModes.INSERT_MODE;
import static it.aesposito.forms.FormModes.MODIFY_MODE;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Antonio
 */
public class MySpecialForm extends JPanel{
    protected MainApplicationForm parent_frame;
    protected FormModes form_mode;
    protected JPanel mainContainer;
    protected Container father;
    
    public MySpecialForm(Container father, MainApplicationForm pf, String title){
        super();
        this.parent_frame = pf;
        this.father = father;
        
        mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        mainContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
    
    public FormModes getForm_mode() {
        return form_mode;
    }

    public void setForm_mode(FormModes form_mode) {
        this.form_mode = form_mode;
    }
    
    public void close(){
        switch(form_mode){
            case INSERT_MODE:
            case MODIFY_MODE:
                
                if(JOptionPane.showConfirmDialog(father, "Sei sicuro di voler uscire? I dati non salvati andranno persi.","Attenzione",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    father.setVisible(false);
                    parent_frame.refreshChilds();
                }
                
                break;
            default:
                father.setVisible(false);
                parent_frame.refreshChilds();
        }
    }
}
