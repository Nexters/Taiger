package com.nexters.taiger.config;

import com.nexters.taiger.common.ApiErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Baek on 2016-01-28.
 */
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            Exception.class,
            RuntimeException.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiErrorDto processExceptionAndRuntimeException(Exception exception, WebRequest request) {
        return new ApiErrorDto(
                exception.getClass().getName(),
                exception.getMessage(),
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }

}
