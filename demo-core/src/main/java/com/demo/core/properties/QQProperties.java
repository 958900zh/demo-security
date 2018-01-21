package com.demo.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Created by zxdong on 2018/1/20.
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
