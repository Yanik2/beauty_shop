package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.defaultimpl.SubmitFeedbackServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class SubmitFeedbackCommand implements Command {
    private final SubmitFeedbackServiceImpl submitFeedbackService = new SubmitFeedbackServiceImpl();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        Long masterId = (Long)request.getSession().getAttribute(MASTERID);
        Account currentUser = (Account)request.getSession().getAttribute(USER);
        String comment = request.getParameter(COMMENT);
        Double rate = Double.parseDouble(request.getParameter(RATE));
        request.getSession().removeAttribute(MASTERID);
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, HOMEPAGE + CLIENT_HOME);
        map.put(HIDEDATE, true);
        if(submitFeedbackService.submitFeedback(masterId, currentUser.getId(), comment, rate)) {
            map.put(MESSAGE, MESSAGE_SUCCESS);
        } else {
            map.put(MESSAGE, MESSAGE_FAILURE);
        }
        return map;
    }
}
