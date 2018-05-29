package vk.vadim.karavayev.web.servlets;

import vk.vadim.karavayev.captcha.CaptchaHandler;
import vk.vadim.karavayev.constants.Attributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    private CaptchaHandler captchaHandler;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/png");
        ServletOutputStream outputStream = resp.getOutputStream();
        ImageIO.write(captchaHandler.generateCaptcha(req, resp).getImage(), "png", outputStream);
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        captchaHandler = (CaptchaHandler) servletConfig.getServletContext()
                .getAttribute(Attributes.CONTEXT_CAPTCHA_HANDLER);
    }
}
