package com.gdg.springmyworkspace.opendata.exercise;


import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DustTimelyService {

	//@Scheduled(cron = "*/30 * * * * *")
	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void requestHourlyService() throws IOException {
		getDustHourlyData();
	}
	
	//���̿�û �޼���
	private void getDustHourlyData() throws IOException {
		String serviceKey = "FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";
		
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552657/ErmctInfoInqireService");//��û url
		builder.append("/getEmrrmRltmUsefulSckbdInfoInqire?");//�� ��� �ּ�
		builder.append("serviceKey=" + serviceKey);
		builder.append("&STAGE1=" + URLEncoder.encode("����Ư����", "UTF-8"));
		builder.append("&STAGE2=" + URLEncoder.encode("������", "UTF-8"));
		
		System.out.println(builder);
		
		//1. url �ּҷ� ����
		URL url = new URL(builder.toString());
		
		//2. url �ּҷ� ����
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		byte[] result = con.getInputStream().readAllBytes();
		String data = new String(result, "UTF-8");
		System.out.println(data);
	}
}
