package com.demo.core.validate.code;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class SmsValidateCodeFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SMS_VALIDATE_CODE_PHONE = "phone";
    private String phoneParameter = "phone";
    private boolean postOnly = true;

    public SmsValidateCodeFilter() {
        super(new AntPathRequestMatcher("/authentication/sms", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String phone = this.obtainPhone(request);
            if (phone == null) {
                phone = "";
            }

            phone = phone.trim();
            SmsValidateCodeAuthenticationToken authRequest = new SmsValidateCodeAuthenticationToken(phone);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected String obtainPhone(HttpServletRequest request) {
        return request.getParameter(this.phoneParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsValidateCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getPhoneParameter() {
        return this.phoneParameter;
    }

    public void setPhoneParameter(String phoneParameter) {
        Assert.hasText(phoneParameter, "phone parameter must not be empty or null");
        this.phoneParameter = phoneParameter;
    }

}
