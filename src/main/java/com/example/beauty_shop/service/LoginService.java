package com.example.beauty_shop.service;

import com.example.beauty_shop.dao.mysql.AccountDaoImpl;
import com.example.beauty_shop.dao.mysql.CatalogDaoImpl;
import com.example.beauty_shop.entity.entities.Account;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.beauty_shop.constants.Constants.CATALOG;
import static com.example.beauty_shop.constants.Constants.USER;

public class LoginService {
    private AccountDaoImpl accountDao = new AccountDaoImpl();
    private CatalogDaoImpl catalogDao = new CatalogDaoImpl();

    public Map<String, Object> login(String login, String password) {
        Optional<Account> account = accountDao.findByName(login);
        account = account.filter(account1 -> password.equals(account1.getPassword()));
        Map<String, Object> map = new HashMap<>();
        map.put(USER, account);
        if(account.isPresent()) {
            Account user = account.get();
            switch(user.getRole()) {
                case CLIENT:
                    map.put(CATALOG, catalogDao.getCatalog());
                    break;
                case MASTER:
                    break;
                case ADMIN:
                    break;
            }
        }
        return map;
    }
}
