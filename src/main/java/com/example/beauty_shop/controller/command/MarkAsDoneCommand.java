package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.defaultimpl.AppointmentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class MarkAsDoneCommand implements Command {
    private AppointmentServiceImpl appointmentServiceImpl = new AppointmentServiceImpl();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(SHOWLINK, true);
        String timeslot = request.getParameter("timeslot_id");
        Long timeslotId = Long.parseLong(timeslot);
        String date = LocalDate.now().toString();
        Account master = (Account) request.getSession().getAttribute(USER);
        Long masterId = master.getId();
        boolean markSuccess = appointmentServiceImpl.markAsDone(masterId, timeslotId, date);
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, HOMEPAGE + MASTER_HOME);
        if(markSuccess) {
            map.put(MESSAGE, MARK_SUCCESS);
        } else {
            map.put(MESSAGE, MARK_FAILURE);
        }
        return map;
    }

    public void setAppointmentServiceImpl(AppointmentServiceImpl appointmentServiceImpl) {
        this.appointmentServiceImpl = appointmentServiceImpl;
    }
}
