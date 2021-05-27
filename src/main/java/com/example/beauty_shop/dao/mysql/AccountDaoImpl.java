package com.example.beauty_shop.dao.mysql;

import com.example.beauty_shop.dao.AccountDao;
import com.example.beauty_shop.dao.DBManager;
import com.example.beauty_shop.entity.Role;
import com.example.beauty_shop.entity.Account;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Optional;

import static com.example.beauty_shop.constants.Constants.*;
import static com.example.beauty_shop.constants.SQLConstants.SELECT_ACCOUNT_BY_ID;
import static com.example.beauty_shop.constants.SQLConstants.SELECT_ACCOUNT_BY_LOGIN;

public class AccountDaoImpl implements AccountDao {
    private static final Logger logger = LogManager.getLogger();

    public Optional<Account> findByName(String name) {
        Connection con = null;
        Account acc = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement st = con.prepareStatement(SELECT_ACCOUNT_BY_LOGIN);
            st.setString(1, name);
            acc = initAccount(st);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            DBManager.closeConnection(con);
        }
        return Optional.ofNullable(acc);
    }

    public Optional<Account> findById(Long id) {
        Connection con = null;
        Account acc = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement selectStatement = con.prepareStatement(SELECT_ACCOUNT_BY_ID);
            selectStatement.setLong(1, id);
            acc = initAccount(selectStatement);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return Optional.ofNullable(acc);
    }

    private Account initAccount(PreparedStatement st) throws SQLException {
        ResultSet set = st.executeQuery();
        if(set.next()) {
            Account acc = new Account();
            acc.setId(set.getLong(ID));
            acc.setLogin(set.getString(LOGIN));
            acc.setPassword(set.getString(PASSWORD));
            acc.setRate(set.getDouble(RATE));
            String role = set.getString(ROLE);
            acc.setRole(Role.valueOf(role.toUpperCase()));
            acc.setFeedbackAmount(set.getInt("feedback_amount"));
            return acc;
        }
        return null;
    }
}
