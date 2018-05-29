package vk.vadim.karavayev.captcha.captcha_store_method.impl;

import org.apache.commons.lang3.StringUtils;
import vk.vadim.karavayev.captcha.Captcha;
import vk.vadim.karavayev.captcha.captcha_store_method.CaptchaStoreMethod;
import vk.vadim.karavayev.constants.Attributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class CookieCaptchaStoreMethod implements CaptchaStoreMethod {

    @Override
    public void store(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        String uuid = UUID.randomUUID().toString();
        ((Map<String, Captcha>)request.getServletContext().getAttribute(Attributes.CAPTCHA)).put(uuid, captcha);
        response.addCookie(new Cookie(Attributes.CAPTCHA_ID, uuid));
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String uuid = StringUtils.EMPTY;
        if (Objects.nonNull(cookies)) {
            uuid = Arrays.stream(cookies).filter(cookie -> Objects.equals(cookie.getName(), Attributes.CAPTCHA_ID))
                    .map(Cookie::getValue).findFirst().orElse(StringUtils.EMPTY);
        }
        return ((Map<String, Captcha>)request.getServletContext().getAttribute(Attributes.CAPTCHA)).get(uuid);
    }
}
