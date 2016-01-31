package com.nexters.taiger.user;
// Generated 2016. 1. 23 ���� 3:44:35 by Hibernate Tools 4.0.0

import lombok.Data;

@Data
public class UserDto {
	private int id;
	private String kakaoId;
	private String kakaoToken;
	private String name;
	private Integer gender;
	private Integer age;
	private Integer meetingCount;
	private Integer reliability;
	private Integer primaryDepartureId;
	private String createdBy;
}
