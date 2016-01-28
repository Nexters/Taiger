package com.nexters.taiger.meeting;

import com.nexters.taiger.common.AuthUserDto;
import com.nexters.taiger.common.SortType;
import com.nexters.taiger.user.UserDto;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	@RequestMapping(value="/meetings")
	public List<MeetingDto> getMeetings(AuthUserDto authUser, SortType sortType) {
		return null;
	}

	/**
	 *
	 * @param authUser
	 * @param meetingDto
     * @return
     */
	@RequestMapping(value="/meeting",method =RequestMethod.POST)
	public MeetingDto createMeeting(AuthUserDto authUser, @RequestBody MeetingDto meetingDto){
		MeetingEntity meetingEntity = dozer.map(meetingDto, MeetingEntity.class);
		return null;
	}

	/**
	 * 미팅삭제
	 * @param authUser
	 * @param meetingDto
     */
	@RequestMapping(value="/meeting",method =RequestMethod.POST)
	public MeetingDto deleteMeeting(AuthUserDto authUser, @RequestBody MeetingDto meetingDto){
		return null;
	}

	/**
	 * 미팅입장
	 * @param authUser
	 * @param meetingId
     * @return
     */
	@RequestMapping(value="/meeting/{meetingId}", method=RequestMethod.POST)
	public MeetingDto enterMeeting(AuthUserDto authUser, @PathVariable long meetingId){
		return null;
	}

	/**
	 * 미팅참여자목록
	 * @param authUser
	 * @param meetingId
     * @return
     */
	@RequestMapping(value="/meeting/{meetingId}/users", method=RequestMethod.GET)
	public List<UserDto> getMeetingUsers(AuthUserDto authUser, @PathVariable long meetingId){
		return null;
	}

	/**
	 * 미팅참여확정
	 * @param authUser
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="/meeting/{meetingId}/join", method=RequestMethod.POST)
	public List<UserDto> joinMeetingUser(AuthUserDto authUser, @PathVariable long meetingId){
		return null;
	}

	/**
	 * 미팅참여취소
	 * @param authUser
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="/meeting/{meetingId}/join", method=RequestMethod.DELETE)
	public List<UserDto> cancelMeetingUser(AuthUserDto authUser, @PathVariable long meetingId){
		return null;
	}

	/**
	 * 미팅덧글조회
	 * @param authUser
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="/meeting/{meetingId}/comments", method=RequestMethod.GET)
	public List<MeetingCommentDto> getMeetingComments(AuthUserDto authUser, @PathVariable long meetingId){
		return null;
	}

	/**
	 * 미팅덧글달기
	 * @param authUser
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="/meeting/{meetingId}/comment", method=RequestMethod.POST)
	public List<MeetingCommentDto> getMeetingComments(AuthUserDto authUser, @PathVariable long meetingId, @RequestBody MeetingCommentDto meetingCommentDto){
		return null;
	}

	/**
	 * 미팅덧글삭제
	 * @param authUser
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="/meeting/{meetingId}/comment/{commentId}", method=RequestMethod.DELETE)
	public List<MeetingCommentDto> deleteMeetingComments(AuthUserDto authUser, @PathVariable long meetingId, @PathVariable long commentId){
		return null;
	}

}
