package com.example.beauty_shop.dao.mysql;

import com.example.beauty_shop.dao.DBManager;
import com.example.beauty_shop.dao.FeedbackDao;
import com.example.beauty_shop.entity.Feedback;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDaoImpl implements FeedbackDao {
    private static final Logger logger = LogManager.getLogger();

    public boolean insertFeedback(Long masterId, Long clientId, String comment, Double clientRate, Double newRate, Integer feedbackAmount) {
        String insertFeedback = "INSERT INTO feedback (master_id, client_id, feedback_text, rate) " +
                "values (?, ?, ?, ?);";
        String updateAccount = "UPDATE account SET rate = ?, feedback_amount = ? " +
                "WHERE id = ?;";
        Connection con = null;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement insertSt = con.prepareStatement(insertFeedback);
            PreparedStatement updateSt = con.prepareStatement(updateAccount);
            initInsertSt(insertSt, masterId, clientId, comment, clientRate);
            initUpdateSt(updateSt, newRate, feedbackAmount, masterId);
            insertSt.executeUpdate();
            updateSt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            DBManager.rollback(con);
            return false;
        } finally {
            DBManager.closeConnection(con);
        }
        return true;
    }

    @Override
    public List<Feedback> findAll() {
        Connection con = null;
        List<Feedback> feedbacks = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            Statement getAll = con.createStatement();
            ResultSet set = getAll.executeQuery("SELECT A.login, account.login, feedback_text, feedback.rate FROM feedback " +
                    "JOIN (SELECT id, login FROM account) A ON A.id = master_id " +
                    "JOIN account ON account.id = client_id;");
            initList(set, feedbacks);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return feedbacks;
    }

    private void initInsertSt(PreparedStatement st, Long masterId, Long clientId, String comment, Double clientRate) throws SQLException {
        st.setLong(1, masterId);
        st.setLong(2, clientId);
        st.setString(3, comment);
        st.setDouble(4, clientRate);
    }

    private void initUpdateSt(PreparedStatement st, Double rate, Integer feedbackAmount, Long masterId) throws SQLException {
        st.setDouble(1, rate);
        st.setInt(2, feedbackAmount);
        st.setLong(3, masterId);
    }

    private void initList(ResultSet set, List<Feedback> list) throws SQLException {
        while(set.next()) {
            Feedback feedback = new Feedback();
            feedback.setMasterName(set.getString("A.login"));
            feedback.setClientName(set.getString("account.login"));
            feedback.setComment(set.getString("feedback_text"));
            feedback.setRate(set.getDouble("feedback.rate"));
            list.add(feedback);
        }
    }
}
