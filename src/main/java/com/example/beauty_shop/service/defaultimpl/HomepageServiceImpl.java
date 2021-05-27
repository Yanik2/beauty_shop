package com.example.beauty_shop.service.defaultimpl;

import com.example.beauty_shop.dao.mysql.TableDaoImpl;
import com.example.beauty_shop.entity.*;
import com.example.beauty_shop.service.HomepageService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.beauty_shop.constants.Constants.CATALOG;

public class HomepageServiceImpl implements HomepageService {
    private TableDaoImpl tableDao = new TableDaoImpl();

    public Map<String, Object> getPageFill(Account currentUser) throws SQLException {
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

    public static List<MasterSlotItem> makeMasterSlots(ListWrapper wrapper) {
        List<Appointment> apps = wrapper.getAppointments();
        List<MasterSlotItem> catalog = wrapper.getMasterSlots();
        for(Appointment ap : apps) {
            Long id = ap.getTimeslot_id();
            Boolean done = ap.getDone();
            MasterSlotItem item = catalog.get(Math.toIntExact(id - 1));
            item.setAvailability(false);
            item.setDone(done);
        }
        return catalog;
    }

    public List<AdminTableItem> filterByDate(String date) {
        List<AdminTableItem> adminTable = tableDao.getAdminTable();
        return adminTable.stream().filter(item -> item.getDate().equals(date)).collect(Collectors.toList());
    }

    public void setTableDao(TableDaoImpl tableDao) {
        this.tableDao = tableDao;
    }
}
