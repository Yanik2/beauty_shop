package com.example.beauty_shop.entity;

public class Feedback {
    private String masterName;
    private String clientName;
    private Double rate;
    private String comment;

    public Feedback(String masterName, String clientName, Double rate, String comment) {
        this.masterName = masterName;
        this.clientName = clientName;
        this.rate = rate;
        this.comment = comment;
    }

    public Feedback() {
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
