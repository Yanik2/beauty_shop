package com.example.beauty_shop.service;

import com.example.beauty_shop.dao.mysql.TableDaoImpl;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.AdminTableItem;
import com.example.beauty_shop.entity.Appointment;
import com.example.beauty_shop.entity.MasterSlotItem;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.beauty_shop.constants.Constants.APPOINTMENT;
import static com.example.beauty_shop.constants.Constants.CATALOG;

public class HomepageService {
    private final TableDaoImpl tableDao = new TableDaoImpl();

    public Map<String, Object> getPageFill(Account currentUser) throws SQLException, NamingException {
        Map<String, Object> map = new HashMap<>();
        switch (currentUser.getRole()) {
            case CLIENT:
                map.put(CATALOG, tableDao.getClientTable());
                break;
            case MASTER:
                List<MasterSlotItem> masterCatalog = makeMasterSlots(tableDao.getMasterTable(currentUser, LocalDate.now().toString()));
                map.put(CATALOG, masterCatalog);
                break;
            case ADMIN:
                map.put(CATALOG, tableDao.getAdminTable());
                break;
        }
        return map;
    }

    public static List<MasterSlotItem> makeMasterSlots(Map<String, List> map) {
        List<Appointment> apps = map.get(APPOINTMENT);
        List<MasterSlotItem> catalog = map.get(CATALOG);
        for(Appointment ap : apps) {
            Long id = ap.getTimeslot_id();
            Boolean done = ap.getDone();
            MasterSlotItem item = catalog.get(Math.toIntExact(id - 1));
            item.setAvailability(false);
            item.setDone(done);
        }
        return catalog;
    }

    public List<AdminTableItem> filterByDate(String date) throws SQLException, NamingException {
        List<AdminTableItem> adminTable = tableDao.getAdminTable();
        return adminTable.stream().filter(item -> item.getDate().equals(date)).collect(Collectors.toList());
    }
}
