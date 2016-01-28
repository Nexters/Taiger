package com.nexters.taiger.common;

import com.nexters.taiger.user.UserDto;
import lombok.Data;

import java.util.Date;

/**
 * Created by Baek on 2016-01-28.
 */
@Data
public class AuthUserDto {
    private UserDto userDto;
    private Date loginDate;
}
