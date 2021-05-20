package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.Account;

import java.util.List;
import java.util.Map;

public interface CatalogDao {
    List<Account> getClientCatalog();
    Map<String, List> getMasterCatalog(Account account, String date);
}
