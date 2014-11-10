/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.jstore.forms;

import it.aesposito.db.SQLMapClientFactory;
import it.aesposito.forms.MainApplicationForm;
import it.aesposito.forms.MyButtonTableColumn;
import it.aesposito.forms.MyTableColumn;
import it.aesposito.jstore.bean.ApplicationProperties;
import it.aesposito.jstore.bean.ApplicationPropertiesExample;
import it.aesposito.jstore.bean.ApplicationPropertiesWithBLOBs;
import it.aesposito.jstore.bean.Categorie;
import it.aesposito.jstore.bean.Listino;
import it.aesposito.jstore.bean.ListinoForView;
import it.aesposito.jstore.dao.ApplicationPropertiesMapper;
import it.aesposito.jstore.dao.IvaMapper;
import it.aesposito.jstore.dao.ListinoMapper;
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
public class ListiniTableModel extends MyTableModel{
    private List<MyTableColumn> columns;
    private List<ListinoForView> rows;
    private MainApplicationForm parent_frame;
    
    public ListiniTableModel(List<ListinoForView> rows, MainApplicationForm parent_frame){
        this.parent_frame = parent_frame;
        this.columns = new ArrayList<MyTableColumn>();
        
        columns.add(new MyTableColumn("Tipologia", String.class, "descrTipologia",100));
        columns.add(new MyTableColumn("Descrizione", String.class, "descrizione",100));
        columns.add(new MyTableColumn("Ricarico", String.class, "ricarico",""," %",100));
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
            case 0:
            case 1:
            case 2: return getHumanReadable(rows.get(rowIndex),columns.get(columnIndex));
            case 3: JButton button = new JButton("",new ImageIcon(getClass()
				.getClassLoader().getResource("detail_small.png")));
                    button.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ((MainApplicationForm)parent_frame).openListinoForm(rows.get(rowIndex).getId(),rows.get(rowIndex).getTipologiaListino());
                        }
                    });
                    
                    return button;
            case 4: JButton button1 = new JButton("",new ImageIcon(getClass()
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
                            
                            ListinoMapper im = session.getMapper(ListinoMapper.class);
                            ApplicationPropertiesMapper apm = session.getMapper(ApplicationPropertiesMapper.class);
                            
                            ApplicationPropertiesExample ape = new ApplicationPropertiesExample();
                            ape.createCriteria().andListinoPrestazioniEqualTo(rows.get(rowIndex).getId());
                            ApplicationPropertiesWithBLOBs ap = new ApplicationPropertiesWithBLOBs();
                            ap.setListinoPrestazioni(-1);
                            apm.updateByExampleSelective(ap, ape);
                            
                            ape = new ApplicationPropertiesExample();
                            ape.createCriteria().andListinoProdottiEqualTo(rows.get(rowIndex).getId());
                            ap = new ApplicationPropertiesWithBLOBs();
                            ap.setListinoProdotti(-1);
                            apm.updateByExampleSelective(ap, ape);
                            
                            im.deleteByPrimaryKey(rows.get(rowIndex).getId());
                            rows.remove(rowIndex);
                            session.commit();
                            session.close();    

                        }
                    });
                    
                    return button1;
            case 5: return rows.get(rowIndex);
        }
        
        return "";
    }
    
}
