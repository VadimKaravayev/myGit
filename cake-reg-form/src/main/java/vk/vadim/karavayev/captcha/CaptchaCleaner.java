package vk.vadim.karavayev.captcha;

import org.apache.log4j.Logger;
import vk.vadim.karavayev.constants.Attributes;

import javax.servlet.ServletContext;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CaptchaCleaner implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(CaptchaCleaner.class);

    private ServletContext servletContext;
    private long timeSleep;

    public CaptchaCleaner(ServletContext servletContext, long timeSleep) {
        this.servletContext = servletContext;
        this.timeSleep = timeSleep;
    }

    @Override
    public void run() {
        while (true) {
             Map<String, Captcha> captchaMap = (Map<String, Captcha>) servletContext.getAttribute(Attributes.CAPTCHA);
             captchaMap.entrySet().removeIf(entry-> entry.getValue().getExpirationDate().isBefore(LocalDateTime.now()));
            try {
                TimeUnit.MINUTES.sleep(timeSleep);
            } catch (InterruptedException e) {
                LOGGER.error("Cleaner was interrupted.", e);
            }
        }
    }
}
