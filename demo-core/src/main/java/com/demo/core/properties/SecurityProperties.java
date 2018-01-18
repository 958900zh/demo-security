package com.demo.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 栋 on 2018/1/18.
 */
@ConfigurationProperties(prefix = "demo.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
