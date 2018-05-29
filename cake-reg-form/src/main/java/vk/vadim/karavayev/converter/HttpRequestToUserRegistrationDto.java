package vk.vadim.karavayev.converter;


import org.apache.commons.lang3.StringUtils;
import vk.vadim.karavayev.constants.SignupFormData;
import vk.vadim.karavayev.dto.UserRegistrationDto;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestToUserRegistrationDto implements Converter<HttpServletRequest, UserRegistrationDto> {
    @Override
    public UserRegistrationDto convert(HttpServletRequest httpRequest) {

        return UserRegistrationDto.getBuilder()
                .setFirstName(StringUtils.defaultString(httpRequest.getParameter(SignupFormData.FIRST_NAME)))
                .setLastName(StringUtils.defaultString(httpRequest.getParameter(SignupFormData.LAST_NAME)))
                .setEmail(StringUtils.defaultString(httpRequest.getParameter(SignupFormData.EMAIL)))
                .setPassword(StringUtils.defaultString(httpRequest.getParameter(SignupFormData.PASSWORD)))
                .setPasswordRepeat(StringUtils.defaultString(httpRequest.getParameter(SignupFormData.PASSWORD_REPEAT)))
                .setRemember(httpRequest.getParameter(SignupFormData.REMEMBER))
                .setVerification(StringUtils.defaultString(httpRequest.getParameter(SignupFormData.VERIFICATION)))
                .build();
    }
}
