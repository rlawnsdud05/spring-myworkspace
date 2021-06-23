package com.gdg.springmyworkspace.opendata.exercise;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class DustMonthlyService {

	// @Scheduled(cron = "*/30 * * * * *")
	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void requestHourlyService() throws IOException {
		// getDustHourlyData();
	}

	// 데이요청 메서드
	private void getDustMonthlyData() throws IOException {
		String serviceKey = "FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1");// 요청 url
		builder.append("/getOceansBeachSandInfo1");// 상세 기능 주소
		builder.append("?serviceKey=" + serviceKey);
		builder.append("&SIDO_NM=" + URLEncoder.encode("인천", "UTF-8"));// 시도명
		builder.append("&RES_YEAR=" + 2016);// 조사년도
		builder.append("&resultType=json");// 응답 데이터 형식

		// System.out.println(builder);

		// 1. url 주소로 생성
		URL url = new URL(builder.toString());

		// 2. url 주소로 접속
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		byte[] result = con.getInputStream().readAllBytes();
		String data = new String(result, "UTF-8");
		// System.out.println(data);

		// 3. 문자열을 Object로 변환하기
		DustTimelyResponse response = new Gson().fromJson(data, DustTimelyResponse.class);
		for (DustTimelyResponse.Item item : response.getGetOceansBeachSandInfo().getItems()) {

		}
	}
}
