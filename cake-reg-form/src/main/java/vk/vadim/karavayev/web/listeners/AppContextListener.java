package vk.vadim.karavayev.web.listeners;

import vk.vadim.karavayev.captcha.CaptchaCleaner;
import vk.vadim.karavayev.captcha.CaptchaGenerator;
import vk.vadim.karavayev.captcha.CaptchaHandler;
import vk.vadim.karavayev.captcha.captcha_store_method.CaptchaStoreMethod;
import vk.vadim.karavayev.captcha.captcha_store_method.impl.CookieCaptchaStoreMethod;
import vk.vadim.karavayev.captcha.captcha_store_method.impl.HiddenFieldCaptchaStoreMethod;
import vk.vadim.karavayev.captcha.captcha_store_method.impl.SessionCaptchaStoreMethod;
import vk.vadim.karavayev.constants.Attributes;
import vk.vadim.karavayev.constants.ContextParam;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebListener
public class AppContextListener implements ServletContextListener {

    private ExecutorService executor;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        if (!Objects.equals(servletContext.getInitParameter(ContextParam.CAPTCHA_STORE_METHOD), ContextParam.SESSION)) {
            servletContext.setAttribute(Attributes.CAPTCHA, new ConcurrentHashMap<>());
            executor = Executors.newSingleThreadExecutor();
            executor.execute(new CaptchaCleaner(servletContext, 5));
        }

        CaptchaGenerator captchaGenerator =
                new CaptchaGenerator(Long.parseLong(servletContext.getInitParameter(ContextParam.EXPIRATION_CAPTCHA)));

        servletContext.setAttribute(Attributes.CONTEXT_CAPTCHA_HANDLER,
                new CaptchaHandler(captchaGenerator, getCaptchaStoreMethod(servletContext)));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (Objects.nonNull(executor)) {
            executor.shutdownNow();
        }
    }

    private CaptchaStoreMethod getCaptchaStoreMethod(ServletContext sc) {
        Map<String, CaptchaStoreMethod> methodMap = new HashMap<>();
        methodMap.put(ContextParam.SESSION, new SessionCaptchaStoreMethod());
        methodMap.put(ContextParam.HIDDEN_FIELD, new HiddenFieldCaptchaStoreMethod());
        methodMap.put(ContextParam.COOKIE, new CookieCaptchaStoreMethod());
        return methodMap.get(sc.getInitParameter(ContextParam.CAPTCHA_STORE_METHOD));
    }
}
