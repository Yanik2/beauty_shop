package com.example.beauty_shop.service;

import com.example.beauty_shop.entity.Account;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface BookService {
    boolean bookTime(Account master, Account client, String time, String date) throws SQLException, NamingException;
}
