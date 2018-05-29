package vk.vadim.karavayev.captcha;

import vk.vadim.karavayev.captcha.captcha_store_method.CaptchaStoreMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

public class CaptchaHandler {

    private CaptchaGenerator captchaGenerator;
    private CaptchaStoreMethod storeMethod;

    public CaptchaHandler(CaptchaGenerator captchaGenerator, CaptchaStoreMethod storeMethod) {
        this.captchaGenerator = captchaGenerator;
        this.storeMethod = storeMethod;
    }

    public Captcha generateCaptcha(HttpServletRequest request, HttpServletResponse response) {
        Captcha captcha = captchaGenerator.generateCaptcha();
        storeMethod.store(request, response, captcha);
        return captcha;
    }

    public boolean checkCaptcha(HttpServletRequest request, String verification) {
        Captcha captcha = storeMethod.getCaptcha(request);
        if (Objects.isNull(captcha)) {
            return false;
        } else if (!Objects.equals(captcha.getValue(), verification)) {
            return false;
        } else if (!captcha.getExpirationDate().isAfter(LocalDateTime.now())) {
            return false;
        }
        captcha.makeInvalid();
        return true;
    }
}
