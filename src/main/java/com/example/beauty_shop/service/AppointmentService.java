package com.example.beauty_shop.service;

import com.example.beauty_shop.dao.mysql.AppointmentDaoImpl;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.Appointment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.beauty_shop.constants.Constants.*;

public class AppointmentService {
    private final AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();

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

    public boolean markAsDone(Long masterId, Long timeslotId, String date) {
        return appointmentDao.markAsDoneAppointment(masterId, timeslotId, date);
    }

    public boolean updateAppointment(Long masterId, Long clientId, Long timeslotId, String date, String action) {
        return appointmentDao.updateAppointment(masterId, clientId, timeslotId, date, action);
    }

    public boolean changeTimeslot(Long masterId, Long clientId, Long timeslotId, String date, String newTimeslot) {
        return appointmentDao.changeTimeslot(masterId, clientId, timeslotId, date, newTimeslot);
    }
}
