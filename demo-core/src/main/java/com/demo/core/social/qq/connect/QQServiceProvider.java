package com.demo.core.social.qq.connect;

import com.demo.core.social.qq.api.QQ;
import com.demo.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * Created by zxdong on 2018/1/20.
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    //将用户导向的认证服务器的地址
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    //用授权码去申请token的地址
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
    private String appId;

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
