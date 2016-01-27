package com.nexters.taiger;

import com.nexters.taiger.hello.Hello;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitDataLoader {
	
	

	public void init() {
		log.info("InitDataLoader : ");


	}
	private void saveDummyHello() {
		Hello hello = new Hello();
		hello.setMessage("Hello world");
		
	}

}
