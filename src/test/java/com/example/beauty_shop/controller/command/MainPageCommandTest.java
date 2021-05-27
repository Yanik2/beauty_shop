package com.example.beauty_shop.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainPageCommandTest {
    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;

    private MainPageCommand testingInstance = new MainPageCommand();
    @Test
    public void shouldReturnMainPage() {
        Map<String, Object> map = testingInstance.execute(requestMock, responseMock);
        String page = (String) map.get("page");
        Assertions.assertEquals("/index.jsp", page);
    }
}