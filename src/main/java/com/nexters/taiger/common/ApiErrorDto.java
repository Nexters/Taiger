package com.nexters.taiger.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiErrorDto {
    private String name;
    private String message;
    private long timestamp;
    private int statusCode;
}
