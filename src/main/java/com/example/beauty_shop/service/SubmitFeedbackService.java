package com.example.beauty_shop.service;

public interface SubmitFeedbackService {
    boolean submitFeedback(Long masterId, Long clientId, String comment, Double rate);
}
