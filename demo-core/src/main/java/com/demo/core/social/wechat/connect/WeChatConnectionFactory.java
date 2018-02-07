package com.demo.core.social.wechat.connect;

import com.demo.core.social.wechat.api.WeChat;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * Created by zxdong on 2018/2/7.
 */
public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChat> {

    public WeChatConnectionFactory(String providerId, OAuth2ServiceProvider<WeChat> serviceProvider, ApiAdapter<WeChat> apiAdapter) {
        super(providerId, serviceProvider, apiAdapter);
    }
}
