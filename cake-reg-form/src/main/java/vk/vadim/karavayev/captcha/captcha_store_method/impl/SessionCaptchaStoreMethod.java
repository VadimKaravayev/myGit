package vk.vadim.karavayev.captcha.captcha_store_method.impl;

import vk.vadim.karavayev.captcha.Captcha;
import vk.vadim.karavayev.captcha.captcha_store_method.CaptchaStoreMethod;
import vk.vadim.karavayev.constants.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionCaptchaStoreMethod implements CaptchaStoreMethod {

    @Override
    public void store(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        request.getSession().setAttribute(Attributes.CAPTCHA, captcha);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        Captcha captcha = (Captcha) request.getSession().getAttribute(Attributes.CAPTCHA);
        request.getSession().removeAttribute(Attributes.CAPTCHA);
        return captcha;
    }
}
