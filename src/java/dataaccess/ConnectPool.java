/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;

public class ConnectPool {
    private static ConnectPool pool = null;
    private static DataSource source = null;
    
    private ConnectPool () {
        try {
            InitialContext iContext = new InitialContext ();
            source = (DataSource) iContext.lookup("java:/comp/env/jdbc/userdb");
        } catch (NamingException e) {
            System.out.println (e);
        }
    }
    
    public static synchronized ConnectPool getInstance () {
        if (pool == null) {
            pool = new ConnectPool();
        }
        return pool;
    }
    
    public Connection getConnection() {
        try {
            return source.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public void freeConnection (Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
