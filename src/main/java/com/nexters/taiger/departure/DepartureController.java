package com.nexters.taiger.departure;

import com.nexters.taiger.common.AccessTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on Baek on 2016. 1. 23...
 */
@RestController
public class DepartureController {
	
	@Autowired
	private DepartureService departureService;

	/**
	 * 출발지 조회
	 * @param authUser
	 * @return
     */
	@RequestMapping("/departures")
	public List<DepartureDto> getDepartures() {
		
		return departureService.getDepartures();
	}
}
