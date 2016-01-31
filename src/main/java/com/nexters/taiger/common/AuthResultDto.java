package com.nexters.taiger.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created by Baek on 2016-01-28.
 */
@Data
@AllArgsConstructor
public class AuthResultDto {
    private String accessToken;
    private String ipAddress;
    private Date loginDate;
}
