package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.entities.Account;
import com.example.beauty_shop.service.HomepageService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class HomepageCommand implements Command {
    private HomepageService homepageService = new HomepageService();
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Account user = (Account) request.getSession().getAttribute(USER);
        List<Account> catalog = homepageService.getPageFill(user);
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, HOMEPAGE + CLIENT_HOME);
        map.put(CATALOG, catalog);
//        request.setAttribute("hidedate", false);
        return map;
    }
}
