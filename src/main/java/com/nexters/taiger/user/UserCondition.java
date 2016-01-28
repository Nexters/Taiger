package com.nexters.taiger.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by BoBinLee on 2016. 1. 24..
 */

@Data
@NoArgsConstructor
@ToString
public class UserCondition {
	private String kakaoId;
	private String kakaoToken;
	private String name;
	private Integer gender;
	private Integer age;
}
