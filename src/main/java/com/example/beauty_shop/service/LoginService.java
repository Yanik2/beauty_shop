package com.example.beauty_shop.service;

import com.example.beauty_shop.dao.mysql.AccountDaoImpl;
import com.example.beauty_shop.dao.mysql.CatalogDaoImpl;
import com.example.beauty_shop.entity.MasterSlotItem;
import com.example.beauty_shop.entity.Account;

import java.time.LocalDate;
import java.util.*;

import static com.example.beauty_shop.constants.Constants.*;

public class LoginService {
    private final AccountDaoImpl accountDao = new AccountDaoImpl();
    private final CatalogDaoImpl catalogDao = new CatalogDaoImpl();

    public Map<String, Object> login(String login, String password) {
        Optional<Account> account = accountDao.findByName(login);
        account = account.filter(account1 -> password.equals(account1.getPassword()));
        Map<String, Object> map = new HashMap<>();
        map.put(USER, account);
        if(account.isPresent()) {
            Account user = account.get();
            switch(user.getRole()) {
                case CLIENT:
                    map.put(CATALOG, catalogDao.getClientCatalog());
                    break;
                case MASTER:
                    List<MasterSlotItem> masterCatalog = HomepageService.makeMasterSlots(catalogDao.getMasterCatalog(user, LocalDate.now().toString()));
                    map.put(CATALOG, masterCatalog);
                    break;
                case ADMIN:
                    break;
            }
        }
        return map;
    }


}
