package com.example.beauty_shop.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Map;

public interface Command {
    Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException;
}
