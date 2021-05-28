package com.example.beauty_shop.controller;

import com.example.beauty_shop.constants.Constants;
import com.example.beauty_shop.controller.command.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private Map<String, Command> commands;

    @Override
    public void init() {
        commands = new HashMap<>();
        commands.put(SERVICE_CATALOG, new ServiceCatalogCommand());
        commands.put(MAIN, new MainPageCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(APPOINTMENT, new AppointmentCommand());
        commands.put(BOOK_TIME, new BookTimeCommand());
        commands.put(HOMEPAGE_COMMAND, new HomepageCommand());
        commands.put(LOGOUT, new LogOutCommand());
        commands.put(MARKASDONE, new MarkAsDoneCommand());
        commands.put(FILTERBYDATE, new FilterByDateCommand());
        commands.put(UPDATE, new UpdateAppointmentCommand());
        commands.put(CHANGE_TIME_SLOT, new ChangeTimeslotCommand());
        commands.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(LEAVE_FEEDBACK, new LeaveFeedbackCommand());
        commands.put(SUBMIT_FEEDBACK, new SubmitFeedbackCommand());
        commands.put(ALL_FEEDBACK, new AllFeedbackCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = commands.get(request.getParameter(Constants.COMMAND));
        Map<String, Object> map = command.execute(request, response);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        getServletContext().getRequestDispatcher((String) map.get(Constants.PAGE)).forward(request, response);
    }
}
