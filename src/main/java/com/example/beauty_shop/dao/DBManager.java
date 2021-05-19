package com.example.beauty_shop.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static DataSource ds;

    private DBManager() {

    }

    public static Connection getConnection() throws SQLException {
        if(ds == null) {
            try {
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("java:comp/env/jdbc/yan");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return ds.getConnection();
    }

    public static void closeConnection(Connection con) {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
