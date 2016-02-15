package com.nexters.taiger.common;

import lombok.Data;

@Data
public class GcmDto {

	private int userId;
	private int rid;
	private String message;
}
