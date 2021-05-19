package com.example.beauty_shop.dao.mysql;

import com.example.beauty_shop.dao.AccountDao;
import com.example.beauty_shop.dao.DBManager;
import com.example.beauty_shop.entity.Role;
import com.example.beauty_shop.entity.entities.Account;

import java.sql.*;
import java.util.Optional;

import static com.example.beauty_shop.constants.Constants.*;

public class AccountDaoImpl implements AccountDao {
    public Optional<Account> findByName(String name) {
        String sql = "SELECT * FROM account WHERE login = ?";
        Connection con;
        Account acc = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
//            ResultSet set = st.executeQuery("SELECT * FROM account WHERE login = '" + name + "';");
            ResultSet set = st.executeQuery();
            if(set.next()) {
                acc = new Account();
                acc.setId(set.getLong(ID));
                acc.setLogin(set.getString(LOGIN));
                acc.setPassword(set.getString(PASSWORD));
                acc.setRate(set.getDouble(RATE));
                String role = set.getString(ROLE);
                acc.setRole(Role.valueOf(role.toUpperCase()));
//                acc.setService(set.getInt("service"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(acc);
    }
}
