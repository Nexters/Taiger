package com.nexters.taiger.departure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on Baek on 2016. 1. 23...
 */
@RestController
@RequestMapping(value = "/departure")
public class DepartureController {
	@Autowired
	private DepartureService departureService;

}
