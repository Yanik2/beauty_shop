package com.example.beauty_shop.service.defaultimpl;

import com.example.beauty_shop.dao.mysql.AccountDaoImpl;
import com.example.beauty_shop.dao.mysql.TableDaoImpl;
import com.example.beauty_shop.entity.MasterSlotItem;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.LoginService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static com.example.beauty_shop.constants.Constants.*;

public class LoginServiceImpl implements LoginService {
    private AccountDaoImpl accountDao = new AccountDaoImpl();
    private TableDaoImpl tableDao = new TableDaoImpl();

    public Map<String, Object> login(String login, String password) throws SQLException, NamingException {
        Optional<Account> account = accountDao.findByName(login);
        account = account.filter(account1 -> password.equals(account1.getPassword()));
        Map<String, Object> map = new HashMap<>();
        map.put(USER, account);
        Account user = account.orElse(null);
        if(user != null) {
            switch(user.getRole()) {
                case CLIENT:
                    map.put(CATALOG, tableDao.getClientTable());
                    break;
                case MASTER:
                    List<MasterSlotItem> masterTable = HomepageServiceImpl.makeMasterSlots(tableDao.getMasterTable(user, LocalDate.now().toString()));
                    map.put(CATALOG, masterTable);
                    break;
                case ADMIN:
                    map.put(CATALOG, tableDao.getAdminTable());
                    break;
            }
        }
        return map;
    }

    public void setAccountDao(AccountDaoImpl accountDao) {
        this.accountDao = accountDao;
    }

    public void setTableDao(TableDaoImpl tableDao) {
        this.tableDao = tableDao;
    }
}
