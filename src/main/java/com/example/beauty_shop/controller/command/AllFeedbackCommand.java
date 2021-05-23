package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.Feedback;
import com.example.beauty_shop.service.defaultimpl.FeedbackServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class AllFeedbackCommand implements Command {
    private final FeedbackServiceImpl feedbackService = new FeedbackServiceImpl();
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, FEEDBACK_JSP);
        map.put(FEEDBACKS, feedbacks);
        if(feedbacks.size() == 0) {
            map.put(MESSAGE, NO_FEEDBACKS);
        }
        return map;
    }
}
