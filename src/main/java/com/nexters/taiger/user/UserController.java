package com.nexters.taiger.user;

import com.nexters.taiger.common.AuthUserDto;
import com.nexters.taiger.common.KakaoService;
import com.nexters.taiger.common.exception.BadAuthTrialException;
import com.nexters.taiger.common.exception.BadJoinTrialException;
import com.nexters.taiger.common.exception.InvalidAuthException;
import com.nexters.taiger.departure.DepartureEntity;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Slf4j
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private DozerBeanMapper dozer;

	@Autowired
	private KakaoService kakaoService;

	/**
	 * 로그인
	 * @param accessToken
	 * @param session
	 * @return
	 * @throws InvalidAuthException
     */
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	public AuthUserDto login(String accessToken, HttpSession session) throws BadAuthTrialException {
		long kakaoId = kakaoService.isValidAccessToken(accessToken);
		AuthUserDto authUser = new AuthUserDto();
		authUser.setLoginDate(new Date());
		UserDto userDto = new UserDto();
		UserEntity userEntity = userService.getUserByKakaoId(String.valueOf(kakaoId));
		if(userEntity == null) {
			throw new BadAuthTrialException();
		}

		if(!userEntity.getKakaoToken().equals(accessToken)) {
			userEntity.setKakaoToken(accessToken);
			userService.saveUser(userEntity);
		}

		dozer.map(userEntity, userDto);
		authUser.setUserDto(userDto);
		session.setAttribute("login", authUser);

		return authUser;
	}

	/**
	 * 로그아웃
	 */
	@RequestMapping(value="/user/logout", method=RequestMethod.POST)
	public boolean logout(HttpSession session) {
		session.setAttribute("login", null);
		return true;
	}

	/**
	 * 가입/연동
	 * @param condition
	 * @return kakaoId
	 * @throws InvalidAuthException
	 */
	@RequestMapping(value="/user/join",method = RequestMethod.POST)
	public String register(UserCondition userCondition) throws InvalidAuthException, BadJoinTrialException {
		log.info("signup : " + userCondition.toString());
		long kakaoId = kakaoService.isValidAccessToken(userCondition.getKakaoToken());
		if(kakaoId != -1) {
			UserEntity userEntity = userService.getUserByKakaoId(String.valueOf(kakaoId));
			Map<String, Object> me = kakaoService.me(userCondition.getKakaoToken());

			userEntity.setName((String) me.get("nickname"));
			userEntity.setKakaoToken(userCondition.getKakaoToken());
			userEntity.setKakaoId(String.valueOf(kakaoId));
			userService.signup(userEntity);
		} else {
			throw new BadJoinTrialException();
		}
		UserEntity userEntity = new UserEntity(userCondition);
		userService.signup(userEntity);
		return String.valueOf(kakaoId);
	}

	/**
	 * 마이페이지조회
	 * @return
     */
	@RequestMapping(value="/user/me", method = RequestMethod.GET)
	public UserDto getUser(AuthUserDto authUser){
		int id = authUser.getUserDto().getId();
		UserEntity user=userService.getUser(id);
		return dozer.map(user, UserDto.class);
	}

	/**
	 * 마이페이지저장
	 * @param authUser
	 * @param userDto
     * @return
     */
	@RequestMapping(value="/user/me", method = RequestMethod.PUT)
	public UserDto saveMyPage(AuthUserDto authUser, UserCondition userCondition){
		UserEntity userEntity = userService.getUserByKakaoId(String.valueOf(authUser.getUserDto().getKakaoId()));

		DepartureEntity departureEntity = new DepartureEntity();
		departureEntity.setId(userCondition.getPrimaryDepartureId());

		userEntity.setPrimaryDeparture(departureEntity);
		userEntity = userService.saveUser(userEntity);

		UserDto userDto = dozer.map(userEntity, UserDto.class);
		authUser.setUserDto(userDto);
		return userDto;
	}

}
