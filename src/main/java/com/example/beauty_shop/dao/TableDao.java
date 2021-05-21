package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.AdminTableItem;

import java.util.List;
import java.util.Map;

public interface TableDao {
    List<Account> getClientTable();
    Map<String, List> getMasterTable(Account account, String date);
    List<AdminTableItem> getAdminTable();
}
