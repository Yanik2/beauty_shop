package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.AppointmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.example.beauty_shop.constants.Constants.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AppointmentCommand implements Command {
    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(HIDEDATE, true);
        String master = request.getParameter(MASTER);
        String date = request.getParameter(APPOINTMENT_DATE);
        String currentDate = LocalDate.now().toString();
        Map<String, Object> map = new HashMap<>();
        if(currentDate.compareTo(date) > 0) {
            map.put(MESSAGE, CORRECT_DATE);
            map.put(PAGE, HOMEPAGE + CLIENT_HOME);
            return map;
        }
        Map<String, Object> mapFromService = appointmentService.getAvailableTime(master, date);
        Account masterAcc = (Account)mapFromService.get(MASTER);
        Map<Long, String> mapTimeslots = (Map<Long, String>) mapFromService.get(TIMESLOTS);
        Collection<String> timeslots =  mapTimeslots.values();
        map.put(PAGE, HOMEPAGE + CLIENT_TIMESLOT_JSP);
        if(timeslots.size() != 0) {
            initSession(request, date, map, masterAcc, timeslots);
        } else {
            map.put(ERR_MESSAGE, TIMESLOT_MESSAGE);
        }
        return map;
    }

    private void initSession(HttpServletRequest request, String date, Map<String, Object> map, Account masterAcc, Collection<String> timeslots) {
        map.put(TIMESLOTS, timeslots);
        HttpSession session = request.getSession();
        session.setAttribute(MASTER, masterAcc);
        session.setAttribute(DATE, date);
    }
}
