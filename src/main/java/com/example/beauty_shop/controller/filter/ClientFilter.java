package com.example.beauty_shop.controller.filter;

import com.example.beauty_shop.entity.Account;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.example.beauty_shop.constants.Constants.*;

import java.io.IOException;

public class ClientFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Account account = (Account) request.getSession().getAttribute(USER);
        String[] uri = request.getRequestURI().split("/");
        if(access(account, uri[uri.length - 1])) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect(ACCESS_FAILED_JSP);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean access(Account account, String page) {
        return account != null && page.contains(account.getRole().toString().toLowerCase());
    }
}
