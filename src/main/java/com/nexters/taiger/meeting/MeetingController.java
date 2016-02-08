package com.nexters.taiger.meeting;

import java.util.List;

import com.nexters.taiger.common.AccessTokenDto;
import com.nexters.taiger.common.constant.UserSortType;
import com.nexters.taiger.common.exception.MeetingRoomFullException;
import com.nexters.taiger.user.UserDto;




import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on Baek on 2016. 1. 23...
 */
@RestController
public class MeetingController {
	@Autowired
	private MeetingService meetingService;

	@Autowired
	private DozerBeanMapper dozer;

	/**
	 * 미팅목록조회 / 정렬
	 * @param authUser
	 * @param sortType
     */
	@RequestMapping(value="/meetings",method =RequestMethod.GET)
	public List<MeetingDto> getMeetings(AccessTokenDto authUser, UserSortType sortType) {
		
		return null;
	}

	/**
	 * 미팅생성
	 * @param authUser
	 * @param meetingDto
     * @return
     */
	@RequestMapping(value="/meeting",method =RequestMethod.POST)
	public MeetingDto createMeeting(AccessTokenDto authUser, @RequestBody MeetingDto meetingDto){
		
		
		meetingService.createMeeting(meetingDto);
		
		
		
		return meetingDto;
	}

	/**
	 * 미팅삭제
	 * @param authUser
	 * @param meetingDto
     */
	@RequestMapping(value="/meeting",method =RequestMethod.DELETE)
	public MeetingDto deleteMeeting(AccessTokenDto authUser, @RequestBody MeetingDto meetingDto){
		MeetingEntity meetingEntity = dozer.map(meetingDto, MeetingEntity.class);
		
		meetingService.deleteMeeting(meetingEntity);
		
		return meetingDto;
	}

	/**
	 * 미팅입장
	 * @param authUser
	 * @param meetingId
     * @return
     */
	@RequestMapping(value="/meeting/{meetingId}", method=RequestMethod.GET)
	public MeetingDto enterMeeting(AccessTokenDto authUser, @PathVariable int meetingId){
		
		
		return meetingService.enterMeeting(meetingId);
	}

	/**
	 * 미팅참여자목록
	 * @param authUser
	 * @param meetingId
     * @return
     */
	@RequestMapping(value="/meeting/{meetingId}/users", method=RequestMethod.GET)
	public List<UserDto> getMeetingUsers(AccessTokenDto authUser, @PathVariable int meetingId){
		
		List<UserDto> users=meetingService.getMeetingUsers(meetingId);
		return users;
	}

	/**
	 * 미팅참여확정
	 * @param authUser
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="/meeting/{meetingId}/join", method=RequestMethod.POST)
	public List<UserDto> joinMeetingUser(AccessTokenDto authUser, @PathVariable int meetingId) throws MeetingRoomFullException{
		
		int userId=authUser.getId();
		meetingService.joinMeetingUser(userId, meetingId);
		
		return null;
	}

	/**
	 * 미팅참여취소
	 * @param authUser
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="/meeting/{meetingId}/join", method=RequestMethod.DELETE)
	public List<UserDto> cancelMeetingUser(AccessTokenDto authUser, @PathVariable int meetingId){
		int userId=authUser.getId();
		
		meetingService.cancelMeetingUser(userId, meetingId);
		return null;
	}

	/**
	 * 미팅덧글조회
	 * @param authUser
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="/meeting/{meetingId}/comments", method=RequestMethod.GET)
	public List<MeetingCommentDto> getMeetingComments(AccessTokenDto authUser, @PathVariable int meetingId){
		
		List<MeetingCommentDto> list=meetingService.getMeetingComments(meetingId);
		
		return list;
	}

	/**
	 * 미팅덧글달기
	 * @param authUser
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="/meeting/{meetingId}/comment", method=RequestMethod.POST)
	public List<MeetingCommentDto> getMeetingComments(AccessTokenDto authUser, @PathVariable int meetingId, @RequestBody MeetingCommentDto meetingCommentDto){
		
		
		meetingCommentDto.setMeetingId(meetingId);
		
		MeetingCommentEntity entity=new MeetingCommentEntity(meetingCommentDto);
		
		List<MeetingCommentDto> list=meetingService.saveComment(meetingId,entity);
		
		return list;
	}

	/**
	 * 미팅덧글삭제
	 * @param authUser
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="/meeting/{meetingId}/comment/{commentId}", method=RequestMethod.DELETE)
	public List<MeetingCommentDto> deleteMeetingComments(AccessTokenDto authUser, @PathVariable int meetingId, @PathVariable int commentId){
		
		List<MeetingCommentDto> list=meetingService.deleteMeetingComments(commentId,meetingId);
		return list;
	}

}
