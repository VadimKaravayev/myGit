package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.constant.HtmlFormParameters;
import com.epam.preprod.karavayev.dto.UserRegistrationDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestToUserDto implements Converter<HttpServletRequest, UserRegistrationDto> {

    @Override
    public UserRegistrationDto convert(HttpServletRequest httpRequest) {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setFirstName(StringUtils.defaultString(httpRequest.getParameter(HtmlFormParameters.REG_NAME)));
        dto.setLastName(StringUtils.defaultString(httpRequest.getParameter(HtmlFormParameters.REG_LASTNAME)));
        dto.setEmail(StringUtils.defaultString(httpRequest.getParameter(HtmlFormParameters.REG_EMAIL)));
        dto.setPassword(StringUtils.defaultString(httpRequest.getParameter(HtmlFormParameters.REG_PASSWORD)));
        dto.setConfirmPassword(StringUtils.defaultString(httpRequest.getParameter(HtmlFormParameters.REG_PASSWORD_CONFIRM)));
        dto.setCaptchaValue(StringUtils.defaultString(httpRequest.getParameter(HtmlFormParameters.CAPTCHA_VALUE)));
        dto.setSubscription(httpRequest.getParameter(HtmlFormParameters.REG_FORM_SUBSCRIPTION));

        return dto;
    }
}
