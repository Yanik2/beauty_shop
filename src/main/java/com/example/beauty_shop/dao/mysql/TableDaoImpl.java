package com.example.beauty_shop.dao.mysql;

import com.example.beauty_shop.dao.TableDao;
import com.example.beauty_shop.dao.DBManager;
import com.example.beauty_shop.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.beauty_shop.constants.Constants.*;
import static com.example.beauty_shop.constants.SQLConstants.*;

public class TableDaoImpl implements TableDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Account> getClientTable() {
        Connection con = null;
        List<Account> catalog = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(SELECT_ACCOUNT_JOIN_SERVICE);
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
        } catch (SQLException e) {
            logger.log(Level.ERROR,  e);
        }
        finally {
            DBManager.closeConnection(con);
        }
        return catalog;
    }

    @Override
    public ListWrapper getMasterTable(Account account, String date) {
        ListWrapper wrapper = new ListWrapper();
        Connection con = null;
         try {
             con = DBManager.getConnection();
             con.setAutoCommit(false);
             PreparedStatement prepStatementUpdateTimeslots = con.prepareStatement(UPDATE_ACCOUNT_HAS_TIMESLOT);
             PreparedStatement prepStatementGetTimeslots = con.prepareStatement(SELECT_ACCOUNT_HAS_TIMESLOT_JOIN_TIMESLOT);
             PreparedStatement prepStatementRollbackDB = con.prepareStatement(ROLLBACK_ACCOUNT_HAS_TIMESLOT);
             PreparedStatement prepStatementGetAppointments = con.prepareStatement(SELECT_APPOINTMENTS_BY_MASTERID);
             prepStatementRollbackDB.setLong(1, account.getId());
             updateTimeslots(prepStatementUpdateTimeslots, account, date);
             List<MasterSlotItem> masterCatalog = getMasterSlots(prepStatementGetTimeslots, account);
             prepStatementRollbackDB.executeUpdate();
             List<Appointment> apps = getAppointments(prepStatementGetAppointments, account, date);
             con.commit();
             wrapper.setMasterSlots(masterCatalog);
             wrapper.setAppointments(apps);
         } catch (SQLException e) {
             DBManager.rollback(con);
             logger.log(Level.ERROR, "TableDao: ", e);
         } finally {
             DBManager.closeConnection(con);
         }
         return wrapper;
    }

    @Override
    public List<AdminTableItem> getAdminTable() {
        Connection con = null;
        List<AdminTableItem> adminTable = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(SELECT_ADMIN_TABLE);
            initList(set, adminTable);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "TableDao: ", e);
            throw new RuntimeException(e.getMessage());
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
}
