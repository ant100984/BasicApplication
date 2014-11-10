/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Antonio
 */
public class MyTable extends JTable{
    protected List<Integer> mySelectedRows;
    protected MainApplicationForm parent_frame;
    
    public MyTable(MainApplicationForm parent_frame, TableModel tModel){
        this.parent_frame = parent_frame;
        this.setModel(tModel);
        mySelectedRows = new ArrayList<Integer>();
    }
    
    public MyTable(MainApplicationForm parent_frame){
        this.parent_frame = parent_frame;
        mySelectedRows = new ArrayList<Integer>();
    }
    
    public void addAllSelection(){
        mySelectedRows.clear();
        int[] selection = this.getSelectedRows();
        for (int sel:selection) {
            mySelectedRows.add(sel);
        }
    }
    
    public void setSelection(Integer sel){
        mySelectedRows.clear();
        mySelectedRows.add(sel);
    }
    
    public void drawSelection(){
        for (Integer rowIndex : mySelectedRows) {
            this.setRowSelectionInterval(rowIndex, rowIndex);
        }
    }
    
    public void resetSelection(){
        mySelectedRows = new ArrayList<Integer>();
    }

    public List<Integer> getMySelectedRows() {
        return mySelectedRows;
    }

    public void setMySelectedRows(List<Integer> mySelectedRows) {
        this.mySelectedRows = mySelectedRows;
    }
    
}
