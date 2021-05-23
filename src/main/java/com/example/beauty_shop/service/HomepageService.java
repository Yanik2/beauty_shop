package com.example.beauty_shop.service;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.AdminTableItem;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface HomepageService {
    Map<String, Object> getPageFill(Account currentUser) throws SQLException, NamingException;
    List<AdminTableItem> filterByDate(String date) throws SQLException, NamingException;
}
