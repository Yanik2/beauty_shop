package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.service.defaultimpl.AppointmentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Map;

public class MarkAsDoneCommandTest {
    @Mock
    private AppointmentServiceImpl appointmentServiceMock;
    @Mock
    private HttpServletRequest requestMock;
    @Mock
    HttpServletResponse responseMock;
    @Mock
    private Account accountMock;
    @Mock
    HttpSession sessionMock;
    @InjectMocks
    private MarkAsDoneCommand testingInstance;

    @Test
    public void shouldReturnMarkSuccess() throws SQLException, NamingException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(requestMock.getParameter("timeslot_id")).thenReturn("1");
        Mockito.when(requestMock.getSession()).thenReturn(sessionMock);
        Mockito.when(requestMock.getSession().getAttribute("user")).thenReturn(accountMock);
        Mockito.when(accountMock.getId()).thenReturn(1L);
        Mockito.when(appointmentServiceMock.markAsDone(1L, 1L, "2021-05-27")).thenReturn(true);
        Map<String, Object> map = testingInstance.execute(requestMock, responseMock);
        String result = (String)map.get("message");
        Assertions.assertEquals("markSuccess", result);
    }

    @Test
    public void shouldReturnFailure() throws SQLException, NamingException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(requestMock.getParameter("timeslot_id")).thenReturn("1");
        Mockito.when(requestMock.getSession()).thenReturn(sessionMock);
        Mockito.when(requestMock.getSession().getAttribute("user")).thenReturn(accountMock);
        Mockito.when(accountMock.getId()).thenReturn(1L);
        Mockito.when(appointmentServiceMock.markAsDone(1L, 1L, "2021-05-27")).thenReturn(false);
        Map<String, Object> map = testingInstance.execute(requestMock, responseMock);
        String result = (String)map.get("message");
        Assertions.assertEquals("markFailure", result);
    }

}