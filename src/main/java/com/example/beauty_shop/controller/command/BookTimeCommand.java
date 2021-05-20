package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class BookTimeCommand implements Command {
    private final BookService bookService = new BookService();
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Account client = (Account) request.getSession().getAttribute(USER);
        Account master = (Account) request.getSession().getAttribute(MASTER);
        String timeslot = request.getParameter(TIMESLOT);
        String date = (String) request.getSession().getAttribute(DATE);
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, HOMEPAGE + CLIENT_HOME);
        if(bookService.bookTime(master, client, timeslot, date)) {
            map.put(MESSAGE, MESSAGE_SUCCESS);
        } else {
            map.put(MESSAGE, MESSAGE_FAILURE);
        }
        return map;
    }
}
