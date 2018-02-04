package com.demo.core.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * Created by zxdong on 2018/2/4.
 */
public class QQOAuth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        /*
         * 通过Authorization Code获取Access Token，发请求的时候，必须包含5个参数
         * SpringSecurity根据 useParametersForClientAuthentication 的值来决定发请求时是否带上参数，默认是false
         */
        setUseParametersForClientAuthentication(true);
    }

    /**
     * 通过Authorization Code获取Access Token，默认处理json格式，QQ返回的是以下格式
     * access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String resultStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        logger.info("获取Access Token的响应为:" + resultStr);
        String[] params = resultStr.split("&");
        String accessToken = StringUtils.substringAfter(params[0], "=");
        Long expiresIn = Long.valueOf(StringUtils.substringAfter(params[1], "="));
        String refreshToken = StringUtils.substringAfter(params[2], "=");
        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }

    //能够处理 text/html 的 contentType
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate template = super.createRestTemplate();
        template.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return template;
    }
}
