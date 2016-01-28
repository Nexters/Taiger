package com.nexters.taiger.meeting;
// Generated 2016. 1. 23 ���� 3:44:35 by Hibernate Tools 4.0.0

import lombok.Data;

import java.util.Date;

@Data
public class MeetingCommentDto {
	private int id;
	private int meetingId;
	private int userId;
	private String content;
	private Date createdAt;
}
