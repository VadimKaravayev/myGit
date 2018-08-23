package com.epam.preprod.karavayev.web.listener;

import com.epam.preprod.karavayev.captcha.Captcha;
import com.epam.preprod.karavayev.captcha.CaptchaCleaner;
import com.epam.preprod.karavayev.captcha.CaptchaGenerator;
import com.epam.preprod.karavayev.captcha.store.method.impl.AbstractStoreMethod;
import com.epam.preprod.karavayev.captcha.store.method.impl.CookieCaptchaStoreMethod;
import com.epam.preprod.karavayev.captcha.store.method.impl.HiddenFieldCaptchaStoreMethod;
import com.epam.preprod.karavayev.captcha.store.method.impl.SessionCaptchaStoreMethod;
import com.epam.preprod.karavayev.constant.Attributes;
import com.epam.preprod.karavayev.constant.ContextParam;
import com.epam.preprod.karavayev.db.dao.TransactionManager;
import com.epam.preprod.karavayev.db.dao.UserDao;
import com.epam.preprod.karavayev.db.dao.mysql.MysqlUserDaoImpl;
import com.epam.preprod.karavayev.service.UserService;
import com.epam.preprod.karavayev.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.*;

@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(AppContextListener.class);
    private ScheduledExecutorService scheduledExecutorService;
    private long timeSleep;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        UserDao userDao = new MysqlUserDaoImpl();
        TransactionManager transactionManager = new TransactionManager(getDataSource());
        UserService userService = new UserServiceImpl(userDao, transactionManager);
        Map<String, Captcha> captchaMap = new ConcurrentHashMap<>();
        servletContext.setAttribute(Attributes.CAPTCHA_MAP, captchaMap);

        timeSleep = Long.parseLong(servletContext.getInitParameter(ContextParam.CAPTCHA_EXPIRATION_TIME));
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new CaptchaCleaner(captchaMap), timeSleep, timeSleep, TimeUnit.SECONDS);

        CaptchaGenerator captchaGenerator = new CaptchaGenerator(timeSleep);
        servletContext.setAttribute(Attributes.CAPTCHA_GENERATOR, captchaGenerator);
        servletContext.setAttribute(Attributes.CAPTCHA_STORE_METHOD, getCaptchaStoreMethod(servletContext, captchaMap, (int)timeSleep));
        servletContext.setAttribute(Attributes.CONTEXT_USER_SERVICE, userService);
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduledExecutorService.shutdownNow();
    }

    private AbstractStoreMethod getCaptchaStoreMethod(ServletContext servletContext, Map<String, Captcha> captchaMap, int timeout) {
        String captchaStoreMethod = servletContext.getInitParameter(ContextParam.CAPTCHA_STORE_METHOD);
        servletContext.setAttribute(Attributes.STORE_METHOD_NAME, captchaStoreMethod);
        switch (captchaStoreMethod) {
            case ContextParam.SESSION:
                return new SessionCaptchaStoreMethod(captchaMap, timeout);
            case ContextParam.HIDDEN_FIELD:
                return new HiddenFieldCaptchaStoreMethod(captchaMap, timeout);
            case ContextParam.COOKIE:
                return new CookieCaptchaStoreMethod(captchaMap, timeout);
            default:
                servletContext.setAttribute(Attributes.STORE_METHOD_NAME, ContextParam.SESSION);
                return new SessionCaptchaStoreMethod(captchaMap, timeout);
        }
    }

    private DataSource getDataSource() {
        DataSource dataSource;
        try {
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/web_shop_db");
        } catch (NamingException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
