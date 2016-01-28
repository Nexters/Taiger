package com.nexters.taiger.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Baek on 2016-01-28.
 */
@Slf4j
@Service
public class KakaoService {

    @Autowired
    private RestTemplate restTemplate;

    private <T> HttpEntity<T> prepareHeader(T param, String accessToken) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");

        HttpEntity<T> request = new HttpEntity<T>(param, headers);
        return request;
    }

    public Map<String, Object> me(String accessToken) {
        try {
            Map<String, Object> response = restTemplate.postForObject("https://kapi.kakao.com/v1/user/me", prepareHeader(null, accessToken), Map.class);
            return response;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public long signup(String accessToken) {
        try {
            Map response = restTemplate.postForObject("https://kapi.kakao.com/v1/user/signup", prepareHeader(null, accessToken), Map.class);
            return (Long) response.get("id");
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return -1;
    }

    public long signout(String accessToken) {
        try {
            Map response = restTemplate.postForObject("https://kapi.kakao.com/v1/user/logout", prepareHeader(null, accessToken), Map.class);
            return (Long) response.get("id");
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return -1;
    }



    public long isValidAccessToken(String accessToken) {
        try {
            Map response = restTemplate.postForObject("https://kapi.kakao.com/v1/user/access_token_info", prepareHeader(null, accessToken), Map.class);
            Long id = (Long) response.get("id");
            Long expiresInMillis = (Long) response.get("expiresInMillis");

            if(expiresInMillis <= 0) {
                throw new Exception("Expired Token");
            }
            return id;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return -1;
    }
}
