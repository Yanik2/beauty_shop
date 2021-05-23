package com.example.beauty_shop.service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Map;

public interface LoginService {
    Map<String, Object> login(String login, String password) throws SQLException, NamingException;
}
