package com.example.beauty_shop.service.defaultimpl;

import com.example.beauty_shop.dao.mysql.FeedbackDaoImpl;
import com.example.beauty_shop.entity.Feedback;
import com.example.beauty_shop.service.FeedbackService;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackDaoImpl feedbackDao = new FeedbackDaoImpl();

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackDao.findAll();
    }

    public void setFeedbackDao(FeedbackDaoImpl feedbackDao) {
        this.feedbackDao = feedbackDao;
    }
}
