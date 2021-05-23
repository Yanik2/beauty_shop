package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.service.defaultimpl.AppointmentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class UpdateAppointmentCommand implements Command {
    private final AppointmentServiceImpl appointmentServiceImpl = new AppointmentServiceImpl();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        Long masterId = Long.parseLong(request.getParameter(MASTERID));
        Long clientId = Long.parseLong(request.getParameter(CLIENTID));
        Long timeslotId = Long.parseLong(request.getParameter(TIMESLOTID));
        String date = request.getParameter(DATE);
        String action = request.getParameter(ACTION);
        String masterName = request.getParameter(MASTERNAME);
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, HOMEPAGE + ADMIN_HOME);
        if(action.equals("changeTime")) {
            initSession(request, masterId, clientId, timeslotId, date);
            Map<String, Object> availableTime = appointmentServiceImpl.getAvailableTime(masterName, date);
            Map<Long, String> mapTimeslots = (Map<Long, String>) availableTime.get(TIMESLOTS);
            Collection<String> timeslots = mapTimeslots.values();
            map.put(TIMESLOTS, timeslots);
            map.put(PAGE, HOMEPAGE + ADMIN_CHANGE_TIME);
            return map;
        }

        if(appointmentServiceImpl.updateAppointment(masterId, clientId, timeslotId, date, action)) {
            map.put(MESSAGE, UPDATE_SUCCESS);
            map.put(SHOWLINK, true);
        } else {
            map.put(MESSAGE, MESSAGE_FAILURE);
        }
        return map;
    }

    private void initSession(HttpServletRequest request, Long masterId, Long clientId, Long timeslotId, String date) {
        request.getSession().setAttribute(MASTERID, masterId);
        request.getSession().setAttribute(CLIENTID, clientId);
        request.getSession().setAttribute(TIMESLOTID, timeslotId);
        request.getSession().setAttribute(DATE, date);
    }
}
