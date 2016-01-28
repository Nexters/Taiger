package com.nexters.taiger.user;

import com.nexters.taiger.common.AuthUserDto;
import com.nexters.taiger.common.KakaoService;
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
	public AuthUserDto login(String accessToken, HttpSession session) throws InvalidAuthException {
		long kakaoId = kakaoService.isValidAccessToken(accessToken);
		AuthUserDto authUser = new AuthUserDto();
		authUser.setLoginDate(new Date());
		UserDto userDto = new UserDto();
		UserEntity userEntity = userService.getUserByKakaoId(String.valueOf(kakaoId));
		if(userEntity == null) {
			throw new InvalidAuthException();
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
		return true;
	}

	/**
	 * 가입/연동
	 * @param condition
	 * @return
	 * @throws InvalidAuthException
	 */
	@RequestMapping(value="/user/join",method = RequestMethod.POST)
	public String register(UserCondition condition) throws InvalidAuthException {
		log.info("signup : " + condition.toString());
		long kakaoId = kakaoService.isValidAccessToken(condition.getKakaoToken());
		if(kakaoId != -1) {
			UserEntity userEntity = userService.getUserByKakaoId(String.valueOf(kakaoId));
			Map<String, Object> me = kakaoService.me(condition.getKakaoToken());

			userEntity.setName((String) me.get("nickname"));
			userEntity.setKakaoToken(condition.getKakaoToken());
			userEntity.setKakaoId(String.valueOf(kakaoId));
			userService.signup(userEntity);
		} else {
			throw new InvalidAuthException();
		}
		UserEntity userEntity = new UserEntity(condition);
		userService.signup(userEntity);
		return "success";
	}

	/**
	 * 마이페이지조회
	 * @param id
	 * @return
     */
	@RequestMapping(value="/user/me", method = RequestMethod.GET)
	public UserDto getUser(@PathVariable int id){
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
	public UserDto saveMyPage(AuthUserDto authUser, UserDto userDto){
		UserEntity userEntity = userService.getUserByKakaoId(String.valueOf(authUser.getUserDto().getKakaoId()));
		DepartureEntity departureEntity = new DepartureEntity();
		departureEntity.setId(userDto.getPrimaryDeparture().getId());
		userEntity.setPrimaryDeparture(departureEntity);
		userEntity = userService.saveUser(userEntity);

		return dozer.map(userEntity, UserDto.class);
	}

}
