package com.nexters.taiger.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Service
public class MeetingService {
	@Autowired
	private MeetingRepository meetingRepository;
	
	@Autowired
	private MeetingCommentRepository meetingCommentRepository;

}
