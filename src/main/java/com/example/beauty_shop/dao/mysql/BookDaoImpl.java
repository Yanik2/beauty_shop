package com.example.beauty_shop.dao.mysql;

import com.example.beauty_shop.dao.BookDao;
import com.example.beauty_shop.dao.DBManager;

import java.sql.*;

import static com.example.beauty_shop.constants.Constants.*;

public class BookDaoImpl implements BookDao {

    @Override
    public Boolean insertAppointment(Long master_id, Long client_id, Long service_id, String time, String date) {
        String selectTimeslot = "SELECT * FROM timeslot WHERE time = ?;";
        String addAppointment = "INSERT INTO appointment (master_id, client_id, service_id, timeslot_id, date) " +
                "values (?, ?, ?, ?, ?);";

        Connection con = null;
        int rowsUpdated = 0;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement preparedStatement1 = con.prepareStatement(selectTimeslot);
            PreparedStatement preparedStatement2 = con.prepareStatement(addAppointment);
            preparedStatement1.setString(1, time);
            ResultSet set = preparedStatement1.executeQuery();
            set.next();
            Long timeslot_id = set.getLong(ID);
            initStatement(master_id, client_id, service_id, date, preparedStatement2, timeslot_id);
            rowsUpdated = preparedStatement2.executeUpdate();
            con.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.closeConnection(con);
        }
        return rowsUpdated == 1;
    }

    private void initStatement(Long master_id, Long client_id, Long service_id, String date, PreparedStatement preparedStatement2, Long timeslot_id) throws SQLException {
        preparedStatement2.setLong(1, master_id);
        preparedStatement2.setLong(2, client_id);
        preparedStatement2.setLong(3, service_id);
        preparedStatement2.setLong(4, timeslot_id);
        preparedStatement2.setString(5, date);
    }
}
