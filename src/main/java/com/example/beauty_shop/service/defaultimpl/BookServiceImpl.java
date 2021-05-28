package com.example.beauty_shop.service.defaultimpl;

import com.example.beauty_shop.dao.mysql.BookDaoImpl;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.BookService;

import java.sql.SQLException;

public class BookServiceImpl implements BookService {
    private BookDaoImpl bookDao = new BookDaoImpl();

    public boolean bookTime(Account master, Account client, String time, String date) {
        Long master_id = master.getId();
        Long client_id = client.getId();
        Long service_id = master.getService().getId();
        return bookDao.insertAppointment(master_id, client_id, service_id, time, date);
    }

    public void setBookDao(BookDaoImpl bookDao) {
        this.bookDao = bookDao;
    }
}
