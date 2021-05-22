package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.Appointment;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AppointmentDao {
    List<Appointment> getAppointments(String master, String date) throws SQLException, NamingException;
    Map<Long, String> getTimeslots() throws SQLException, NamingException;
    Account getMaster(String name) throws SQLException, NamingException;
    boolean markAsDoneAppointment(Long masterId, Long timeslotId, String date) throws SQLException, NamingException;
    boolean updateAppointment(Long masterId, Long clientId, Long timeslotId, String date, String action) throws SQLException, NamingException;
    boolean changeTimeslot(Long masterId, Long clientId, Long timeslotId, String date, String newTimeslot) throws SQLException, NamingException;

}
