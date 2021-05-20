package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.HomepageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class HomepageCommand implements Command {
    private final HomepageService homepageService = new HomepageService();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Account currentUser = (Account) request.getSession().getAttribute(USER);
        String userRole = currentUser.getRole().toString().toLowerCase();
        List<Account> catalog = (List<Account>) homepageService.getPageFill(currentUser).get(CATALOG);
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, HOMEPAGE + userRole + H0ME_JSP);
        map.put(CATALOG, catalog);
        request.getSession().setAttribute(HIDEDATE, false);
        return map;
    }
}
