/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SQLMapClientFactory {
	private static SqlSessionFactory sqlSessionFactory = null;

	public static SqlSessionFactory getSqlMapClient() throws IOException {
		
		if(sqlSessionFactory == null){
			String resource = "it/aesposito/conf/SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}
		return sqlSessionFactory;
	}
	
}
