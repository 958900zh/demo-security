package com.demo.core.validate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxdong on 2018/1/21.
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Map<String, String> urls = new HashMap<>();

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    public ValidateCodeFilter() {
        urls.put("/authentication/phone", "sms");
        urls.put("/authentication/form", "image");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String type = getValidateCodeType(request);
        if (StringUtils.isNotBlank(type)) {
            logger.info("校验" + type + "的验证码");
            try {
                validateCodeProcessorHolder.findValidateProcessor(type).validate(new ServletWebRequest(request, response), type);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getValidateCodeType(HttpServletRequest request) {
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            for (String url : urls.keySet()) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    return urls.get(url);
                }
            }
        }
        return null;
    }

//    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
//        this.authenticationFailureHandler = authenticationFailureHandler;
//    }
}
