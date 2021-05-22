package com.example.beauty_shop.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class ChangeLanguageCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        String language = request.getParameter(LANGUAGE);
        request.getSession().setAttribute(LANGUAGE, language);
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, INDEX_JSP);
        return map;
    }
}
