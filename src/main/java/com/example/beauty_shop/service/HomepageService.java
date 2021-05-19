package com.example.beauty_shop.service;

import com.example.beauty_shop.dao.mysql.CatalogDaoImpl;
import com.example.beauty_shop.entity.entities.Account;

import java.util.List;

public class HomepageService {
    private CatalogDaoImpl catalogDao = new CatalogDaoImpl();
    public List<Account> getPageFill(Account user) {
        switch (user.getRole()) {
            case CLIENT:
                return catalogDao.getCatalog();
            case MASTER:
                break;
            case ADMIN:
                break;
        }
        return null;
    }
}
