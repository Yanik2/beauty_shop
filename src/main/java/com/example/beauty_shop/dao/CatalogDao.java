package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.entities.Account;

import java.util.List;

public interface CatalogDao {
    List<Account> getCatalog();
}
