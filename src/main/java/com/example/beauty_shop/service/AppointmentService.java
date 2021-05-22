package com.example.beauty_shop.service;

import com.example.beauty_shop.dao.mysql.AppointmentDaoImpl;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.Appointment;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.beauty_shop.constants.Constants.*;

public class AppointmentService {
    private final AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();

    public Map<String, Object> getAvailableTime(String master, String date) throws SQLException, NamingException {
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

    public boolean markAsDone(Long masterId, Long timeslotId, String date) throws SQLException, NamingException {
        return appointmentDao.markAsDoneAppointment(masterId, timeslotId, date);
    }

    public boolean updateAppointment(Long masterId, Long clientId, Long timeslotId, String date, String action) throws SQLException, NamingException {
        return appointmentDao.updateAppointment(masterId, clientId, timeslotId, date, action);
    }

    public boolean changeTimeslot(Long masterId, Long clientId, Long timeslotId, String date, String newTimeslot) throws SQLException, NamingException {
        return appointmentDao.changeTimeslot(masterId, clientId, timeslotId, date, newTimeslot);
    }
}
