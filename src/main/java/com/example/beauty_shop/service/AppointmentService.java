package com.example.beauty_shop.service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Map;

public interface AppointmentService {
    Map<String, Object> getAvailableTime(String master, String date) throws SQLException;
    boolean markAsDone(Long masterId, Long timeslotId, String date) throws SQLException;
    boolean updateAppointment(Long masterId, Long clientId, Long timeslotId, String date, String action) throws SQLException;
    boolean changeTimeslot(Long masterId, Long clientId, Long timeslotId, String date, String newTimeslot) throws SQLException, NamingException;
}
