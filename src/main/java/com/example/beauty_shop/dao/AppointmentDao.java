package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.entities.Account;
import com.example.beauty_shop.entity.entities.Appointment;

import java.util.List;
import java.util.Map;

public interface AppointmentDao {
    List<Appointment> getAppointments(String master, String date);
    Map<Long, String> getTimeslots();
    Account getMaster(String name);
}
