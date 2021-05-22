package com.example.beauty_shop.service;

import com.example.beauty_shop.dao.mysql.BookDaoImpl;
import com.example.beauty_shop.entity.Account;

import javax.naming.NamingException;
import java.sql.SQLException;

public class BookService {
    private final BookDaoImpl bookDao = new BookDaoImpl();

    public boolean bookTime(Account master, Account client, String time, String date) throws SQLException, NamingException {
        Long master_id = master.getId();
        Long client_id = client.getId();
        Long service_id = master.getService().getId();
        return bookDao.insertAppointment(master_id, client_id, service_id, time, date);
    }
}
