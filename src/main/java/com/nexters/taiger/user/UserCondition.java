package com.nexters.taiger.user;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by BoBinLee on 2016. 1. 24..
 */

@Data
@NoArgsConstructor
public class UserCondition {
	private String kakaoId;
	private String name;
	private Integer gender;
	private Integer age;
}
