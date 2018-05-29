package vk.vadim.karavayev.tag;

import vk.vadim.karavayev.constants.ContextParam;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class CaptchaTag extends SimpleTagSupport {

    private static final String url = "captcha?captchaId=";
    private static final String name = "captchaId";

    @Override
    public void doTag() throws IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        String uuid = UUID.randomUUID().toString();
        StringBuilder stringBuilder = new StringBuilder().append("<img src=\"").append(url).append(uuid);
        stringBuilder.append("\"/>");
        if (Objects.equals((((PageContext) getJspContext()).getServletContext()
                .getInitParameter(ContextParam.CAPTCHA_STORE_METHOD)), ContextParam.HIDDEN_FIELD)) {
            stringBuilder.append("<input type=\"hidden\" name=\"")
            .append(name)
            .append("\" value=\"")
            .append(uuid)
            .append("\"/>");
        }
        out.println(stringBuilder.toString());
    }
}
