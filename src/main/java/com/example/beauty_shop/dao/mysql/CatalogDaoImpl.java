package com.example.beauty_shop.dao.mysql;

import com.example.beauty_shop.dao.CatalogDao;
import com.example.beauty_shop.dao.DBManager;
import com.example.beauty_shop.entity.MasterSlotItem;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.Appointment;
import com.example.beauty_shop.entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class CatalogDaoImpl implements CatalogDao {

    @Override
    public List<Account> getClientCatalog() {
        Connection con = null;
        List<Account> catalog = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery("SELECT * FROM account JOIN service ON service_id = service.id;");
            while(set.next()) {
                Account master = new Account();
                Service service = new Service();
                master.setId(set.getLong(ID));
                master.setLogin(set.getString(LOGIN));
                master.setRate(set.getDouble(RATE));
                service.setName(set.getString(NAME));
                service.setDescription(set.getString(DESCRIPTION));
                master.setService(service);
                catalog.add(master);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            DBManager.closeConnection(con);
        }
        return catalog;
    }

    @Override
    public Map<String, List> getMasterCatalog(Account account, String date) {
        String updateTimeslots = "UPDATE account_has_timeslot JOIN(SELECT timeslot_id AS v1 from appointment where master_id = ?" +
                " and date = ?) A ON account_has_timeslot.timeslot_id = A.v1 AND account_has_timeslot.account_id = ?" +
                " SET account_has_timeslot.availability = 0;";
        String getTimeslots = "SELECT timeslot.id, timeslot.time, availability FROM account_has_timeslot " +
                "JOIN timeslot ON timeslot.id = timeslot_id WHERE account_id = ? ORDER BY timeslot.id;";
        String rollbackDB = "UPDATE account_has_timeslot SET availability = 1 WHERE account_id = ?";
        String getAppointments = "SELECT * FROM appointment WHERE master_id = ? and date = ?;";

        Map<String, List> map = new HashMap<>();
        Connection con = null;
         try {
             con = DBManager.getConnection();
             con.setAutoCommit(false);
             PreparedStatement prepStatementUpdateTimeslots = con.prepareStatement(updateTimeslots);
             PreparedStatement prepStatementGetTimeslots = con.prepareStatement(getTimeslots);
             PreparedStatement prepStatementRollbackDB = con.prepareStatement(rollbackDB);
             PreparedStatement prepStatementGetAppointments = con.prepareStatement(getAppointments);
             prepStatementRollbackDB.setLong(1, account.getId());
             updateTimeslots(prepStatementUpdateTimeslots, account, date);
             List<MasterSlotItem> masterCatalog = getMasterSlots(prepStatementGetTimeslots, account);
             prepStatementRollbackDB.executeUpdate();
             List<Appointment> apps = getAppointments(prepStatementGetAppointments, account, date);
             con.commit();
             map.put(CATALOG, masterCatalog);
             map.put(APPOINTMENT, apps);
         } catch (SQLException throwables) {
             rollback(con);
             throwables.printStackTrace();
         } finally {
             DBManager.closeConnection(con);
         }
         return map;
    }

    private List<MasterSlotItem> getMasterSlots(PreparedStatement st, Account account) throws SQLException {
        st.setLong(1, account.getId());
        ResultSet set = st.executeQuery();
        List<MasterSlotItem> slots = new ArrayList<>();
        while(set.next()) {
            MasterSlotItem item = new MasterSlotItem();
            item.setTimeslot_id(set.getLong(ID));
            item.setTimeslot(set.getString(TIME));
            item.setAvailability(set.getBoolean(AVAILABILITY));
            slots.add(item);
        }
        return slots;
    }

    private List<Appointment> getAppointments(PreparedStatement st, Account account, String date) throws SQLException {
        st.setLong(1, account.getId());
        st.setString(2, date);
        ResultSet set = st.executeQuery();
        List<Appointment> apps = new ArrayList<>();
        while(set.next()) {
            Appointment app = new Appointment();
            app.setMaster_id(set.getLong(MASTER_ID));
            app.setClient_id(set.getLong(CLIENT_ID));
            app.setService_id(set.getLong(SERVICE_ID));
            app.setDone(set.getBoolean(DONE));
            app.setTimeslot_id(set.getLong(TIMESLOT_ID));
            app.setDate(set.getString(DATE));
            apps.add(app);
        }
        return apps;
    }

    private void updateTimeslots(PreparedStatement st, Account account, String date) throws SQLException {
        st.setLong(1, account.getId());
        st.setString(2, date);
        st.setLong(3, account.getId());
        st.executeUpdate();
    }

    private void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
