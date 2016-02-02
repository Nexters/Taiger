package com.nexters.taiger.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexters.taiger.user.UserEntity;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Service
public class MeetingService {
	@Autowired
	private MeetingRepository meetingRepository;
	
	@Autowired
	private MeetingCommentRepository meetingCommentRepository;
	
	public void createMeeting(MeetingEntity meetingEntity){
		meetingRepository.save(meetingEntity);
	}
	public void deleteMeeting(MeetingEntity meetingEntity){
		meetingRepository.delete(meetingEntity);
	}
	
	
	
	

}
