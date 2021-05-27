package com.example.beauty_shop.constants;

public class SQLConstants {
    public static final String SELECT_ACCOUNT_BY_LOGIN = "SELECT * FROM account WHERE login = ?;";
    public static final String SELECT_ACCOUNT_BY_ID = "SELECT * FROM account WHERE ID = ?;";
    public static final String SELECT_ACCOUNT_JOIN_SERVICE = "SELECT * FROM account JOIN service ON service_id = service.id;";
    public static final String SELECT_ACCOUNT_JOIN_SERVICE_WHERE = "SELECT * FROM account JOIN service ON service_id = service.id " +
            "WHERE login = ?;";
    public static final String UPDATE_ACCOUNT_HAS_TIMESLOT = "UPDATE account_has_timeslot " +
            "JOIN(SELECT timeslot_id AS v1 FROM appointment WHERE master_id = ? " +
            "AND date = ?) A ON account_has_timeslot.timeslot_id = A.v1 AND account_has_timeslot.account_id = ? " +
            "SET account_has_timeslot.availability = 0;";
    public static final String SELECT_ACCOUNT_HAS_TIMESLOT_JOIN_TIMESLOT = "SELECT timeslot.id, timeslot.time, availability " +
            "FROM account_has_timeslot JOIN timeslot ON timeslot.id = timeslot_id " +
            "WHERE account_id = ? ORDER BY timeslot.id;";
    public static final String ROLLBACK_ACCOUNT_HAS_TIMESLOT = "UPDATE account_has_timeslot SET availability = 1 " +
            "WHERE account_id = ?;";
    public static final String SELECT_APPOINTMENTS_BY_MASTERID = "SELECT * FROM appointment " +
            "WHERE master_id = ? AND date = ?;";
    public static final String SELECT_ADMIN_TABLE = "SELECT A.ID, account.id, timeslot.id, A.login, account.login, " +
            "service.name, timeslot.time, date, paid " +
            "FROM appointment JOIN service ON service_id = service.id " +
            "JOIN(SELECT id, login FROM account) A ON A.id = master_id " +
            "JOIN timeslot ON timeslot_id = timeslot.id " +
            "JOIN account ON account.id = client_id " +
            "ORDER BY date, time;";
    public static final String INSERT_FEEDBACK = "INSERT INTO feedback (master_id, client_id, feedback_text, rate) " +
            "VALUES (?, ?, ?, ?);";
    public static final String UPDATE_ACCOUNT = "UPDATE account SET rate = ?, feedback_amount = ? " +
            "WHERE id = ?;";
    public static final String SELECT_ALL_FEEDBACKS = "SELECT A.login, account.login, feedback_text, feedback.rate " +
            "FROM feedback JOIN(SELECT id, login FROM account) A ON A.id = master_id " +
            "JOIN account ON account.id = client_id;";
    public static final String SELECT_ALL_TIMESLOT = "SELECT * FROM timeslot WHERE time = ?;";
    public static final String INSERT_APPOINTMENT = "INSERT INTO appointment " +
            "(master_id, client_id, service_id, timeslot_id, date) VALUES(?, ?, ?, ?, ?);";
    public static final String SELECT_APPOINTMENT_JOIN_ACCOUNT = "SELECT * FROM appointment " +
            "JOIN account ON master_id = account.id WHERE account.login = ? AND date = ?;";
    public static final String SELECT_TIMESLOT = "SELECT * FROM timeslot;";
    public static final String UPDATE_APPOINTMENT = "UPDATE appointment SET done = true " +
            "WHERE master_id = ? AND timeslot_id = ? AND date = ?;";
    public static final String SELECT_ID_FROM_TIMESLOT = "SELECT id FROM timeslot WHERE time = ?;";
    public static final String UPDATE_APPOINTMENT_SET_TIMESLOT = "UPDATE appointment SET timeslot_id = ? " +
            "WHERE master_id = ? AND client_id = ? AND timeslot_id = ? AND date = ?;";
    public static final String UPDATE_APPOINTMENT_SET_PAID = "UPDATE appointment SET paid = true " +
            "WHERE master_id = ? AND client_id = ? AND timeslot_id = ? AND date = ?;";
    public static final String DELETE_FROM_APPOINTMENT = "DELETE FROM appointment " +
            "WHERE master_id = ? AND client_id = ? AND timeslot_id = ? AND date = ?;";


}
