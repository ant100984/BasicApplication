/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.db;

import it.aesposito.application.ReturnContainer;
import static it.aesposito.db.DBCreationQueries.CREATE_COMUNI_TABLE;
import it.aesposito.errors.ReturnCodes;
import it.aesposito.utils.Utils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Antonio
 */
public class DB implements DBCreationQueries{
    final static String url = "jdbc:derby:DB";
    
    final static String dbUser = "root";
    final static String dbPwd = "root";
    
    public static void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        loadSqlMapClient();
    }
    
    public static Connection getConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(url, dbUser, dbPwd);
        return conn;
    }
    
    public static ReturnContainer createDatabase() {
        ReturnContainer ret = new ReturnContainer();
        Connection dbConnection = null;
        Statement statement = null;
        
        try{
        
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            statement.execute(TEST_CONNECTION_QUERY);
            
            ret.setReturn_code(ReturnCodes.DB_ALREADY_EXISTS);
            ret.setReturn_object(false);
                       
        }catch (SQLException ex) {
        
            try {

                dbConnection = DriverManager.getConnection(url + ";create=true", dbUser, dbPwd);
                statement = dbConnection.createStatement();

            } catch (SQLException subEx) {
                Utils.logError(DB.class, subEx);
                ret.setReturn_code(ReturnCodes.DB_CONNECT_ERROR);
                ret.setReturn_object(false);
                return ret;
            }

            try{
                statement.execute(CREATE_COMUNI_TABLE);
                statement.execute(CREATE_RITIRI_TABLE);
                statement.execute(CREATE_APP_PROPS_TABLE);
            }catch (SQLException subEx) {
                Utils.logError(DB.class, subEx);
                ret.setReturn_code(ReturnCodes.DB_CREATION_ERROR);
                ret.setReturn_object(false);
                return ret;
            }
        
            ret.setReturn_code(ReturnCodes.OK);
            ret.setReturn_object(true);
        }
        
        return ret;
    }
    
    public static void loadSqlMapClient() {
        System.out.println("Reading SQLMAP settings....");

        try{
            SQLMapClientFactory.getSqlMapClient();
        }catch(IOException e){
            System.out.println("Reading SQLMAP settings error!");
            Utils.logError(DB.class, e);
            return;
        }

        System.out.println("Reading SQLMAP settings complete");

    }
}