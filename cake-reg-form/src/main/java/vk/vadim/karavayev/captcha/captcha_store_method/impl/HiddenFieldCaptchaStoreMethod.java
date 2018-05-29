package vk.vadim.karavayev.captcha.captcha_store_method.impl;

import org.apache.commons.lang3.StringUtils;
import vk.vadim.karavayev.captcha.Captcha;
import vk.vadim.karavayev.captcha.captcha_store_method.CaptchaStoreMethod;
import vk.vadim.karavayev.constants.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class HiddenFieldCaptchaStoreMethod implements CaptchaStoreMethod {
    @Override
    public void store(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        String uuid = request.getParameter(Attributes.CAPTCHA_ID);
        if (Objects.nonNull(uuid)) {
            ((Map<String, Captcha>) request.getServletContext().getAttribute(Attributes.CAPTCHA)).put(uuid, captcha);
        }
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        String uuid = request.getParameter(Attributes.CAPTCHA_ID);
        if (Objects.isNull(uuid)) {
            uuid = StringUtils.EMPTY;
        }
        return ((Map<String, Captcha>) request.getServletContext().getAttribute(Attributes.CAPTCHA)).get(uuid);
    }
}
