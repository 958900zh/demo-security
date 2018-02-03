package com.demo.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by zxdong on 2018/2/4.
 */
public class MySpringSocialConfig extends SpringSocialConfigurer {

    private String processUrl;

    public MySpringSocialConfig(String processUrl) {
        this.processUrl = processUrl;
    }

    @Override
    protected <T> T postProcess(T object) {

        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(processUrl);
        return (T) filter;
    }
}
