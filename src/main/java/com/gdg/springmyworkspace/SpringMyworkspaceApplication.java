package com.gdg.springmyworkspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@Scheduled ��� ������̼��� ����ϱ� ���� �ʿ��� ������̼�
@EnableScheduling
@SpringBootApplication
public class SpringMyworkspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMyworkspaceApplication.class, args);

	}

}
