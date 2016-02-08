package com.nexters.taiger.meeting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Repository
public interface MeetingCommentRepository extends CrudRepository<MeetingCommentEntity, Integer> {

	
	List<MeetingCommentEntity> findAllByMeetingId(int meetingId);
	
}