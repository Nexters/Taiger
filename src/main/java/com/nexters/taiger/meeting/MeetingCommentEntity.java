package com.nexters.taiger.meeting;
// Generated 2016. 1. 23 ���� 3:44:35 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nexters.taiger.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "meeting_comment", catalog = "taiger")
public class MeetingCommentEntity implements java.io.Serializable {

	public MeetingCommentEntity(){
		
	}
	
	public MeetingCommentEntity(MeetingCommentDto meetingCommentDto){
		this.id=meetingCommentDto.getId();
		
		this.user=new UserEntity();
		this.user.setId(meetingCommentDto.getUserId());
		
		this.meeting=new MeetingEntity();
		this.meeting.setId(meetingCommentDto.getMeetingId());
		
		this.content=meetingCommentDto.getContent();
		this.createdAt=meetingCommentDto.getCreatedAt();
	
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "meeting_id", nullable = false)
	private MeetingEntity meeting;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)	
	private UserEntity user;
	
	
	@Column(name = "content", length = 512)
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	private Date createdAt;

}
