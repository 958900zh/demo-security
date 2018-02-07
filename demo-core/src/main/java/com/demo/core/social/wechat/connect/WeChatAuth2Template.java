package com.demo.core.social.wechat.connect;

import org.springframework.social.oauth2.OAuth2Template;

/**
 * Created by zxdong on 2018/2/7.
 */
public class WeChatAuth2Template extends OAuth2Template {

    public WeChatAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
    }
}
