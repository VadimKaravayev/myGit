package vk.vadim.karavayev.captcha.captcha_store_method;

import vk.vadim.karavayev.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaStoreMethod {

    void store(HttpServletRequest request, HttpServletResponse response, Captcha captcha);

    Captcha getCaptcha(HttpServletRequest request);
}
