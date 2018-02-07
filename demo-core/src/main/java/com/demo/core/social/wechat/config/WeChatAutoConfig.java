package com.demo.core.social.wechat.config;

import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * Created by zxdong on 2018/2/7.
 */
public class WeChatAutoConfig extends SocialAutoConfigurerAdapter {
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return null;
    }
}
