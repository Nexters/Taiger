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

    private static final String KAKAO_API_URL = "https://kapi.kakao.com/v1";

    private <T> HttpEntity<T> prepareHeader(T param, String accessToken) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");

        HttpEntity<T> request = new HttpEntity<T>(param, headers);
        return request;
    }

    /**
     * 카카오 자기정보 조회
     * @param accessToken
     * @return
     */
    public Map<String, Object> me(String accessToken) {
        try {
            Map<String, Object> response = restTemplate.postForObject(KAKAO_API_URL + "/user/me", prepareHeader(null, accessToken), Map.class);
            return response;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 카카오 앱 회원가입
     * @param accessToken
     * @return kakaoId
     */
    public long signup(String accessToken) {
        try {
            Map response = restTemplate.postForObject(KAKAO_API_URL + "/user/signup", prepareHeader(null, accessToken), Map.class);
            return (Long) response.get("id");
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return -1;
    }

    /**
     * 카카오 앱 회원탈퇴
     * @param accessToken
     * @return kakaoId
     */
    public long signout(String accessToken) {
        try {
            Map response = restTemplate.postForObject(KAKAO_API_URL + "/user/logout", prepareHeader(null, accessToken), Map.class);
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
