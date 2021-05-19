package com.example.beauty_shop.dao.mysql;

import com.example.beauty_shop.dao.AppointmentDao;
import com.example.beauty_shop.dao.DBManager;
import com.example.beauty_shop.entity.Role;
import com.example.beauty_shop.entity.entities.Account;
import com.example.beauty_shop.entity.entities.Appointment;
import com.example.beauty_shop.entity.entities.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class AppointmentDaoImpl implements AppointmentDao {

    @Override
    public List<Appointment> getAppointments(String master, String date) {
        String sql = "SELECT * FROM appointment JOIN account ON master_id = account.id " +
        "WHERE account.login = ? AND date = ?;";
        Connection con = null;
        List<Appointment> appointments = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, master);
            st.setString(2, date);
//            ResultSet set = st.executeQuery("SELECT * FROM appointment JOIN account ON master_id = account.id " +
//                    "WHERE account.login = '" + master + "' AND date = '" + date + "';");
            ResultSet set = st.executeQuery();
            while(set.next()) {
                Appointment appointment = new Appointment();
                appointment.setMaster_id(set.getLong(MASTER_ID));
                appointment.setClient_id(set.getLong(CLIENT_ID));
                appointment.setService_id(set.getLong(SERVICE_ID));
                appointment.setPaid(set.getBoolean(PAID));
                appointment.setDone(set.getBoolean(DONE));
                appointment.setTimeslot_id(set.getLong(TIMESLOT_ID));
                appointment.setDate(set.getString(DATE));
                appointments.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            DBManager.closeConnection(con);
        }
        return appointments;
    }

    @Override
    public Map<Long, String> getTimeslots() {
        Connection con = null;
        Map<Long, String> timeslots = new HashMap<>();
        try {
            con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery("SELECT * FROM timeslot");
            while(set.next()) {
                timeslots.put(set.getLong(ID), set.getString(TIME));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.closeConnection(con);
        }
        return timeslots;
    }

    @Override
    public Account getMaster(String name) {
        String sql = "SELECT * FROM account JOIN service ON service_id = service_id = service.id " +
                "WHERE login = ?;";
        Connection con = null;
        Account master = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
//            ResultSet set = st.executeQuery("SELECT * FROM account " +
//                    "JOIN service ON service_id = service.id " +
//                    "where login = '" + name + "';");
            ResultSet set = st.executeQuery();
            while(set.next()) {
                master = new Account();
                master.setId(set.getLong(ID));
                master.setLogin(set.getString(LOGIN));
                Role role = Role.valueOf(set.getString(ROLE).toUpperCase());
                master.setRole(role);
                master.setRate(set.getDouble(RATE));
                Service service = new Service();
                service.setId(set.getLong(SERVICE_ID));
                service.setName(set.getString(NAME));
                service.setDescription(set.getString(DESCRIPTION));
                master.setService(service);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.closeConnection(con);
        }
        return master;
    }
}
