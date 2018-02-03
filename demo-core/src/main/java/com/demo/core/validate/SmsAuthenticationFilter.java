package com.demo.core.validate;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zxdong on 2018/2/3.
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private String phoneNumberParameter = "phoneNumber";
    private boolean postOnly = true;

    public SmsAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/phone", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String phoneNumber = this.obtainPhoneNumber(request);
            if (phoneNumber == null) {
                phoneNumber = "";
            }

            phoneNumber = phoneNumber.trim();
            SmsAuthenticationToken authRequest = new SmsAuthenticationToken(phoneNumber);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected String obtainPhoneNumber(HttpServletRequest request) {
        return request.getParameter(this.phoneNumberParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getPhoneNumberParameter() {
        return this.phoneNumberParameter;
    }

    public void setPhoneNumberParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "phoneNumber parameter must not be empty or null");
        this.phoneNumberParameter = usernameParameter;
    }

}
