package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.captcha.Captcha;
import com.epam.preprod.karavayev.captcha.store.method.impl.AbstractStoreMethod;
import com.epam.preprod.karavayev.converter.HttpRequestToUserDto;
import com.epam.preprod.karavayev.converter.UserRegDtoToUser;
import com.epam.preprod.karavayev.dto.UserRegistrationDto;
import com.epam.preprod.karavayev.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.epam.preprod.karavayev.service.UserService;
import com.epam.preprod.karavayev.util.UserRegistrationDtoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private UserRegistrationDtoValidator validator;
    @Mock
    private UserService userService;
    @Mock
    private Map<String, String> errors;
    @Mock
    private HttpRequestToUserDto converterToDto;
    @Mock
    private UserRegDtoToUser converterToUser;
    @Mock
    private User user;
    @Mock
    private UserRegistrationDto dto;
    @Mock
    private Captcha captcha;
    @Mock
    private AbstractStoreMethod storeMethod;
    @InjectMocks
    private RegistrationServlet registrationServlet;

    @Before
    public void setUp() {
        when(request.getSession()).thenReturn(session);

    }


     @Test
    public void shouldNotInvokeUserServiceRegisterWhenValidatorReturnNotEmptyMap() throws IOException {
         when(converterToDto.convert(request)).thenReturn(dto);
         when(storeMethod.getCaptcha(request)).thenReturn(captcha);
         when(validator.validate(dto, captcha)).thenReturn(errors);
         when(errors.isEmpty()).thenReturn(false);
         verify(userService, never()).register(user);
         registrationServlet.doPost(request, response);
    }

    @Test
    public void shouldInvokeUserServiceRegisterWhenValidatorReturnEmptyMap() throws IOException {
        when(converterToDto.convert(request)).thenReturn(dto);
        when(storeMethod.getCaptcha(request)).thenReturn(captcha);
        when(validator.validate(dto, storeMethod.getCaptcha(request))).thenReturn(errors);
        when(errors.isEmpty()).thenReturn(true);
        if (errors.isEmpty()) {
            userService.register(user);
        }
        verify(userService, atLeastOnce()).register(user);
        registrationServlet.doPost(request, response);
    }
}
