package com.nexters.taiger.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nexters.taiger.user.UserEntity;

import org.springframework.ui.Model;
/**
 * Created on Baek on 2016. 1. 23...
 */
@RestController
public class MeetingController {
	@Autowired
	private MeetingService meetingService;
	
	@RequestMapping(value="/meeting",method =RequestMethod.POST)
	public void createMeeting(@RequestBody MeetingEntity meetingEntity){
	meetingService.createMeeting(meetingEntity);
	}
	
	
	
	

}
