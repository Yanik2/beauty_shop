package com.example.beauty_shop.service.defaultimpl;

import com.example.beauty_shop.dao.mysql.TableDaoImpl;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.CatalogService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.*;

import static com.example.beauty_shop.constants.Constants.FILTER_BY_MASTER;
import static com.example.beauty_shop.constants.Constants.SORT_BY_NAME;

public class CatalogServiceImpl implements CatalogService {
    private final TableDaoImpl catalogDao = new TableDaoImpl();

    public List<Account> getCatalog(String sortMethod, String filterMethod, String filter) throws SQLException {
        List<Account> catalog = catalogDao.getClientTable();
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
        catalog.sort((catalogItem, t1) -> {
            String name = catalogItem.getLogin();
            String name2 = t1.getLogin();
            return name.compareTo(name2);
        });
    }

    private void sortByRate(List<Account> catalog) {
        catalog.sort(Comparator.comparingDouble(Account::getRate));
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
