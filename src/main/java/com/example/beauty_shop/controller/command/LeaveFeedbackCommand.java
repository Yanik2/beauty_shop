package com.example.beauty_shop.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class LeaveFeedbackCommand implements Command {

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Long masterId = Long.parseLong(request.getParameter(MASTERID));
        request.getSession().setAttribute(MASTERID, masterId);
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, HOMEPAGE + CLIENT_FEEDBACK_JSP);
        return map;
    }
}
