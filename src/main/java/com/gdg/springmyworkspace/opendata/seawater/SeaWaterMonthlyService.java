package com.gdg.springmyworkspace.opendata.seawater;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SeaWaterMonthlyService {

	// @Scheduled(cron = "0 0 0 15,L 5,6,7,8,9 ?")
	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void requestSeaWaterMonthlyData() throws IOException {
		// 강원,경남,경북,부산,울산,인천,전남,전북,제주,충남
		final String[] SIDO_NM = { "강원", "경남", "경북", "부산", "울산", "인천", "전남", "전북", "제주", "충남" };
		String[] RES_YEAR = { "2014", "2015", "2016", "2017", "2018", "2019", "2020" };
		// 2014년자료부터~ 6,7,8월 자료 업데이트하는 달
		for (int i = 0; i < RES_YEAR.length; i++) {
			// System.out.println("cron 체크 : " + new SimpleDateFormat("HH시 mm분
			// ss초").format(new Date().getTime()));
			for (int j = 0; j < SIDO_NM.length; j++) {
				getSeaWaterMonthlyData(SIDO_NM[j], RES_YEAR[i]);
			}

		}

	}

	public void getSeaWaterMonthlyData(String SIDO_NM, String RES_YEAR) throws IOException {
		String serviceKey = "FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1");// url
		builder.append("/getOceansBeachSeawaterInfo1");// 상세기능명
		builder.append("?ServiceKey=" + serviceKey);// 서비스키
		builder.append("&SIDO_NM=" + URLEncoder.encode(SIDO_NM, "UTF-8"));
		builder.append("&pageNo=1");
		builder.append("&numOfRows=10");
		builder.append("&RES_YEAR=" + RES_YEAR);
		builder.append("&resultType=json");// 응답결과형식

		System.out.println(builder.toString());

		// 1. URL 생성
//		URL url = new URL(builder.toString());
//
//		// 2. URL 주소로 접속
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//		// 3. 받아온 데이터 가져오기
//		byte[] result = con.getInputStream().readAllBytes();
//
//		// 4. 읽은 data String으로 변환
//		String data = new String(result, "UTF-8");
//		System.out.println(data);

	}

}
