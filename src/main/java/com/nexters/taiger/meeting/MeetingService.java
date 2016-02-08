package com.nexters.taiger.meeting;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexters.taiger.common.constant.UserSortType;
import com.nexters.taiger.common.util.DozerHelper;
import com.nexters.taiger.departure.DepartureEntity;
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
	
	@Autowired
	private DozerBeanMapper dozer;
	
	public void createMeeting(MeetingEntity meetingEntity){
		meetingRepository.save(meetingEntity);
	}
	
	
    public void deleteMeeting(MeetingEntity meetingEntity){
		 meetingRepository.delete(meetingEntity);
	 }
	
	public List<MeetingDto> getMeeting(UserSortType sortType,MeetingEntity meetingEntity){
		UserSortType sort=null;
		List<MeetingDto> meetingDto=null;
		ArrayList<MeetingEntity> meeting=null;
		if(sortType==sort.DEPARTURE){
			meeting=meetingRepository.findAllByDeparture_idEndDest_name(meetingEntity);
			//meetingDto=DozerHelper.map(dozer, meeting, MeetingDto.class);
		}else if(sortType==sort.approach){
			meeting=meetingRepository.findAllByDeparture_id(meetingEntity);
			//meetingDto=DozerHelper.map(dozer, meeting, MeetingDto.class);
			
		}
		
		
		
		meetingDto=DozerHelper.map(dozer, meeting, MeetingDto.class);
		return meetingDto;
	}




	
	
	
	
	

}
