package com.example.beauty_shop.service;

import com.example.beauty_shop.entity.Account;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface CatalogService {
    List<Account> getCatalog(String sortMethod, String filterMethod, String filter) throws SQLException, NamingException;
}
