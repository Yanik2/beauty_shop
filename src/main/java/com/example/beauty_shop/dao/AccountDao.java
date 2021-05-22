package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.Account;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Optional;

public interface AccountDao {
    Optional<Account> findByName(String name) throws SQLException, NamingException;
}
