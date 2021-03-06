package com.demo.core.social.qq.connect;

import com.demo.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * Created by zxdong on 2018/1/20.
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
