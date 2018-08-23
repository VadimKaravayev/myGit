package com.epam.preprod.karavayev.util;

import com.epam.preprod.karavayev.captcha.Captcha;
import com.epam.preprod.karavayev.constant.HtmlFormParameters;
import com.epam.preprod.karavayev.constant.ViewMessages;
import com.epam.preprod.karavayev.dto.UserRegistrationDto;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class UserRegistrationDtoValidator {

    private static final Pattern NAME_PATTERN = Pattern.compile("^([A-Z][a-z]+)((\\s|-)[A-Z][a-z]+)?$");
    private static final Pattern
            EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    private static final Pattern
            PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}");

    public Map<String, String> validate(UserRegistrationDto dto, Captcha captcha) {
        Map<String, String> errors = new HashMap<>();

        if (!isMatchField(dto.getFirstName(), NAME_PATTERN)) {
            errors.put(HtmlFormParameters.REG_NAME, ViewMessages.WRONG_NAME_FORMAT);
        }

        if (!isMatchField(dto.getLastName(), NAME_PATTERN)) {
            errors.put(HtmlFormParameters.REG_LASTNAME, ViewMessages.WRONG_LASTNAME_FORMAT);
        }

        if (!isMatchField(dto.getEmail(), EMAIL_PATTERN)) {
            errors.put(HtmlFormParameters.REG_EMAIL, ViewMessages.WRONG_EMAIL_FORMAT);
        }

        if (!isMatchField(dto.getPassword(), PASSWORD_PATTERN)) {
            errors.put(HtmlFormParameters.REG_PASSWORD, ViewMessages.WRONG_PASSWORD_FORMAT);
        }

        if (!Objects.equals(dto.getPassword(), dto.getConfirmPassword())) {
            errors.put(HtmlFormParameters.REG_PASSWORD_CONFIRM, ViewMessages.WRONG_CONFIRMPASSWORD);
        }

        if (!checkCaptcha(captcha, dto.getCaptchaValue())) {
            errors.put(HtmlFormParameters.CAPTCHA_VALUE, ViewMessages.WRONG_CAPTCHA);
        }

        return errors;
    }

    private boolean isMatchField(String field, Pattern pattern) {
        return !StringUtils.isBlank(field) && pattern.matcher(field).matches();
    }

    private boolean checkCaptcha(Captcha captcha, String captchaValue) {
        if (Objects.isNull(captcha)) {
            return false;
        } else if (!Objects.equals(captcha.getValue(), captchaValue)) {
            return false;
        } else if (!captcha.getExpirationDate().isAfter(LocalDateTime.now())) {
            return false;
        }
        captcha.makeInvalid();
        return true;
    }

}
