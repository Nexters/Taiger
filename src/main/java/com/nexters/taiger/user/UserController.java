package com.nexters.taiger.user;

import com.nexters.taiger.common.AccessTokenDto;
import com.nexters.taiger.common.AuthResultDto;
import com.nexters.taiger.common.AuthService;
import com.nexters.taiger.common.exception.BadAuthTrialException;
import com.nexters.taiger.common.exception.BadJoinTrialException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
	private AuthService authService;

	/**
	 * 로그인
	 * @param kakaoToken
	 * @param request
	 * @return
	 * @throws BadAuthTrialException
     */
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	public AuthResultDto login(String kakaoToken, HttpServletRequest request) throws BadAuthTrialException {
		String ipAddress = request.getRemoteAddr();
		String accessToken = authService.login(kakaoToken, ipAddress);
		return new AuthResultDto(accessToken, ipAddress, new Date());
	}

	/**
	 * 로그아웃
	 */
	@RequestMapping(value="/user/logout", method=RequestMethod.POST)
	public void revmoeSession(@RequestHeader("AccessToken") String accessToken, AccessTokenDto accessTokenDto) {
		authService.logout(accessToken);
	}

	/**
	 * 회원가입
	 * @param condition
	 * @param request
	 * @return
	 * @throws BadJoinTrialException
	 * @throws BadAuthTrialException
     */
	@RequestMapping(value="/user/register",method = RequestMethod.POST)
	public AuthResultDto registUser(@RequestParam UserCondition condition, HttpServletRequest request) throws BadJoinTrialException, BadAuthTrialException {
		String ipAddress = request.getRemoteAddr();
		authService.register(condition);
		String accessToken = authService.login(condition.getKakaoToken(), ipAddress);
		return new AuthResultDto(accessToken, ipAddress, new Date());
	}

	/**
	 * 마이페이지조회
	 * @return
     */
	@RequestMapping(value="/user/me", method = RequestMethod.GET)
	public UserDto getUser(AccessTokenDto authUser){
		int id = authUser.getId();
		UserEntity user = userService.getUser(id);
		return dozer.map(user, UserDto.class);
	}

	/**
	 * 마이페이지저장
	 * @param accessTokenDto
	 * @param userDto
     * @return
     */
	@RequestMapping(value="/user/me", method = RequestMethod.PUT)
	public void saveUser(AccessTokenDto accessTokenDto, @RequestBody UserDto userDto){
		userService.saveUser(userDto);
	}

}
