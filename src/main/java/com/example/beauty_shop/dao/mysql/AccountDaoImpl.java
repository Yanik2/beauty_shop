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

public class AccountDaoImpl implements AccountDao {
    private static final Logger logger = LogManager.getLogger();

    public Optional<Account> findByName(String name) throws SQLException {
        String selectAccount = "SELECT * FROM account WHERE login = ?";
        Connection con = null;
        Account acc = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement st = con.prepareStatement(selectAccount);
            st.setString(1, name);
            ResultSet set = st.executeQuery();
            if(set.next()) {
                acc = new Account();
                acc.setId(set.getLong(ID));
                acc.setLogin(set.getString(LOGIN));
                acc.setPassword(set.getString(PASSWORD));
                acc.setRate(set.getDouble(RATE));
                String role = set.getString(ROLE);
                acc.setRole(Role.valueOf(role.toUpperCase()));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "AccountDao: ", e);
            throw e;
        } finally {
            DBManager.closeConnection(con);
        }
        return Optional.ofNullable(acc);
    }
}
