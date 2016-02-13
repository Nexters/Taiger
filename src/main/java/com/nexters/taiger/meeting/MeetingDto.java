package com.nexters.taiger.meeting;
// Generated 2016. 1. 23 ���� 3:44:35 by Hibernate Tools 4.0.0

import lombok.Data;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nexters.taiger.user.UserDto;

@Data
public class MeetingDto {
	private int id;
	private int createUserId;
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
	private Double depLat;
	private Double depLng;
	private String depName;
	private List<UserDto> users;
	private int search;
}
