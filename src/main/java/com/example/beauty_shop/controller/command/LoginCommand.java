package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.beauty_shop.constants.Constants.*;

public class LoginCommand implements Command {
    private final LoginService loginService = new LoginService();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        Map<String, Object> loginMap = loginService.login(username, password);
        Optional<Account> account =  (Optional<Account>) loginMap.get(USER);

        if(account.isPresent()) {
            initSession(request, map, loginMap, account.get());
        } else {
            map.put(PAGE, INDEX_JSP);
            map.put(ERR_MESSAGE, ERROR_MESSAGE);
        }
        return map;
    }

    private void initSession(HttpServletRequest request, Map<String, Object> map, Map<String, Object> loginMap, Account account) {
        String role = account.getRole().toString().toLowerCase();
        map.put(PAGE, HOMEPAGE + role + H0ME_JSP);
        map.put(CATALOG, loginMap.get(CATALOG));
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(-1);
        session.setAttribute(USER, account);
        session.setAttribute("userLoggedIn", true);
    }
}
