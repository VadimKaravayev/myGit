package vk.vadim.karavayev.web.servlets;

import org.apache.commons.lang3.StringUtils;
import vk.vadim.karavayev.captcha.CaptchaHandler;
import vk.vadim.karavayev.constants.Attributes;
import vk.vadim.karavayev.constants.ErrorMessage;
import vk.vadim.karavayev.constants.SignupFormData;
import vk.vadim.karavayev.converter.HttpRequestToUserRegistrationDto;
import vk.vadim.karavayev.converter.UserSignupDtoToUser;
import vk.vadim.karavayev.dto.UserRegistrationDto;
import vk.vadim.karavayev.service.UserService;
import vk.vadim.karavayev.service.impl.UserServiceImpl;
import vk.vadim.karavayev.validator.UserRegistrationDtoValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private HttpRequestToUserRegistrationDto dtoConverter;
    private UserRegistrationDtoValidator validator;
    private CaptchaHandler captchaHandler;
    private UserService userService;
    private UserSignupDtoToUser userConverter;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistrationDto userDto = dtoConverter.convert(req);
        Map<String, String> errors = validator.validate(userDto);
        if (!captchaHandler.checkCaptcha(req, userDto.getVerification())) {
            errors.put(SignupFormData.VERIFICATION, ErrorMessage.WRONG_VERIFICATION);
        }
        if (errors.isEmpty()) {
            if (!userService.register(userConverter.convert(userDto))) {
                errors.put(SignupFormData.EMAIL, ErrorMessage.USER_ALREADY_REGISTERED);
            } else {
                userDto = null;
                errors = null;
            }
        }
        if (Objects.nonNull(userDto)) {
            makeInsensitive(userDto);
        }
        HttpSession session = req.getSession();
        session.setAttribute(Attributes.USER_REG_DTO, userDto);
        session.setAttribute(Attributes.USER_REG_ERRORS, errors);
        resp.sendRedirect("register");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute(Attributes.USER_REG_DTO, session.getAttribute(Attributes.USER_REG_DTO));
        session.removeAttribute(Attributes.USER_REG_DTO);
        req.setAttribute(Attributes.USER_REG_ERRORS, session.getAttribute(Attributes.USER_REG_ERRORS));
        session.removeAttribute(Attributes.USER_REG_ERRORS);
        req.getRequestDispatcher("home").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        dtoConverter = new HttpRequestToUserRegistrationDto();
        validator = new UserRegistrationDtoValidator();
        captchaHandler = (CaptchaHandler) config.getServletContext().getAttribute(Attributes.CONTEXT_CAPTCHA_HANDLER);
        userService = new UserServiceImpl(new ArrayList<>());
        userConverter = new UserSignupDtoToUser();
    }

    private void makeInsensitive(UserRegistrationDto dto) {
        dto.setPassword(StringUtils.EMPTY);
        dto.setPasswordRepeat(StringUtils.EMPTY);
        dto.setVerification(StringUtils.EMPTY);
    }
}
