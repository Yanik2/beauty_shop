package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.AdminTableItem;
import com.example.beauty_shop.entity.ListWrapper;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface TableDao {
    List<Account> getClientTable() throws SQLException, NamingException;
    ListWrapper getMasterTable(Account account, String date) throws SQLException, NamingException;
    List<AdminTableItem> getAdminTable() throws SQLException, NamingException;
}
