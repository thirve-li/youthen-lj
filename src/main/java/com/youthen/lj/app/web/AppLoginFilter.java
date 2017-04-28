package com.youthen.lj.app.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import com.youthen.framework.common.StringUtils;
import com.youthen.lj.app.bean.Result;
import com.youthen.lj.app.service.LjAppService;
import com.youthen.lj.constant.AppConfig;
import com.youthen.master.service.dto.LoginUserDto;

public class AppLoginFilter implements Filter {

    @Autowired
    LjAppService ljAppService;

    private static Logger logger = Logger.getLogger(AppLoginFilter.class.getName());

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res,
            final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        String token = "";
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                final String cookieName = cookie.getName();
                if (!StringUtils.isEmpty(cookieName) && cookieName.equalsIgnoreCase(AppConfig.MANAGER_MEMORY)) {
                    token = cookie.getValue();
                }
            }
        }

        if (StringUtils.isNotEmpty(token)) {
            final ServletContext sc = request.getSession().getServletContext();
            final XmlWebApplicationContext cxt =
                    (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
            if (cxt != null && cxt.getBean("ljAppService") != null) {
                if (this.ljAppService == null) {
                    this.ljAppService = (LjAppService) cxt.getBean("ljAppService");
                }

                final Result result = this.ljAppService.isValidToken(request, response);
                final Object obj = result.getResultObject();
                if (obj != null) {
                    final LoginUserDto userDto = (LoginUserDto) obj;
                    final String mobile = userDto.getMobile();
                    if (this.ljAppService.canAddScore(mobile, AppConfig.ACTION_LOGIN)) {// 登录加积分
                        logger.info("=====================>自动登录 登录加积分");
                        this.ljAppService.addScore(mobile, AppConfig.ACTION_LOGIN, AppConfig.SCORE_LOGIN);
                    }
                }

            }
        }

        chain.doFilter(req, res);
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */

    @Override
    public void destroy() {
    }

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */

    @Override
    public void init(final FilterConfig aArg0) throws ServletException {
    }
}
