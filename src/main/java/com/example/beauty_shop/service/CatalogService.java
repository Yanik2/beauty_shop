package com.example.beauty_shop.service;

import com.example.beauty_shop.dao.mysql.CatalogDaoImpl;
import com.example.beauty_shop.entity.entities.Account;

import java.util.*;

import static com.example.beauty_shop.constants.Constants.FILTER_BY_MASTER;
import static com.example.beauty_shop.constants.Constants.SORT_BY_NAME;

public class CatalogService {
    private CatalogDaoImpl catalogDao = new CatalogDaoImpl();

    public List<Account> getCatalog(String sortMethod, String filterMethod, String filter) {
        List<Account> catalog = catalogDao.getCatalog();
        filter(catalog, filterMethod, filter);
        sort(catalog, sortMethod);
        return catalog;
    }

    private void sort(List<Account> catalog, String sortMethod) {
        if (sortMethod.equals(SORT_BY_NAME)) {
            sortByName(catalog);
        } else {
            sortByRate(catalog);
        }
    }

    private void filter(List<Account> catalog, String filterMethod, String filter) {
        if (filterMethod == null) return;
        Iterator<Account> it = catalog.iterator();
        if(filterMethod.equals(FILTER_BY_MASTER)) {
            filterByMaster(it, filter);
        } else {
            filterByService(it, filter);
        }
    }

    private void sortByName(List<Account> catalog) {
        Collections.sort(catalog, (catalogItem, t1) -> {
            String name = catalogItem.getLogin();
            String name2 = t1.getLogin();
            return name.compareTo(name2);
        });
    }

    private void sortByRate(List<Account> catalog) {
        Collections.sort(catalog, (catalogItem, t1) -> {
            if (t1.getRate() > t1.getRate()) return 1;
            if (t1.getRate() < t1.getRate()) return -1;
            return 0;
        });
    }

    private void filterByMaster(Iterator<Account> it, String filter) {
        while(it.hasNext()) {
            Account item = it.next();
            if (!(item.getLogin().equals(filter))) {
                it.remove();
            }
        }
    }

    private void filterByService(Iterator<Account> it, String filter) {
        while (it.hasNext()) {
            Account item = it.next();
            if (!(item.getService().getName().equals(filter))) {
                it.remove();
            }
        }
    }
}
