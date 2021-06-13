package com.gdg.springmyworkspace.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	// CORS(cross origin resource sharing)�� ����
	// ��Ʈ�� �ٸ������� ajax ȣ���� ���� �� �⺻������ ���ܵ�
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// addMapping("/**")��� �������� ���Ͽ� ��� ���ҽ��� �޼��带 ����Ѵ�.
		registry.addMapping("/**").allowedMethods("*");
	}
}
