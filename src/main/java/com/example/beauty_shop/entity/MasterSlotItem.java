package com.example.beauty_shop.entity;

public class MasterSlotItem {
    private Long timeslot_id;
    private String timeslot;
    private Boolean done;
    private Boolean availability;

    public MasterSlotItem() {
    }

    public MasterSlotItem(Long timeslot_id, String timeslot, Boolean done, Boolean availability) {
        this.timeslot_id = timeslot_id;
        this.timeslot = timeslot;
        this.done = done;
        this.availability = availability;
    }


    public Long getTimeslot_id() {
        return timeslot_id;
    }

    public void setTimeslot_id(Long timeslot_id) {
        this.timeslot_id = timeslot_id;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return timeslot + " " + availability + " " + done;
    }
}
