package com.nexters.taiger;

import com.nexters.taiger.hello.Hello;
import com.nexters.taiger.hello.HelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class InitDataLoader {
	@Autowired
	private HelloRepository helloRepository;

	public void init() {
		log.info("InitDataLoader : ");

		saveDummyHello();

	}

	private void saveDummyHello() {
		Hello hello = new Hello();
		hello.setMessage("Hello world");
		
		helloRepository.save(hello);
		
	}
}
