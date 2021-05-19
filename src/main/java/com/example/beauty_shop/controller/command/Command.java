package com.example.beauty_shop.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public interface Command {
    Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response);
}
