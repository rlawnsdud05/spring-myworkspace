package com.gdg.springmyworkspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

//@Scheduled 라는 어노테이션을 사용하기 위해 필요한 어노테이션
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class SpringMyworkspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMyworkspaceApplication.class, args);

	}

}
