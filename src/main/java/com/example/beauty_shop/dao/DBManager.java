package com.example.beauty_shop.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static final Logger logger = LogManager.getLogger();

    private static DataSource ds;

    private DBManager() {

    }

    public static Connection getConnection() throws SQLException {
        if(ds == null) {
            try {
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("java:comp/env/jdbc/yan");
            } catch (NamingException e) {
                logger.log(Level.ERROR, "DBManager: ", e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return ds.getConnection();
    }

    public static void closeConnection(Connection con) {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "CloseConnection: ", e);
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

}
