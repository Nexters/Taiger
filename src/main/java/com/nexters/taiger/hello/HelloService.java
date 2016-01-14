package com.nexters.taiger.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BoBinLee on 2016. 1. 14..
 */

@Service
public class HelloService {
	@Autowired
	private HelloRepository helloRepository;

	public Hello getHello(){
		return helloRepository.findOne(1);
	}
}
