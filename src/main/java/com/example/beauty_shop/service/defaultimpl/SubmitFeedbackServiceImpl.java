package com.example.beauty_shop.service.defaultimpl;

import com.example.beauty_shop.dao.mysql.AccountDaoImpl;
import com.example.beauty_shop.dao.mysql.FeedbackDaoImpl;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.SubmitFeedbackService;

import java.util.Optional;

public class SubmitFeedbackServiceImpl implements SubmitFeedbackService {
    private final FeedbackDaoImpl feedbackDao = new FeedbackDaoImpl();
    private final AccountDaoImpl accountDao = new AccountDaoImpl();

    @Override
    public boolean submitFeedback(Long masterId, Long clientId, String comment, Double rate) {
        Optional<Account> masterOpt = accountDao.findById(masterId);
        if(masterOpt.isPresent()) {
            Account master = accountDao.findById(masterId).get();
            int feedbackAmount = master.getFeedbackAmount();
            double masterRate = master.getRate();
            Double newRate = (masterRate * feedbackAmount + rate) / (feedbackAmount + 1);
            return feedbackDao.insertFeedback(masterId, clientId, comment, rate, newRate, feedbackAmount + 1);
        }
        return false;
    }
}
