package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.CatalogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class ServiceCatalogCommand implements Command {
    private final CatalogService catalogService = new CatalogService();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        String sortMethod = request.getParameter(SORT_METHOD);
        String filterMethod = request.getParameter(FILTER_METHOD);
        String filter = request.getParameter(FILTER);
        List<Account> catalog = catalogService.getCatalog(sortMethod, filterMethod, filter);
        Map<String, Object> map = new HashMap<>();
        if(catalog.size() == 0) {
            map.put(PAGE, SERVICE_NOT_FOUND_JSP);
        } else {
            map.put(CATALOG, catalog);
            map.put(PAGE, SERVICE_JSP);
        }
        return map;
    }
}
