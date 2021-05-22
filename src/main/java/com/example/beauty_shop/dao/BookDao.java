package com.example.beauty_shop.dao;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface BookDao {
    Boolean insertAppointment (Long master_id, Long client_id, Long service_id, String time, String date) throws SQLException, NamingException;
}
