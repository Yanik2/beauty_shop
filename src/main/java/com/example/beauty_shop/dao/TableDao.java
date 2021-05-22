package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.AdminTableItem;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TableDao {
    List<Account> getClientTable() throws SQLException, NamingException;
    Map<String, List> getMasterTable(Account account, String date) throws SQLException, NamingException;
    List<AdminTableItem> getAdminTable() throws SQLException, NamingException;
}
