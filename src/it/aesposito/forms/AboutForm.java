/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Antonio
 */
public class AboutForm extends MySpecialForm{
    
    public AboutForm(Container father, Integer fId, MainApplicationForm pf,String title, boolean resizable, boolean closable, boolean maximizable, boolean iconable){
        super(father, pf, title);
        initComponents();
    }
    
    private void initComponents(){
        this.setSize(400,100);
        this.setEnabled(true);
        this.setVisible(true);
        this.setForm_mode(FormModes.READ_ONLY_MODE);
        JPanel mainAboutPanel = new JPanel();
        mainAboutPanel.setBorder(new EmptyBorder(10,10,10,10));
        mainAboutPanel.setLayout(new GridBagLayout());
        mainAboutPanel.setBackground(Color.WHITE);
        
        JLabel info = new JLabel("<html><b>jStore: a special store management software</b> - Designed by Antonio Esposito</html>");
        info.setForeground(Color.BLUE);
        mainAboutPanel.add(info);
        
        this.add(mainAboutPanel);
    }

}
