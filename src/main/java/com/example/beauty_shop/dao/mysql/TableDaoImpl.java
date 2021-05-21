package com.example.beauty_shop.dao.mysql;

import com.example.beauty_shop.dao.TableDao;
import com.example.beauty_shop.dao.DBManager;
import com.example.beauty_shop.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class TableDaoImpl implements TableDao {

    @Override
    public List<Account> getClientTable() {
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
    public Map<String, List> getMasterTable(Account account, String date) {
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

    @Override
    public List<AdminTableItem> getAdminTable() {
        Connection con = null;
        List<AdminTableItem> adminTable = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery("SELECT A.id, account.id, timeslot.id, A.login, account.login, " +
                    "service.name, timeslot.time, date, paid from appointment " +
                    "JOIN service on service_id = service.id " +
                    "JOIN(SELECT id, login from account) A on A.id = master_id " +
                    "JOIN timeslot on timeslot_id = timeslot.id " +
                    "JOIN account on account.id = client_id " +
                    "ORDER BY date, time;");
            initList(set, adminTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.closeConnection(con);
        }
        return adminTable;
    }

    private void initList(ResultSet set, List<AdminTableItem> adminTable) throws SQLException {
        while(set.next()) {
            AdminTableItem item = new AdminTableItem();
            item.setMasterId(set.getLong(SQL_A_ID));
            item.setClientId(set.getLong(SQL_ACCOUNT_ID));
            item.setTimeslotId(set.getLong(SQL_TIMESLOT_ID));
            item.setMasterName(set.getString(SQL_A_LOGIN));
            item.setClientName(set.getString(SQL_ACCOUNT_LOGIN));
            item.setServiceName(set.getString(SQL_SERVICE_NAME));
            item.setTime(set.getString(SQL_TIMESLOT_TIME));
            item.setDate(set.getString(DATE));
            item.setPaid(set.getBoolean(PAID));
            adminTable.add(item);
        }
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
