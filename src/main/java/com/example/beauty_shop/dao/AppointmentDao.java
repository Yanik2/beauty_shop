package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.Appointment;

import java.util.List;
import java.util.Map;

public interface AppointmentDao {
    List<Appointment> getAppointments(String master, String date);
    Map<Long, String> getTimeslots();
    Account getMaster(String name);
    boolean markAsDoneAppointment(Long masterId, Long timeslotId, String date);
    boolean updateAppointment(Long masterId, Long clientId, Long timeslotId, String date, String action);
    boolean changeTimeslot(Long masterId, Long clientId, Long timeslotId, String date, String newTimeslot);

}
