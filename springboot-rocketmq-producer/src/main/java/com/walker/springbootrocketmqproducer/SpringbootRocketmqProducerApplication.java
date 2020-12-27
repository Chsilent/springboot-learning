package com.walker.springbootrocketmqproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringbootRocketmqProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRocketmqProducerApplication.class, args);
		log.info("**************SpringbootRocketmqProducerApplication start success.***********************");
	}

}
