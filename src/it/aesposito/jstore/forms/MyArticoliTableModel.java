/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.jstore.bean.ArticoloForView;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Antonio
 */
public abstract class MyArticoliTableModel extends AbstractTableModel {
    protected List<ArticoloForView> rows;

    public List<ArticoloForView> getRows() {
        return rows;
    }

    public void setRows(List<ArticoloForView> rows) {
        this.rows = rows;
    }
    
}
