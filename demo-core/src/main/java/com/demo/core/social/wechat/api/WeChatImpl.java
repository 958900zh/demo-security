package com.demo.core.social.wechat.api;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 * Created by zxdong on 2018/2/7.
 */
public class WeChatImpl extends AbstractOAuth2ApiBinding implements WeChat {
    @Override
    public WeChatUserInfo getInfo() {
        return null;
    }
}
