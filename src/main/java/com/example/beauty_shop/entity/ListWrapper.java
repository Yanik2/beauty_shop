package com.example.beauty_shop.entity;

import java.util.List;

public class ListWrapper {
    private List<Appointment> appointments;
    private List<MasterSlotItem> masterSlots;

    public ListWrapper() {
    }

    public ListWrapper(List<Appointment> appointments, List<MasterSlotItem> masterSlots) {
        this.appointments = appointments;
        this.masterSlots = masterSlots;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<MasterSlotItem> getMasterSlots() {
        return masterSlots;
    }

    public void setMasterSlots(List<MasterSlotItem> masterSlots) {
        this.masterSlots = masterSlots;
    }
}
