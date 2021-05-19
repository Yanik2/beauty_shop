package com.example.beauty_shop.service;

import com.example.beauty_shop.dao.mysql.AccountDaoImpl;
import com.example.beauty_shop.dao.mysql.AppointmentDaoImpl;
import com.example.beauty_shop.entity.entities.Account;
import com.example.beauty_shop.entity.entities.Appointment;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.beauty_shop.constants.Constants.*;

public class AppointmentService {
    private AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();

    public Map<String, Object> getAvailableTime(String master, String date) {
        List<Appointment> appointments = appointmentDao.getAppointments(master, date);
        Map<Long, String> timeslots = appointmentDao.getTimeslots();
        Account masterAccount = appointmentDao.getMaster(master);
        for(Appointment a : appointments) {
            timeslots.remove(a.getTimeslot_id());
        }
        Map<String, Object> map = new HashMap<>();
        map.put(MASTER, masterAccount);
        map.put(TIMESLOTS, timeslots);
        return map;
    }
}
