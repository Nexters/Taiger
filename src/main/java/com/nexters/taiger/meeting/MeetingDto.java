package com.nexters.taiger.meeting;
// Generated 2016. 1. 23 ���� 3:44:35 by Hibernate Tools 4.0.0

import com.nexters.taiger.user.UserDto;
import lombok.Data;

import java.util.Date;

@Data
public class MeetingDto {
	private int id;
	private UserDto user;
	private int departureId;
	private String title;
	private Date startDate;
	private Integer genderType;
	private Integer maxUser;
	private Double destLat;
	private Double destLng;
	private String destName;
	private Integer costType;
	private Date createdAt;
}
