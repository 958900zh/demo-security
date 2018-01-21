package com.demo.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 栋 on 2018/1/18.
 */
@ConfigurationProperties(prefix = "demo.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private SocialProperties social;

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
