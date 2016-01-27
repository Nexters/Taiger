package com.nexters.taiger.user;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on Baek on 2016. 1. 23...
 */

@Slf4j
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value="/user/{id}",method = RequestMethod.POST)
	public String register(UserCondition condition){
		log.info("signup : " + condition.toString());
		
		UserEntity userEntity = new UserEntity(condition);
		userService.signup(userEntity);
		return "success";
	}

}
