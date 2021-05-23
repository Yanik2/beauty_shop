package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.Feedback;

import java.util.List;

public interface FeedbackDao {
    List<Feedback> findAll();
    boolean insertFeedback(Long masterId, Long clientId, String comment, Double clientRate, Double rate, Integer feedbackAmount);
}
