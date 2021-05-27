package com.example.beauty_shop.service.defaultimpl;

import com.example.beauty_shop.dao.mysql.AccountDaoImpl;
import com.example.beauty_shop.dao.mysql.TableDaoImpl;
import com.example.beauty_shop.entity.Account;
import com.example.beauty_shop.entity.Role;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LoginServiceImplTest {

    @Mock
    private AccountDaoImpl accountDaoMock;
    @Mock
    private TableDaoImpl tableDaoMock;
    @Mock
    private Account accountMock;
    @Mock
    private HomepageServiceImpl homepageServiceMock;
    @InjectMocks
    private LoginServiceImpl testingInstance;

    @Test
    public void shouldReturnClientTable() throws SQLException, NamingException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(accountMock.getPassword()).thenReturn("qwerty");
        Mockito.when(accountDaoMock.findByName("Ivan")).thenReturn(Optional.of(accountMock));
        Mockito.when(accountMock.getRole()).thenReturn(Role.CLIENT);
        Mockito.when(tableDaoMock.getClientTable()).thenReturn(new ArrayList<>());
        Map<String, Object> map = testingInstance.login("Ivan", "qwerty");
        Object obj = map.get("catalog");
        Assertions.assertTrue(obj instanceof List);
    }

    @Test
    public void shouldReturnAdminTable() throws SQLException, NamingException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(accountMock.getPassword()).thenReturn("qwerty");
        Mockito.when(accountDaoMock.findByName("Ivan")).thenReturn(Optional.of(accountMock));
        Mockito.when(accountMock.getRole()).thenReturn(Role.ADMIN);
        Mockito.when(tableDaoMock.getAdminTable()).thenReturn(new ArrayList<>());
        Map<String, Object> map = testingInstance.login("Ivan", "qwerty");
        Object obj = map.get("catalog");
        Assertions.assertTrue(obj instanceof List);
    }
}