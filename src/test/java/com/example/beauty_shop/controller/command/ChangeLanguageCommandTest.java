package com.example.beauty_shop.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.*;

public class ChangeLanguageCommandTest {
    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;
    @Mock
    private HttpSession sessionMock;

    private ChangeLanguageCommand testingInstance = new ChangeLanguageCommand();

    @Test
    public void shouldReturnMainPage() throws SQLException, NamingException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(requestMock.getParameter("language")).thenReturn("en");
        Mockito.when(requestMock.getSession()).thenReturn(sessionMock);
        Map<String, Object> map = testingInstance.execute(requestMock, responseMock);
        String page = (String) map.get("page");
        Assert.assertEquals("/index.jsp", page);
    }

}