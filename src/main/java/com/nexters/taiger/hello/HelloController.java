package com.nexters.taiger.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BoBinLee on 2016. 1. 14..
 */

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
	@Autowired
	private HelloService helloService;

	@RequestMapping(value = "")
	public Hello hello(){
		return helloService.getHello();
	}
}
