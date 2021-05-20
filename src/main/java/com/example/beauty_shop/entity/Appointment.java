package com.example.beauty_shop.entity;

public class Appointment {
    private Long master_id;
    private Long client_id;
    private Long service_id;
    private Boolean paid = false;
    private Boolean done = false;
    private Long timeslot_id;

    public Long getTimeslot_id() {
        return timeslot_id;
    }

    public void setTimeslot_id(Long timeslot_id) {
        this.timeslot_id = timeslot_id;
    }

    private String date;

    public Appointment(Long master_id, Long client_id, Long service_id, Boolean paid, Boolean done, Long timeslot_id, String date) {
        this.master_id = master_id;
        this.client_id = client_id;
        this.service_id = service_id;
        this.paid = paid;
        this.done = done;
        this.timeslot_id = timeslot_id;
        this.date = date;
    }

    public Appointment() {
    }

    public Long getMaster_id() {
        return master_id;
    }

    public void setMaster_id(Long master_id) {
        this.master_id = master_id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getService_id() {
        return service_id;
    }

    public void setService_id(Long service_id) {
        this.service_id = service_id;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{ Master Id: " + master_id +
                "\nClient Id: " + client_id +
                "\nService Id: " + service_id +
                "\nPaid: " + paid +
                "\nDone: " + done +
                "\nTimeslot Id: " + timeslot_id + " }";
    }
}
