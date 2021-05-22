package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.HomepageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class HomepageCommand implements Command {
    private final HomepageService homepageService = new HomepageService();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        Account currentUser = (Account) request.getSession().getAttribute(USER);
        Map<String, Object> catalog = homepageService.getPageFill(currentUser);
        Map<String, Object> map = new HashMap<>();
        String userRole = currentUser.getRole().toString().toLowerCase();
        map.put(PAGE, HOMEPAGE + userRole + H0ME_JSP);
        map.put(CATALOG, catalog.get(CATALOG));
        request.getSession().setAttribute(HIDEDATE, false);
        return map;
    }
}
