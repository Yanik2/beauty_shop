package com.example.beauty_shop.entity;

public class AdminTableItem {
    private Long masterId;
    private Long clientId;
    private Long timeslotId;
    private String date;
    private String masterName;
    private String clientName;
    private String serviceName;
    private String time;
    private Boolean paid;

    public AdminTableItem(Long masterId, Long clientId, Long timeslotId, String date, String masterName, String clientName, String serviceName, String time, Boolean paid) {
        this.masterId = masterId;
        this.clientId = clientId;
        this.timeslotId = timeslotId;
        this.date = date;
        this.masterName = masterName;
        this.clientName = clientName;
        this.serviceName = serviceName;
        this.time = time;
        this.paid = paid;
    }

    public AdminTableItem() {
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(Long timeslotId) {
        this.timeslotId = timeslotId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "{ \n\tMaster: " + masterName + "\n\tClient: " + clientName +
                "\n\tService: " + serviceName + "\n\tTime: " + time +
                "\n\tDate: " + date + "\n\tPaid: " + paid + "\n}";
    }
}
