package com.nexters.taiger.departure;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexters.taiger.common.util.DozerHelper;

import java.util.List;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Service
public class DepartureService {
	@Autowired
	private DepartureRepository departureRepository;

	@Autowired
	private DozerBeanMapper dozer;
	

	public List<DepartureDto> getDepartures() {
		
		List<DepartureEntity> list=(List<DepartureEntity>)departureRepository.findAll();
		
		return DozerHelper.map(dozer, list, DepartureDto.class);
	}
}
