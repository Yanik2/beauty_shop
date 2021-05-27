package com.example.beauty_shop.service.defaultimpl;

import com.example.beauty_shop.dao.mysql.AccountDaoImpl;
import com.example.beauty_shop.dao.mysql.FeedbackDaoImpl;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.SubmitFeedbackService;

public class SubmitFeedbackServiceImpl implements SubmitFeedbackService {
    private FeedbackDaoImpl feedbackDao = new FeedbackDaoImpl();
    private AccountDaoImpl accountDao = new AccountDaoImpl();

    @Override
    public boolean submitFeedback(Long masterId, Long clientId, String comment, Double rate) {
        Account master = accountDao.findById(masterId).orElse(null);
        if (master == null) return false;
        int feedbackAmount = master.getFeedbackAmount();
        double masterRate = master.getRate();
        double newRate = (masterRate * feedbackAmount + rate) / (feedbackAmount + 1);
        return feedbackDao.insertFeedback(masterId, clientId, comment, rate, newRate, feedbackAmount + 1);
    }

    public void setFeedbackDao(FeedbackDaoImpl feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    public void setAccountDao(AccountDaoImpl accountDao) {
        this.accountDao = accountDao;
    }
}
