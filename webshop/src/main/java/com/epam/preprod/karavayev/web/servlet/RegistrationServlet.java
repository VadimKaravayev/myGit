package com.epam.preprod.karavayev.web.servlet;

import com.epam.preprod.karavayev.captcha.store.method.impl.AbstractStoreMethod;
import com.epam.preprod.karavayev.constant.Attributes;
import com.epam.preprod.karavayev.constant.HtmlFormParameters;
import com.epam.preprod.karavayev.constant.UrlMapping;
import com.epam.preprod.karavayev.constant.ViewMessages;
import com.epam.preprod.karavayev.converter.HttpRequestToUserDto;
import com.epam.preprod.karavayev.converter.UserRegDtoToUser;
import com.epam.preprod.karavayev.dto.UserRegistrationDto;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.exception.UserAlreadyExistsException;
import com.epam.preprod.karavayev.service.UserService;
import com.epam.preprod.karavayev.util.UserRegistrationDtoValidator;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private UserRegistrationDtoValidator validator;
    private UserService userService;
    private HttpRequestToUserDto converterToDto;
    private UserRegDtoToUser converterToUser;
    private AbstractStoreMethod storeMethod;

    @Override
    public void init() {
        converterToDto = new HttpRequestToUserDto();
        converterToUser = new UserRegDtoToUser();
        validator = new UserRegistrationDtoValidator();
        userService = (UserService) getServletContext().getAttribute(Attributes.CONTEXT_USER_SERVICE);
        storeMethod = (AbstractStoreMethod) getServletContext().getAttribute(Attributes.CAPTCHA_STORE_METHOD);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserRegistrationDto userRegistrationDto = converterToDto.convert(request);

        Map<String, String> errors = validator.validate(userRegistrationDto, storeMethod.getCaptcha(request));

        if (errors.isEmpty()) {
            User user = converterToUser.convert(userRegistrationDto);
            try {
                userService.register(user);
                session.setAttribute(Attributes.USER_ENTITY, user);
                response.sendRedirect("home");
                return;
            } catch (UserAlreadyExistsException e) {
                errors.put(HtmlFormParameters.REG_EMAIL, ViewMessages.EMAIL_ALREADY_EXISTS);
            }
        }
        session.setAttribute(Attributes.USER_REG_DTO, userRegistrationDto);
        session.setAttribute(Attributes.USER_REG_ERRORS, errors);

        response.sendRedirect("register");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(Attributes.CAPTCHA_ID, uuid);

        HttpSession session = request.getSession();
        request.setAttribute(Attributes.USER_REG_DTO, session.getAttribute(Attributes.USER_REG_DTO));
        session.removeAttribute(Attributes.USER_REG_DTO);
        request.setAttribute(Attributes.USER_REG_ERRORS, session.getAttribute(Attributes.USER_REG_ERRORS));
        session.removeAttribute(Attributes.USER_REG_ERRORS);
        request.getRequestDispatcher(UrlMapping.SIGNUP_JSP).forward(request, response);
    }
}
