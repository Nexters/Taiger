package com.nexters.taiger.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserEntity getUser(int id){
		return userRepository.findOne(id);
	}
}
