/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import it.aesposito.application.Constants;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Antonio
 */
public abstract class MyListItemsForm extends MySpecialForm implements Constants{
    protected JPanel filterPanel;
    protected JPanel filterButtonPanel;
    protected JScrollPane tableContainer;
    protected List<FormItem> filtriFormItems;
    protected JPanel outerFiltriPanel;
    protected JPanel innerFiltriPanel;
    protected MyTable myTable;
    protected JPanel resultsInfo;
    protected JLabel resultsLabel;
    protected List<FormButton> nonStandardButtons;
    protected List<StandardButtons> standardButtons;
    protected List<FormButton> allButtons;
    protected JToolBar buttonsContainer;
    protected boolean addToContainer;
    protected JPanel tabNavigationPanel;
    protected int currPage = 1;
    protected int numPages;
    protected JLabel pagesInfo;
    
    public MyListItemsForm(Container father, Integer formId, MainApplicationForm parent_frame, String title, boolean addToContainer){
        super(father, parent_frame,title);
        this.setEnabled(true);
        this.setVisible(true);
        this.myTable = new MyTable(parent_frame);
        this.myTable.setRowHeight(TABLE_ROW_HEIGHT);
        this.addToContainer = addToContainer;
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        filtriFormItems = new ArrayList<FormItem>();
        mainContainer = new JPanel();
        mainContainer.setBorder(new EmptyBorder(10,10,30,10));
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        
        standardButtons = new ArrayList<StandardButtons>();
        nonStandardButtons = new ArrayList<FormButton>();
    }
    
    public MyListItemsForm(Container father, Integer formId, MainApplicationForm parent_frame, String title){
        this(father,formId,parent_frame,title,true);
    }
    
    protected void initComponents(){
        createButtonsContainer();
        if(addToContainer) mainContainer.add(buttonsContainer);
        
        MyFormHelper mfh = new MyFormHelper();
        
        JButton reset = new JButton("Reset",new ImageIcon(getClass()
				.getClassLoader().getResource("cancel_small.png")));
        reset.setHorizontalTextPosition(SwingConstants.CENTER);
        reset.setVerticalTextPosition(SwingConstants.BOTTOM);
        reset.setPreferredSize(new Dimension(Constants.MISC_BUTTON_WIDTH,Constants.MISC_BUTTON_HEIGHT));
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (FormItem formItem : getFormItems()) {
                    formItem.reset();
                }
                myTable.resetSelection();
                loadList();
                refreshInfoComponents();
            }
            
        });
        
        if(getFormItems() != null && getFormItems().size() > 0){
            innerFiltriPanel = mfh.packFiltersForm(getFormItems(),new FlowLayout(FlowLayout.LEFT),reset,3);
        
            outerFiltriPanel = new JPanel();
            outerFiltriPanel.setLayout(new BoxLayout(outerFiltriPanel, BoxLayout.Y_AXIS));
            outerFiltriPanel.setBorder(BorderFactory.createTitledBorder("<html><b> Filtri </b></html>"));
            outerFiltriPanel.add(innerFiltriPanel);

            mainContainer.add(outerFiltriPanel);
        }
        
        resultsLabel = new JLabel();
        
        tabNavigationPanel = new JPanel();
        tabNavigationPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton goToFirst = new JButton("",new ImageIcon(getClass().getClassLoader().getResource("first.png")));
        goToFirst.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currPage = 1;
                loadList();
                refreshInfoComponents();
                refreshTabPagesInfo();
            }
        });
        JButton goToLast = new JButton("",new ImageIcon(getClass().getClassLoader().getResource("last.png")));
        goToLast.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currPage = numPages;
                loadList();
                refreshInfoComponents();
                refreshTabPagesInfo();
            }
        });
        
        JButton goToPrev = new JButton("",new ImageIcon(getClass().getClassLoader().getResource("prev.png")));
        goToPrev.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(currPage > 1) currPage -= 1;
                loadList();
                refreshInfoComponents();
                refreshTabPagesInfo();
            }
        });
        JButton goToSucc = new JButton("",new ImageIcon(getClass().getClassLoader().getResource("succ.png")));
        goToSucc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(currPage < numPages) currPage += 1;
                loadList();
                refreshInfoComponents();
                refreshTabPagesInfo();
            }
        });
        Dimension dim = new Dimension(Constants.MISC_BUTTON_WIDTH,Constants.MISC_BUTTON_HEIGHT);
        goToFirst.setPreferredSize(dim);
        goToLast.setPreferredSize(dim);
        goToPrev.setPreferredSize(dim);
        goToSucc.setPreferredSize(dim);
        
        pagesInfo = new JLabel();
        tabNavigationPanel.add(resultsLabel);
        tabNavigationPanel.add(goToFirst);
        tabNavigationPanel.add(goToPrev);
        tabNavigationPanel.add(pagesInfo);
        tabNavigationPanel.add(goToSucc);
        tabNavigationPanel.add(goToLast);
        
        tableContainer = new JScrollPane(myTable);
        
        if(addToContainer) {
            
            mainContainer.add(tabNavigationPanel);
            mainContainer.add(tableContainer);
        
            this.add(mainContainer);
        }
    }
    
    public abstract List loadList();
    
    public abstract void refreshInfoComponents();
    
    public abstract List<FormItem> getFormItems();
    
    public abstract void openInsertDialog();
    
    public MyTable getMyTable() {
        return myTable;
    }

    public void setMyTable(MyTable myTable) {
        this.myTable = myTable;
    }
    
    private void createButtonsContainer(){
        buttonsContainer = new JToolBar("Azioni", JToolBar.HORIZONTAL);
        buttonsContainer.setFloatable(false);
        buttonsContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        allButtons = new ArrayList<FormButton>();
        
        if(standardButtons.contains(StandardButtons.INSERT)){
            
            allButtons.add(new FormButton("Nuovo", new ImageIcon(getClass()
				.getClassLoader().getResource("plus.png")), new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    openInsertDialog();
                }
            }, null, new FormModes[]{FormModes.INSERT_MODE,FormModes.MODIFY_MODE,FormModes.READ_ONLY_MODE}));
            
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
    
    protected void refreshTabPagesInfo(){
        pagesInfo.setText("<html>Pagina <b>" + currPage + "</b> di <b>" + numPages+"</b></html>");
    }
    
    protected void calculateNumPages(int numResults){
        numPages = (numResults / Constants.TAB_NUM_ITEMS_PER_PAGE);
        
        double check = (double)numResults / (double)Constants.TAB_NUM_ITEMS_PER_PAGE;
        
        if(check-(double)numPages > 0)
            numPages ++;
    }
}
