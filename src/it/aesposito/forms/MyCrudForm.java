/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import it.aesposito.application.Constants;
import it.aesposito.db.SQLMapClientFactory;
import static it.aesposito.forms.FormItemType.TEXTAUTOCOMPLETE;
import static it.aesposito.forms.FormItemType.TIMESTAMP;
import it.aesposito.utils.Utils;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import org.apache.ibatis.session.SqlSession;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Antonio
 */
public class MyCrudForm extends MySpecialForm implements Constants{
    
    protected List<FormItem> formItems; 
    protected List<FormButton> nonStandardButtons;
    protected List<StandardButtons> standardButtons;
    protected List<FormButton> allButtons;
    
    protected JToolBar buttonsContainer;
    protected JPanel formContainer;
    protected Integer recordId;
    protected Class mapper;
    protected Class recordType;
    protected String referenceTable;
    
    public MyCrudForm(Container father, String title,Integer recordId,MainApplicationForm parent_frame, 
                         Class mapper, Class recordType, String referenceTable, boolean resizable, boolean closable, 
                         boolean maximizable, boolean iconable){
        super(father, parent_frame,title);
        this.recordId = recordId;
        this.mapper = mapper;
        this.referenceTable = referenceTable;
        this.recordType = recordType;
        
        formItems = new ArrayList<FormItem>();
        standardButtons = new ArrayList<StandardButtons>();
        nonStandardButtons = new ArrayList<FormButton>();
    }
    
    private void createButtonsContainer(){
        buttonsContainer = new JToolBar("Azioni", JToolBar.HORIZONTAL);
        buttonsContainer.setFloatable(false);
        buttonsContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        allButtons = new ArrayList<FormButton>();
        
        if(standardButtons.contains(StandardButtons.INSERT)){
            
            allButtons.add(new FormButton("Inserisci", new ImageIcon(getClass()
				.getClassLoader().getResource("plus.png")), new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    MyFormHelper formHelper = new MyFormHelper();
                    int val = formHelper.validateForm(formItems);
                    if(val == -1){
                        JOptionPane.showMessageDialog(formContainer, "Compilare tutti i campi obbligatori");
                        return;
                    }else if(val == -2){
                        JOptionPane.showMessageDialog(formContainer, "Immettere un valore numerico");
                        return;
                    }
                    try {
                        insertRecord();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    parent_frame.refreshChilds();

                    setReadOnlyMode();
                }
            }, null, new FormModes[]{FormModes.INSERT_MODE}));
            
        }
        
        if(standardButtons.contains(StandardButtons.SAVE)){
            allButtons.add(new FormButton("Salva Modifiche",new ImageIcon(getClass()
				.getClassLoader().getResource("save.png")),new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    MyFormHelper formHelper = new MyFormHelper();
                    int val = formHelper.validateForm(formItems);
                    if(val == -1){
                        JOptionPane.showMessageDialog(formContainer, "Compilare tutti i campi obbligatori");
                        return;
                    }else if(val == -2){
                        JOptionPane.showMessageDialog(formContainer, "Immettere un valore numerico");
                        return;
                    }
                    try {
                        updateRecord();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    parent_frame.refreshChilds();

                    setReadOnlyMode();
                }
            },null,new FormModes[]{FormModes.MODIFY_MODE}));
            
        }
        
        if(standardButtons.contains(StandardButtons.ANNULLAMODIFICHE)){
            allButtons.add(new FormButton("Annulla Modifiche",new ImageIcon(getClass()
				.getClassLoader().getResource("undo.png")),new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        loadRecordFromDB();
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(MyCrudForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(MyCrudForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(MyCrudForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(MyCrudForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    setReadOnlyMode();
                }
            },null,new FormModes[]{FormModes.MODIFY_MODE}));
            
        }
        
        if(standardButtons.contains(StandardButtons.MODIFY)){
            allButtons.add(new FormButton("Modifica", new ImageIcon(getClass()
				.getClassLoader().getResource("pencil.png")), new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    setModifyMode();

                }
            }, null, new FormModes[]{FormModes.READ_ONLY_MODE}));

        }
        
        if(standardButtons.contains(StandardButtons.CLOSE)){
            allButtons.add(new FormButton("Chiudi", new ImageIcon(getClass()
				.getClassLoader().getResource("exit.png")), new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    close();
                    
                }
            }, null, new FormModes[]{FormModes.READ_ONLY_MODE, FormModes.INSERT_MODE, FormModes.MODIFY_MODE}));

        }
        
        allButtons.addAll(nonStandardButtons);
        
        for (FormButton b : allButtons) {
            b.setButton(new JButton(b.getLabel(),b.getIcon()));
            b.getButton().addActionListener(b.getActions());
            b.getButton().setPreferredSize(new Dimension(FORM_BUTTON_WIDTH,FORM_BUTTON_HEIGHT));
            b.getButton().setVerticalTextPosition(SwingConstants.BOTTOM);
            b.getButton().setHorizontalTextPosition(SwingConstants.CENTER);
            buttonsContainer.add(b.getButton());
        }
    }
    
    protected void createForm(){
        createButtonsContainer();
                
        MyFormHelper formHelper = new MyFormHelper();
        formContainer = formHelper.packForm(formItems);
        
        mainContainer.add(buttonsContainer);
        mainContainer.add(formContainer);
        this.add(mainContainer);
        
    }
    
    public void setFormItemsEnabled(boolean state){
        for (FormItem item : formItems) {
            switch(item.getType()){
                case TIMESTAMP: break;
                case TEXTAREA: ((MyTextArea)item).getTextArea().setEditable(state);
                            break;
                default : item.getField().setEnabled(state);
                          break;
            }
        }
    }
    
    public void setInsertMode(){
        
        setFormItemsEnabled(true);
        form_mode = FormModes.INSERT_MODE;
        
        updateButtonsStatus();
    }
    
    protected void setModifyMode(){
        
        setFormItemsEnabled(true);
        form_mode = FormModes.MODIFY_MODE;
        
        updateButtonsStatus();
    }
    
    protected void setReadOnlyMode(){
        
        setFormItemsEnabled(false);
        form_mode = FormModes.READ_ONLY_MODE;
        
        updateButtonsStatus();
    }
    
    protected void updateButtonsStatus(){
        for (FormButton b : allButtons) {
            if(Arrays.asList(b.getModesEnabled()).contains(form_mode))
                b.getButton().setEnabled(true);
            else
                b.getButton().setEnabled(false);
        }
    }
    
    public JPanel getFormContainer() {
        return formContainer;
    }
    
    public void fillBean(Object bean) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
        
        for (FormItem formItem : formItems) {
            Method m = bean.getClass().getMethod("set" + Utils.capitalizeFirstLetter(formItem.getName()), new Class[]{formItem.getDataMappingType()});
            
            if(formItem.getDataMappingType() == Integer.class)
                m.invoke(bean, formItem.getIntegerValue());
            
            if(formItem.getDataMappingType() == Double.class)
                m.invoke(bean, formItem.getDoubleValue());
            
            if(formItem.getDataMappingType() == String.class)
                m.invoke(bean, formItem.getStringValue());
            
            if(formItem.getDataMappingType() == Date.class)
                m.invoke(bean, formItem.getDateValue());
        }
        
    }
    
    public void fillForm(Object bean) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
        for (FormItem formItem : formItems) {
            Method m = bean.getClass().getMethod("get" + Utils.capitalizeFirstLetter(formItem.getName()), (Class<?>[]) null);
            Object value = m.invoke(bean, new Object[0]);
            if(value == null) continue;
            switch(formItem.getType()){
                case TEXTAREA: ((MyTextArea)formItem).getTextArea().setText("" + value);
                                break;
                case TEXTINPUT:
                case TEXTAUTOCOMPLETE: ((JTextField)formItem.getField()).setText("" + value);
                                       break;
                case DATEPICKER: ((JXDatePicker)formItem.getField()).setDate((Date)value);
                                    break;
                case COMBOBOX: JComboBox combo = ((JComboBox)formItem.getField());
                                
                                int i = 0;
                                for (; i < combo.getItemCount(); i++) {
                                    ComboItem c = (ComboItem)combo.getModel().getElementAt(i);
                                    if(c.getValore().equals(value)){
                                        combo.setSelectedIndex(i);
                                        break;
                                    }
                               }
                               if(i==combo.getItemCount()){
                                    combo.addItem(value);
                                    combo.setSelectedIndex(i);
                               }
                               
            }
        }
        
    }
    
    public void loadRecordFromDB() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
        SqlSession session = null;
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(MyCrudForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return;
        }
        
        Object obj = session.getMapper(mapper);
        Method m = obj.getClass().getMethod("selectByPrimaryKey", new Class[]{Integer.class});
        Object record = m.invoke(obj, new Object[]{recordId});
        
        try {
            fillForm(record);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void insertRecord() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(MyCrudForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return;
        }

        try {
            recordId = Utils.getNextVal(referenceTable);
        } catch(Exception ex){
            Utils.logError(MyCrudForm.class,ex);
            Utils.showErrorAlert("Errore nella generazione dell'identificativo");
            return;
        }

        Object record = recordType.newInstance();
        
        try{
            fillBean(record);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        Method m = record.getClass().getMethod("setId", new Class[]{Integer.class});
        m.invoke(record, new Object[]{recordId});
        
        Object obj = session.getMapper(mapper);
        m = obj.getClass().getMethod("insert", new Class[]{record.getClass()});
        m.invoke(obj, new Object[]{record});
        
        session.commit();
        session.close();
    }
    
    public void updateRecord() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
        SqlSession session = null;
        
        try {
            session = SQLMapClientFactory.getSqlMapClient().openSession();
        } catch (IOException ex) {
            Utils.logError(MyCrudForm.class,ex);
            Utils.showErrorAlert("Non è stato possibile accedere al Database");
            return;
        }

        Object record = recordType.newInstance();
        
        try{
            fillBean(record);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        Method m = record.getClass().getMethod("setId", new Class[]{Integer.class});
        m.invoke(record, new Object[]{recordId});
        
        Object obj = session.getMapper(mapper);
        try{
            m = obj.getClass().getMethod("updateByPrimaryKeyWithBLOBs", new Class[]{record.getClass()});
        }catch(NoSuchMethodException ex){
            m = obj.getClass().getMethod("updateByPrimaryKey", new Class[]{record.getClass()});
        }
        m.invoke(obj, new Object[]{record});
        
        session.commit();
        session.close();
    }

}   