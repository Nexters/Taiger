package com.nexters.taiger.meeting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexters.taiger.common.constant.UserSortType;
import com.nexters.taiger.common.exception.MeetingRoomFullException;
import com.nexters.taiger.common.util.DozerHelper;
import com.nexters.taiger.departure.DepartureEntity;
import com.nexters.taiger.user.UserDto;
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
	
	public void createMeeting(MeetingDto meetingDto){
		
		MeetingEntity meetingEntity = dozer.map(meetingDto, MeetingEntity.class);
		
		DepartureEntity departure=new DepartureEntity();
		departure.setId(meetingDto.getDepartureId());
		
		UserEntity user=new UserEntity();
		user.setId(meetingDto.getCreateUserId());
		
		meetingEntity.setDeparture(departure);
		meetingEntity.setUser(user);
		meetingDto= dozer.map(meetingEntity, MeetingDto.class);
		meetingRepository.save(meetingEntity);
	}
	
    public void deleteMeeting(MeetingEntity meetingEntity){
		 meetingRepository.delete(meetingEntity);
	 }
	
    
    



	public List<MeetingDto> getMeeting(UserSortType sortType,MeetingEntity meetingEntity){
		UserSortType sort=null;
		List<MeetingDto> meetingDto=null;
		List<MeetingEntity> meeting=null;
		if(sortType==sort.DEPARTURE){
			List<MeetingSearch>meetingSearch=meetingRepository.findAllByDepartureIdAndDestName(meetingEntity.getDeparture().getId(),meetingEntity.getDestName());
			
			
			
			meetingDto=DozerHelper.map(dozer, meetingSearch, MeetingDto.class);
		}else if(sortType==sort.RECENTLY){

			meeting=meetingRepository.findAllByDepartureId(meetingEntity.getDeparture().getId());
			meetingDto=DozerHelper.map(dozer, meeting, MeetingDto.class);

			meeting=meetingRepository.findAllByDepartureId(meetingEntity.getDeparture().getId());

			//meetingDto=DozerHelper.map(dozer, meeting, MeetingDto.class);

			
		}
		
		

		return meetingDto;

	}




	
	
	public MeetingDto enterMeeting(int meetingId){
		
		
		MeetingEntity meetingEntity=meetingRepository.findOne(meetingId);
		MeetingDto meetingDto=dozer.map(meetingEntity, MeetingDto.class);
		meetingDto.setCreateUserId(meetingEntity.getUser().getId());
		meetingDto.setDepartureId(meetingEntity.getDeparture().getId());
		
		
		return meetingDto;
	}
	
	public void joinMeetingUser(int userId, int meetingId) throws MeetingRoomFullException{
		
		MeetingEntity meetingEntity=meetingRepository.findOne(meetingId);
		
		if(meetingEntity.getMaxUser() <= meetingEntity.getUsers().size()){
			throw new MeetingRoomFullException("Meeting room is full");
		}
		
		UserEntity user =new UserEntity();
		user.setId(userId);
		List<UserEntity> users=meetingEntity.getUsers();
		users.add(user);
		
		meetingEntity.setUsers(users);
		
		meetingRepository.save(meetingEntity);
		
		
	}
	
	public List<UserDto> getMeetingUsers(int meetingId){
		
		
		MeetingEntity meetingEntity=meetingRepository.findOne(meetingId);
		List<UserEntity> users=meetingEntity.getUsers();
		List<UserDto> list=DozerHelper.map(dozer, users, UserDto.class);
		
		
		
		
		
		return list;
	}
	
	public void cancelMeetingUser(int userId,int meetingId){
		
		MeetingEntity meetingEntity=meetingRepository.findOne(meetingId);
		List<UserEntity> users=meetingEntity.getUsers();
		for(int i=0;i<users.size();i++){
			if(users.get(i).getId()==userId){
				users.remove(i);
				break;
			}
		}
		
		meetingRepository.save(meetingEntity);
	}
	
	public List<MeetingCommentDto> getMeetingComments(int meetingId){
		
		List<MeetingCommentEntity> meetingCommentList=meetingCommentRepository.findAllByMeetingId(meetingId);
		List<MeetingCommentDto> meetingCommentDtoList=new ArrayList<>();
		for(MeetingCommentEntity value : meetingCommentList){
			MeetingCommentDto meetingComment=new MeetingCommentDto();
			meetingComment.setId(value.getId());
			meetingComment.setMeetingId(value.getMeeting().getId());
			meetingComment.setContent(value.getContent());
			meetingComment.setUserId(value.getUser().getId());
			meetingComment.setCreatedAt(value.getCreatedAt());
			meetingCommentDtoList.add(meetingComment);
		}
		return meetingCommentDtoList;
	}
	
	public List<MeetingCommentDto> saveComment(int meetingId,MeetingCommentEntity meetingCommentEntity){
		
		meetingCommentRepository.save(meetingCommentEntity);
		
		return getMeetingComments(meetingId);
	}
	
	public List<MeetingCommentDto> deleteMeetingComments(int commentId,int meetingId){
		
		MeetingCommentEntity meetingCommentEntity=new MeetingCommentEntity();
		meetingCommentEntity.setId(commentId);
		
		MeetingEntity meeting=new MeetingEntity();
		meeting.setId(meetingId);
		meetingCommentEntity.setMeeting(meeting);
		meetingCommentRepository.delete(meetingCommentEntity);
		
		return getMeetingComments(meetingId);
	}
	
	

}
