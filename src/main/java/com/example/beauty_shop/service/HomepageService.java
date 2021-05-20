package com.example.beauty_shop.service;

import com.example.beauty_shop.dao.mysql.CatalogDaoImpl;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.Appointment;
import com.example.beauty_shop.entity.MasterSlotItem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.APPOINTMENT;
import static com.example.beauty_shop.constants.Constants.CATALOG;

public class HomepageService {
    private final CatalogDaoImpl catalogDao = new CatalogDaoImpl();

    public Map<String, Object> getPageFill(Account currentUser) {
        Map<String, Object> map = new HashMap<>();
        switch (currentUser.getRole()) {
            case CLIENT:
                map.put(CATALOG, catalogDao.getClientCatalog());
                break;
            case MASTER:
                List<MasterSlotItem> masterCatalog = makeMasterSlots(catalogDao.getMasterCatalog(currentUser, LocalDate.now().toString()));
                map.put(CATALOG, masterCatalog);
                break;
            case ADMIN:
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
}
