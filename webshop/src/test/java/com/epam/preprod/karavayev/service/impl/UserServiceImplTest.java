package com.epam.preprod.karavayev.service.impl;

import com.epam.preprod.karavayev.db.dao.UserDao;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.exception.UserAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.epam.preprod.karavayev.service.UserService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    private User user;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setId(1);
        user.setFirstName("Bob");
        user.setLastName("Bee");
        user.setEmail("bob@bee.bee");
        user.setPassword("1234");
        user.setSubscription(true);
        userService = new UserServiceImpl(userDao);

    }

    @Test
    public void shouldNotThrowExceptionNoUserWithSuchEmail() {
        when(userDao.getUserByEmail(user.getEmail())).thenReturn(null);
        when(userDao.create(user)).thenReturn(true);
        userService.register(user);
    }



    @Test(expected = UserAlreadyExistsException.class)
    public void shouldThrowExceptionWhenUserExistsWithSuchEmail() {
        when(userDao.getUserByEmail(user.getEmail())).thenThrow(UserAlreadyExistsException.class);
        userService.register(user);
    }
}