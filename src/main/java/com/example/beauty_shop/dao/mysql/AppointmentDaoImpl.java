package com.example.beauty_shop.dao.mysql;

import com.example.beauty_shop.dao.AppointmentDao;
import com.example.beauty_shop.dao.DBManager;
import com.example.beauty_shop.entity.Role;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.Appointment;
import com.example.beauty_shop.entity.Service;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;
import static com.example.beauty_shop.constants.SQLConstants.*;

public class AppointmentDaoImpl implements AppointmentDao {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public List<Appointment> getAppointments(String master, String date) {
        Connection con = null;
        List<Appointment> appointments = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            PreparedStatement st = con.prepareStatement(SELECT_APPOINTMENT_JOIN_ACCOUNT);
            st.setString(1, master);
            st.setString(2, date);
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
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
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
            ResultSet set = st.executeQuery(SELECT_TIMESLOT);
            while(set.next()) {
                timeslots.put(set.getLong(ID), set.getString(TIME));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            DBManager.closeConnection(con);
        }
        return timeslots;
    }

    @Override
    public Account getMaster(String name) {
        Connection con = null;
        Account master = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement st = con.prepareStatement(SELECT_ACCOUNT_JOIN_SERVICE_WHERE);
            st.setString(1, name);
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
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            DBManager.closeConnection(con);
        }
        return master;
    }

    @Override
    public boolean markAsDoneAppointment(Long masterId, Long timeslotId, String date) {
        Connection con = null;
        int rowsUpdated = 0;
        try {
            con = DBManager.getConnection();
            PreparedStatement updateStatement = con.prepareStatement(UPDATE_APPOINTMENT);
            updateStatement.setLong(1, masterId);
            updateStatement.setLong(2, timeslotId);
            updateStatement.setString(3, date);
            rowsUpdated = updateStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "AppointmentDao: ", e);
        } finally {
            DBManager.closeConnection(con);
        }
        return rowsUpdated == 1;
    }

    @Override
    public boolean updateAppointment(Long masterId, Long clientId, Long timeslotId, String date, String action) {
        String query = getQuery(action);
        Connection con = null;
        int rowsUpdated = 0;
        try {
            con = DBManager.getConnection();
            PreparedStatement updateSt = con.prepareStatement(query);
            initStatement(updateSt, masterId, clientId, timeslotId, date);
            rowsUpdated = updateSt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "AppointmentDao: ", e);
        } finally {
            DBManager.closeConnection(con);
        }
        return rowsUpdated == 1;
    }

    @Override
    public boolean changeTimeslot(Long masterId, Long clientId, Long timeslotId, String date, String newTimeslot) {
        Connection con = null;
        int rowsUpdated = 0;
        try {
            con = DBManager.getConnection();
            PreparedStatement selectSt = con.prepareStatement(SELECT_ID_FROM_TIMESLOT);
            PreparedStatement updateSt = con.prepareStatement(UPDATE_APPOINTMENT_SET_TIMESLOT);
            selectSt.setString(1, newTimeslot);
            Long newId = getTimeslotId(selectSt);
            initUpdateSt(updateSt, masterId, clientId, timeslotId, date, newId);
            rowsUpdated = updateSt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "AppointmentDao: ", e);
        } finally {
            DBManager.closeConnection(con);
        }
        return rowsUpdated == 1;
    }

    private Long getTimeslotId(PreparedStatement st) throws SQLException {
        ResultSet set = st.executeQuery();
        set.next();
        return set.getLong(ID);
    }

    private String getQuery(String action) {
        if(action.equals("confirm")) {
            return UPDATE_APPOINTMENT_SET_PAID;
        }
        return DELETE_FROM_APPOINTMENT;
    }

    private void initUpdateSt(PreparedStatement st, Long masterId, Long clientId, Long timeslotId, String date, Long newTimeslotId) throws SQLException {
        st.setLong(1, newTimeslotId);
        st.setLong(2, masterId);
        st.setLong(3, clientId);
        st.setLong(4, timeslotId);
        st.setString(5, date);
    }

    private void initStatement(PreparedStatement st, Long masterId, Long clientId, Long timeslotId, String date) throws SQLException {
        st.setLong(1, masterId);
        st.setLong(2, clientId);
        st.setLong(3, timeslotId);
        st.setString(4, date);
    }
}
