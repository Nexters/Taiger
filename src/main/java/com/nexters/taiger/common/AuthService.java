package com.nexters.taiger.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.taiger.common.exception.BadAuthTrialException;
import com.nexters.taiger.common.exception.BadJoinTrialException;
import com.nexters.taiger.common.exception.CryptoException;
import com.nexters.taiger.common.util.AES256;
import com.nexters.taiger.user.UserCondition;
import com.nexters.taiger.user.UserEntity;
import com.nexters.taiger.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Service
public class AuthService {

	@Value("${crypto.secret}")
	private String secretKey;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private KakaoService kakaoService;

	@Autowired
	private ObjectMapper objectMapper;

	public static final ConcurrentHashMap<String, AccessTokenDto> tokenCache = new ConcurrentHashMap<String, AccessTokenDto>();

	/**
	 * 회원가입 수행
	 * @param condition
	 */
	public void register(UserCondition condition) throws BadJoinTrialException {
		long kakaoId = kakaoService.isValidAccessToken(condition.getKakaoToken());
		if(kakaoId != -1) {
			UserEntity userEntity = new UserEntity();
			userEntity.setName(condition.getName());
			userEntity.setKakaoToken(condition.getKakaoToken());
			userEntity.setKakaoId(String.valueOf(kakaoId));
			userRepository.save(userEntity);
		} else {
			throw new BadJoinTrialException("Kakao AccessToken is invalid.");
		}
	}

	/**
	 * 로그인 로직 수행
	 * @param kakaoToken
	 * @param ipAddress
	 * @return
	 * @throws BadAuthTrialException
     */
	public String login(String kakaoToken, String ipAddress) throws BadAuthTrialException {
		long kakaoId = kakaoService.isValidAccessToken(kakaoToken);
		UserEntity userEntity = userRepository.findByKakaoId(String.valueOf(kakaoId));

		try {
			if(kakaoId == -1) {
				throw new BadAuthTrialException("Invalid Kakao Id");
			}

			if(userEntity == null) {
				throw new BadAuthTrialException("Invalid User");
			}

			// 토큰 갱신시 DB 동기화
			if(!userEntity.getKakaoToken().equals(kakaoToken)) {
				userEntity.setKakaoToken(kakaoToken);
				userRepository.save(userEntity);
			}

			return makeAccessToken(userEntity, ipAddress);
		} catch (Exception e) {
			throw new BadAuthTrialException(e.getMessage());
		}
	}

	/**
	 * 로그아웃
	 * @param accessToken
     */
	public void logout(String accessToken) {
		tokenCache.remove(accessToken);
	}

	/**
	 * AccessToken 생성
	 * @param userEntity
	 * @param ipAddress
	 * @return
	 * @throws JsonProcessingException
	 * @throws CryptoException
     */
	private String makeAccessToken(UserEntity userEntity, String ipAddress) throws JsonProcessingException, CryptoException {
		AccessTokenDto accessTokenDto = new AccessTokenDto(userEntity.getId(), userEntity.getKakaoId(), userEntity.getKakaoToken(), userEntity.getName(), ipAddress, new Date());
		String accessTokenJson = objectMapper.writeValueAsString(accessTokenDto);
		String accessToken = AES256.decode(secretKey, accessTokenJson);
		tokenCache.put(accessToken, accessTokenDto);
		return accessToken;
	}

	/**
	 * AccessToken 해석
	 * @param accessToken
	 * @return
	 * @throws CryptoException
	 * @throws IOException
     */
	public AccessTokenDto toAccessTokenDto(String accessToken) throws CryptoException, IOException {
		if(tokenCache.containsKey(accessToken)) {
			return tokenCache.get(accessToken);
		}

		String accessTokenJson = AES256.decode(secretKey, accessToken);
		AccessTokenDto accessTokenDto = objectMapper.readValue(accessTokenJson, AccessTokenDto.class);
		return accessTokenDto;
	}

}
