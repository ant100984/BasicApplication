package it.aesposito.utils;

import it.aesposito.db.SQLMapClientFactory;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.ibatis.session.SqlSession;

public class PdfUtils {
	
	public static byte[] getPdfRicevuta(Integer idVendita, String path, String reportFileName) throws Exception{
		SqlSession sess = null;
		Connection conn = null;
		byte[] bytes = {};
		
		try {
                    sess = SQLMapClientFactory.getSqlMapClient().openSession();

                    HashMap<String, Object> params = new HashMap<String, Object>();
                    params.put("idFattura", idVendita);
                    params.put("path", path);
                    conn = sess.getConnection();

                    JasperPrint jasperPrint = JasperFillManager
                                    .fillReport(new FileInputStream(reportFileName),
                                                    params, conn);

                    bytes = JasperExportManager
                                    .exportReportToPdf(jasperPrint);

		} catch (Exception e) {

                    Utils.logError(PdfUtils.class,e);
                    throw e;
                    
		} finally {
			
                    if(sess != null)
                        sess.close();
			
		}
		
		return bytes;
	}
	
}
