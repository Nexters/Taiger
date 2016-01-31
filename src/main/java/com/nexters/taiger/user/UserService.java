package com.nexters.taiger.user;

import com.nexters.taiger.departure.DepartureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 사용자 조회
	 * @param id
	 * @return
     */
	public UserEntity getUser(int id){
		return userRepository.findOne(id);
	}


	/**
	 * 마이페이지 저장
	 * @param userDto
     */
	public void saveUser(UserDto userDto) {
		UserEntity userEntity = userRepository.findByKakaoId(String.valueOf(userDto.getKakaoId()));

		DepartureEntity departureEntity = new DepartureEntity();
		departureEntity.setId(userDto.getPrimaryDepartureId());

		userEntity.setPrimaryDeparture(departureEntity);
		userRepository.save(userEntity);
	}
}
