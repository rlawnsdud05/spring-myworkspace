package com.gdg.springmyworkspace.opendata.dust;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

//외부시스템하고 인터페이스하는 클래스를 서비스라고 한다.
@Service
public class DustHourlyService {

	DustHourlyRepository repo;

	@Autowired
	public DustHourlyService(DustHourlyRepository repo) {
		this.repo = repo;
	}

	// @예약 작업요청= 스케쥴을 실행하는 메서드
	// "초 분 시 일 월 년"
	// @Scheduled(cron = "0 30 * * * *") // 매시 30분에 수집
	// 고정비율, ms(milli second 단위), 1000 = 1초
	@SuppressWarnings("deprecation")
	// @Scheduled(fixedRate = 1000 * 60 * 30) // 그냥 30분 마다
	@Scheduled(cron = "*/30 * * * * *")
	public void requestDustHourlyData() throws IOException {
		//System.out.println(new Date().toLocaleString() + "---실행---");
		 //getDustHorulyData("PM10");
	}

	// 데이터를 요청하는 메서드
	private void getDustHorulyData(String itemCode) throws IOException {

		String serviceKey = "&ServiceKey=FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";

		// 데이터 요청 URL을 만들어야함
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584/ArpltnStatsSvc"); // 서비스주소
		builder.append("/getCtprvnMesureLIst"); // 상세 기능 주소
		builder.append("?itemCode=" + itemCode); // 아이템 코드
		builder.append("&dataGubun=HOUR"); // 시간단위 조회
		builder.append("&pageNo=1"); // 현재부터 가까운 페이지만 조회
		builder.append("&numOfRows=1"); // 현재부터 24시간의 데이터 조회
		builder.append("&returnType=JSON"); // 응답 데이터는 JSON 형식으로 받음
		builder.append(serviceKey);

		 System.out.println(builder.toString());

		// 1. url주소로 접속 및 데이터 읽기;
		URL url = new URL(builder.toString()); // 문자열로부터 URL 객체 생성// 제대로된 url이 아니면 프로그램이 종료되버리기 때문에 에러처리 강제
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); // URL 주소에 접속을 함
		byte[] result = con.getInputStream().readAllBytes();

		// 2. byte[] -> String(JSON)으로 변환
		String data = new String(result);
		System.out.println(data);

		// 3. 문자열을 Object로 변환하기(gson라이브러리 활용= google에서 만듦 json을 java 객체로 만들어주는 )
		// DustHourlyResponse response = new Gson().fromJson(data,
		// DustHourlyResponse.class);
		DustHourlyResponse response = new Gson().fromJson(data, DustHourlyResponse.class);
		System.out.println(response);

		for (DustHourlyResponse.Item item : response.getResponse().getBody().getItems()) {
			repo.save(new DustHourly(item));
		}

	}

}
