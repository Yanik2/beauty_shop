package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.service.AppointmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;
import static com.example.beauty_shop.constants.Constants.DATE;

public class ChangeTimeslotCommand implements Command {
    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Long masterId = (Long)(request.getSession().getAttribute(MASTERID));
        Long clientId = (Long)(request.getSession().getAttribute(CLIENTID));
        Long timeslotId = (Long)(request.getSession().getAttribute(TIMESLOTID));
        String date = (String)request.getSession().getAttribute(DATE);
        String newTimeslot = request.getParameter("newTimeslot");

        System.out.println(newTimeslot);

        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, HOMEPAGE + ADMIN_HOME);
        map.put(SHOWLINK, true);
        if(appointmentService.changeTimeslot(masterId, clientId, timeslotId, date, newTimeslot)) {
            map.put(MESSAGE, UPDATE_SUCCESS);
        } else {
            map.put(MESSAGE, MESSAGE_FAILURE);
        }
        return map;
    }
}
