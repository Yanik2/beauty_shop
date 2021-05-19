package com.example.beauty_shop.dao.mysql;

import com.example.beauty_shop.dao.CatalogDao;
import com.example.beauty_shop.dao.DBManager;
import com.example.beauty_shop.entity.entities.Account;
import com.example.beauty_shop.entity.entities.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.example.beauty_shop.constants.Constants.*;

public class CatalogDaoImpl implements CatalogDao {

    @Override
    public List<Account> getCatalog() {
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
                Double rate = set.getDouble(RATE);
                master.setRate(rate != null ? rate : 0.0);
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
}
