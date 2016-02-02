package com.nexters.taiger.meeting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nexters.taiger.common.constant.UserSortType;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Repository
public interface MeetingRepository extends CrudRepository<MeetingEntity, Integer> {

	//List<MeetingEntity> findAllOrderByCreatedAtDesc(MeetingEntity createdAt,UserSortType sortType);
	
	//List<MeetingEntity> findAllOrderByCreatedAtAndDesc(UserSortType sortType);
	
	List<MeetingEntity> findAllOrderByCreatedAtDesc(MeetingEntity createAt);
}
