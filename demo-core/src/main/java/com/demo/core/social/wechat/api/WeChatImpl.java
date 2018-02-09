package com.demo.core.social.wechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by zxdong on 2018/2/7.
 */
public class WeChatImpl extends AbstractOAuth2ApiBinding implements WeChat {

    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";
    private ObjectMapper objectMapper = new ObjectMapper();


    public WeChatImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }


    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }


    public WeChatUserInfo getInfo(String openId) {
        String url = URL_GET_USER_INFO + openId;
        String response = getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(response, "errcode")) {
            return null;
        }
        WeChatUserInfo profile = null;
        try {
            profile = objectMapper.readValue(response, WeChatUserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profile;
    }

    @Override
    public WeChatUserInfo getInfo() {
        return null;
    }
}
