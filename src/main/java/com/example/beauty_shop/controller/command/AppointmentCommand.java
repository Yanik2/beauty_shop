package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.entities.Account;
import com.example.beauty_shop.service.AppointmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.example.beauty_shop.constants.Constants.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AppointmentCommand implements Command {
    private AppointmentService appointmentService = new AppointmentService();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        String master = request.getParameter(MASTER);
        String date = request.getParameter(APPOINTMENT_DATE);
        Map<String, Object> mapFromService = appointmentService.getAvailableTime(master, date);

        Account masterAcc = (Account)mapFromService.get(MASTER);

//        System.out.println(acc);

        Map<Long, String> mapTimeslots = (Map<Long, String>) mapFromService.get(TIMESLOTS);
        Collection<String> timeslots =  mapTimeslots.values();
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, HOMEPAGE + CLIENT_TIMESLOT_JSP);
        if(timeslots.size() != 0) {
            map.put(TIMESLOTS, timeslots);
            HttpSession session = request.getSession();
            session.setAttribute(MASTER, masterAcc);
            session.setAttribute(DATE, date);
            map.put(DATE, date);
        } else {
            map.put(ERR_MESSAGE, TIMESLOT_MESSAGE);
        }
        System.out.println(master);
        System.out.println(date);
        return map;
    }
}
