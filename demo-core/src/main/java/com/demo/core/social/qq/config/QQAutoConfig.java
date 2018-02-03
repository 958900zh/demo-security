package com.demo.core.social.qq.config;

import com.demo.core.properties.QQProperties;
import com.demo.core.properties.SecurityProperties;
import com.demo.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * Created by zxdong on 2018/1/20.
 */
@Configuration
//当配置文件中配置了name为app_id的属性，以下配置才会生效
@ConditionalOnProperty(prefix = "demo.security.social.qq", name = "app_id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties properties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = properties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
    }
}
