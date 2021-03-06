package com.example.beauty_shop.entity;

public class Account {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private Service service;
    private Double rate;
    private Integer feedbackAmount;

    public Account(Long id, String login, String password, Role role, Service service, double rate, int feedbackAmount) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.service = service;
        this.rate = rate;
        this.feedbackAmount = feedbackAmount;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getFeedbackAmount() {
        return feedbackAmount;
    }

    public void setFeedbackAmount(int feedbackAmount) {
        this.feedbackAmount = feedbackAmount;
    }

    @Override
    public String toString() {
        return "{ login : " + login + " Role : " + role + " }";
    }
}
