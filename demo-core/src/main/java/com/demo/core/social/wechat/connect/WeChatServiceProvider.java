package com.demo.core.social.wechat.connect;

import com.demo.core.social.wechat.api.WeChat;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

/**
 * Created by zxdong on 2018/2/7.
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {

    public WeChatServiceProvider(OAuth2Operations oauth2Operations) {
        super(oauth2Operations);
    }

    @Override
    public WeChat getApi(String s) {
        return null;
    }
}
