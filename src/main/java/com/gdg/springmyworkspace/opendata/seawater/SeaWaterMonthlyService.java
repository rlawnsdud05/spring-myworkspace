package com.gdg.springmyworkspace.opendata.seawater;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gdg.springmyworkspace.opendata.seawater.SeaWaterMonthlyResponse.Item;
import com.google.gson.Gson;

@Service
public class SeaWaterMonthlyService {

	
	SeaWaterRepository repo;
	
	@Autowired
	public SeaWaterMonthlyService(SeaWaterRepository repo) {
		this.repo = repo;
	}
	
	// @Scheduled(cron = "0 0 0 15,L 5,6,7,8,9 ?")
	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void requestSeaWaterMonthlyData() throws IOException {
		// 강원,경남,경북,부산,울산,인천,전남,전북,제주,충남
		final String[] SIDO_NM = { "강원", "경남", "경북", "부산", "울산", "인천", "전남", "전북", "제주", "충남" };
		String[] RES_YEAR = { "2014", "2015", "2016", "2017", "2018", "2019", "2020" };
		// 2014년자료부터~ 6,7,8월 자료 업데이트하는 달
//		for (int i = 0; i < RES_YEAR.length; i++) {
//			// System.out.println("cron 체크 : " + new SimpleDateFormat("HH시 mm분
//			// ss초").format(new Date().getTime()));
//			for (int j = 0; j < SIDO_NM.length; j++) {
//				//int totalCount = getSeaWaterDataMonthlyData(SIDO_NM[j], RES_YEAR[i]);//totalCount가져오기
//				getSeaWaterMonthlyData(SIDO_NM[j], RES_YEAR[i], 10);
//			}
//
//		}
		
		getSeaWaterMonthlyData("충남", "2016", 10);
	}
	
	public int getSeaWaterDataMonthlyData(String SIDO_NM, String RES_YEAR) throws IOException {
		String serviceKey = "FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1");// url
		builder.append("/getOceansBeachSeawaterInfo1");// 상세기능명
		builder.append("?ServiceKey=" + serviceKey);// 서비스키
		builder.append("&SIDO_NM=" + URLEncoder.encode(SIDO_NM, "UTF-8"));
		builder.append("&pageNo=1");
		builder.append("&numOfRows=10");//한 페이지의 결과 수
		builder.append("&RES_YEAR=" + RES_YEAR);
		builder.append("&resultType=json");// 응답결과형식

		//System.out.println(builder.toString());

		// 1. URL 생성
		URL url = new URL(builder.toString());

		// 2. URL 주소로 접속
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 3. 받아온 데이터 가져오기
		byte[] result = con.getInputStream().readAllBytes();

		// 4. 읽은 data String으로 변환
		String data = new String(result, "UTF-8");
		//System.out.println(data);
		
		//5. JSON형태의 데이터를 java 객체 형태로 변환
		SeaWaterMonthlyResponse response = new Gson().fromJson(data, SeaWaterMonthlyResponse.class);
		//System.out.println("total count 체크" + response.getGetOceansBeachSeawaterInfo().getTotalCount());
		return response.getGetOceansBeachSeawaterInfo().getTotalCount();
	}

	public void getSeaWaterMonthlyData(String SIDO_NM, String RES_YEAR, int totalCount) throws IOException {
		String serviceKey = "FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1");// url
		builder.append("/getOceansBeachSeawaterInfo1");// 상세기능명
		builder.append("?ServiceKey=" + serviceKey);// 서비스키
		builder.append("&SIDO_NM=" + URLEncoder.encode(SIDO_NM, "UTF-8"));
		builder.append("&pageNo=1");
		builder.append("&numOfRows=" + totalCount);//한 페이지의 결과 수
		builder.append("&RES_YEAR=" + RES_YEAR);
		builder.append("&resultType=json");// 응답결과형식

		//System.out.println(builder.toString());

		// 1. URL 생성
		URL url = new URL(builder.toString());

		// 2. URL 주소로 접속
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 3. 받아온 데이터 가져오기
		byte[] result = con.getInputStream().readAllBytes();

		// 4. 읽은 data String으로 변환
		String data = new String(result, "UTF-8");
		//System.out.println(data);
		
		//5. JSON형태의 데이터를 java 객체 형태로 변환
		SeaWaterMonthlyResponse response = new Gson().fromJson(data, SeaWaterMonthlyResponse.class);
		//System.out.println("total count 체크" + response.getGetOceansBeachSeawaterInfo().getTotalCount());
		//System.out.println(response.getGetOceansBeachSeawaterInfo().getItem());
		
		for(SeaWaterMonthlyResponse.Item item : response.getGetOceansBeachSeawaterInfo().getItem()) {
			
			if(item.getRes1().charAt(0) == '<') {
				item.setRes1(item.getRes1().substring(1));
			}
			else if(item.getRes2().charAt(0) == '<') {
				item.setRes2(item.getRes2().substring(1));
			}
			System.out.println(item.getSido_nm());
			System.out.println(item.getGugun_nm());
			System.out.println(item.getSta_nm());
			System.out.println(item.getRes_loc());
			System.out.println(item.getRes1());
			System.out.println(item.getRes2());
			System.out.println(item.getRes_yn());
			System.out.println(item.getRes_year());
			System.out.println(item.getRes_date());
			System.out.println(item.getLat());
			System.out.println(item.getLon());
			repo.save(new SeaWaterMonthly(item)); 
			
			
		}
	}

}
