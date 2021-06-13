package com.gdg.springmyworkspace.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	// CORS(cross origin resource sharing)를 설정
	// 포트가 다른곳에서 ajax 호출을 했을 때 기본적으로 차단됨
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// addMapping("/**")모든 원격지에 대하여 모든 리소스와 메서드를 허용한다.
		registry.addMapping("/**").allowedMethods("*");
	}
}
