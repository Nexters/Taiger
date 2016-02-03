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
import com.nexters.taiger.common.util.DozerHelper;
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
	
	public void createMeeting(MeetingEntity meetingEntity){
		meetingRepository.save(meetingEntity);
	}
	
	
    public void deleteMeeting(MeetingEntity meetingEntity){
		 meetingRepository.delete(meetingEntity);
	 }
	
	public List<MeetingEntity> getMeetings(UserSortType sortType){
		//List<MeetingEntity> meeting=meetingRepository.findAllOrderByCreatedAtDesc(createAt)
		return null;
	}
	
	public void enterMeeting(int userId, int meetingId){
		
		
		MeetingEntity meetingEntity=new MeetingEntity();
		UserEntity user =new UserEntity();
		user.setId(userId);
		Set<UserEntity> users=new HashSet<UserEntity>();
		users.add(user);
		meetingEntity.setId(meetingId);
		meetingEntity.setUsers(users);
		meetingRepository.save(meetingEntity);
		
	}
	
	public List<UserDto> getMeetingUsers(int meetingId){
		
		
		MeetingEntity meetingEntity=meetingRepository.findOne(meetingId);
		Set<UserEntity> users=meetingEntity.getUsers();
		List<UserDto> list=new ArrayList<UserDto>();
		for(UserEntity user: users){
			list.add(dozer.map(user, UserDto.class));
		}
		
		
		
		return list;
	}
	
	public void cancelMeetingUser(int userId,int meetingId){
		
		MeetingEntity meetingEntity=new MeetingEntity();
		UserEntity user=new UserEntity();
		user.setId(userId);
		meetingEntity.setId(meetingId);
		Set<UserEntity> users=meetingEntity.getUsers();
		users.add(user);
		meetingEntity.setUsers(users);
		meetingRepository.delete(meetingEntity);
	}
	
	public List<MeetingCommentDto> getMeetingComments(int meetingId){
		
		List<MeetingCommentDto> meetingCommentDto=meetingCommentRepository.findAllByMeetingId(meetingId);
		
		return meetingCommentDto;
	}
	
	public List<MeetingCommentDto> saveComment(int meetingId,MeetingCommentEntity meetingCommentEntity){
		
		meetingCommentRepository.save(meetingCommentEntity);
		
		return getMeetingComments(meetingId);
	}
	
	public List<MeetingCommentDto> deleteMeetingComments(int commentId,int meetingId){
		
		meetingCommentRepository.deleteByIdAndMeetingId(commentId, meetingId);
		
		return getMeetingComments(meetingId);
	}
	
	

}
