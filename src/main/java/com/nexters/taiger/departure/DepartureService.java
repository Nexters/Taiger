package com.nexters.taiger.departure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Service
public class DepartureService {
	@Autowired
	private DepartureRepository helloRepository;

	public DepartureEntity getHello(){
		return helloRepository.findOne(1);
	}

	public List<DepartureDto> getDepartures() {
		return null;
	}
}
