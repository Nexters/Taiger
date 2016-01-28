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

	public UserEntity getUserByKakaoId(String id){
		return userRepository.findByKakaoId(id);
	}

	public void signup(UserEntity userEntity){
		userRepository.save(userEntity);
	}

	public UserEntity saveUser(UserEntity userEntity){
		userRepository.save(userEntity);
		return userEntity;
	}

}
