package it.aesposito.utils;

import it.aesposito.db.SQLMapClientFactory;
import it.aesposito.jstore.bean.ApplicationProperties;
import it.aesposito.jstore.bean.ApplicationPropertiesExample;
import it.aesposito.jstore.dao.ApplicationPropertiesMapper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JOptionPane;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class Utils {
	
	public static Integer getNextVal(String table) throws IOException, SQLException{
            SqlSession session = SQLMapClientFactory.getSqlMapClient().openSession();
            Map<String, Object> par = new HashMap<String, Object>();
            par.put("table", table);
            return session.selectOne("custom_nextVal.dynamicNextVal",par);
	}
	
	public static double roundTwoDec(double value){
            return Math.floor(value * 100) / 100;
	}
	
	public static void saveBytesAsFile(byte[] content, String path, String fileName) throws IOException{
            File dir = new File(path);
            if(!dir.exists()) dir.mkdir();
            FileOutputStream fos = new FileOutputStream(path + File.separator + fileName);
            fos.write(content);
            fos.close();
	}
	
	public static Integer nzInt(Integer num){
		return (num==null || num == 0) ? null : num;
	}
	
	public static void compressPath(String path, String archiveName, String destPath) throws IOException{
		FileOutputStream dest = new FileOutputStream(destPath + File.separator + archiveName);
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
		File dir = new File(path);
		
		File[] list = dir.listFiles();
		
		for (File file : list) {
			
                    FileInputStream fi = new FileInputStream(file);
                    ZipEntry entry = new ZipEntry(file.getName());
                    out.putNextEntry(entry);

                    int count;
                    byte[] data = new byte[8192];
                    while((count = fi.read(data, 0, 8192)) != -1) {
                       out.write(data, 0, count);
                    }

                    fi.close();
			
		}
		
		out.close();
	}
	
        public static String capitalizeFirstLetter(String input){
            return input.substring(0,1).toUpperCase() + input.substring(1);
        }
        
        public static void logError(Class c, Exception e){
            Logger.getLogger(c).error("",e.fillInStackTrace());
        }
        
        public static void showErrorAlert(String message){
            JOptionPane.showMessageDialog(null, message, "Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        public static void showInfoAlert(String message){
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        public static void showWarningAlert(String message){
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.WARNING_MESSAGE);
        }
        
        public static int showConfirm(String message){
           return JOptionPane.showConfirmDialog(null, message, "Conferma",JOptionPane.YES_NO_OPTION);
        }
        
        public static ApplicationProperties getPreferences() throws IOException{
            SqlSession session = SQLMapClientFactory.getSqlMapClient().openSession();
            
            ApplicationPropertiesMapper apm = session.getMapper(ApplicationPropertiesMapper.class);
            ApplicationProperties ap = apm.selectByExample(new ApplicationPropertiesExample()).get(0);
            session.close();
            return ap;
        }
        
        public static boolean checkArticoloSottoScorta() throws IOException{
            SqlSession session = SQLMapClientFactory.getSqlMapClient().openSession();
            
            Integer num = (Integer)session.selectOne("custom_Articoli.checkArticoloSottoScorta");
            
            return num > 0;
        }
}