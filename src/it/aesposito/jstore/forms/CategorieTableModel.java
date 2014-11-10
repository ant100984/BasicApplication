/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.db.SQLMapClientFactory;
import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.MyButtonTableColumn;
import it.aesposito.forms.MyTableColumn;
import it.aesposito.jstore.bean.ArticoliExample;
import it.aesposito.jstore.bean.Categorie;
import it.aesposito.jstore.bean.CategorieExample;
import it.aesposito.jstore.dao.ArticoliMapper;
import it.aesposito.jstore.dao.CategorieMapper;
import it.aesposito.utils.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Antonio
 */
public class CategorieTableModel extends MyTableModel{
    private List<MyTableColumn> columns;
    private List<Categorie> rows;
    private MainApplicationForm parent_frame;
    private Integer tipo;
    
    public CategorieTableModel(List<Categorie> rows, MainApplicationForm parent_frame,Integer tipo){
        this.parent_frame = parent_frame;
        this.columns = new ArrayList<MyTableColumn>();
        this.tipo = tipo;
        
        columns.add(new MyTableColumn("Categoria", String.class, "descrizione",100));
        columns.add(new MyButtonTableColumn("", JButton.class,15));
        columns.add(new MyButtonTableColumn("", JButton.class,15));
        columns.add(new MyTableColumn("", String.class, "",0));
        
        this.rows = rows;
    }
    
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return columns.get(columnIndex).getName();
    }
    
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return getHumanReadable(rows.get(rowIndex),columns.get(columnIndex));
            case 1: JButton button = new JButton("",new ImageIcon(getClass()
				.getClassLoader().getResource("detail_small.png")));
                    button.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ((MainApplicationForm)parent_frame).openCategoriaForm(rows.get(rowIndex).getId(),tipo);
                        }
                    });
                    
                    return button;
            case 2: JButton button1 = new JButton("",new ImageIcon(getClass()
				.getClassLoader().getResource("trash_black.png")));
                    button1.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
                            if(Utils.showConfirm("Sei sicuro?") == JOptionPane.NO_OPTION ) return;
                            
                            SqlSession session = null;
                            try {
                                session = SQLMapClientFactory.getSqlMapClient().openSession();
                            } catch (IOException ex) {
                                Logger.getLogger(CategorieTableModel.class.getName()).log(Level.SEVERE, null, ex);
                                return;
                            }
                            
                            CategorieMapper cm = session.getMapper(CategorieMapper.class);
                            ArticoliMapper am = session.getMapper(ArticoliMapper.class);
                            ArticoliExample ae = new ArticoliExample();
                            ae.createCriteria().andCategoriaEqualTo(rows.get(rowIndex).getId());
                            
                            if(am.countByExample(ae) > 0)
                                Utils.showWarningAlert("Impossibile procedere! Non si può eliminare una categoria in uso");
                            else{
                                cm.deleteByPrimaryKey(rows.get(rowIndex).getId());
                                rows.remove(rowIndex);
                                session.commit();
                            }
                            session.close();    

                        }
                    });
                    
                    return button1;
            case 3: return rows.get(rowIndex);
        }
        
        return "";
    }
    
}
