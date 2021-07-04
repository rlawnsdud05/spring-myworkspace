package com.gdg.springmyworkspace.opendata.seawater;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class SeaWaterMonthlyService {

	private SeaWaterRepository repo;
	private String serviceKey = "FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";

	@Autowired
	public SeaWaterMonthlyService(SeaWaterRepository repo) {
		this.repo = repo;
	}

	// @Scheduled(cron = "0 0 0 15,L 5,6,7,8,9 ?")
	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void requestSeaWaterMonthlyData() throws IOException {
		// 강원,경남,경북,부산,울산,인천,전남,전북,제주,충남
		final String[] SIDO_NM = { "강원", "경남", "경북", "부산", "울산", "인천", "전남", "전북", "제주", "충남" };

//		for (int j = 0; j < SIDO_NM.length; j++) {
//			int totalCount = getSeaWaterMonthlyData(SIDO_NM[j], Calendar.getInstance().get(Calendar.YEAR));// totalCount가져오기
//			getSeaWaterMonthlyData(SIDO_NM[j], Calendar.getInstance().get(Calendar.YEAR), totalCount);
//		}

		// 2014년 ~2020년 수질자료 업데이트
//		int[] RES_YEAR = { 2014, 2015, 2016, 2017, 2018, 2019, 2020 };
//		for (int i = 0; i < RES_YEAR.length; i++) {
//
//			for (int j = 0; j < SIDO_NM.length; j++) {
//				int totalCount = getSeaWaterMonthlyData(SIDO_NM[j], RES_YEAR[i]);// totalCount가져오기
//				// System.out.println(RES_YEAR[i] + "년 " + SIDO_NM[j] + "의 수질자료 총 개수:" +
//				// totalCount);
//				getSeaWaterMonthlyData(SIDO_NM[j], RES_YEAR[i], totalCount);
//			}
//
//		}

	}

	public int getSeaWaterMonthlyData(String SIDO_NM, int RES_YEAR) throws IOException {

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1");// url
		builder.append("/getOceansBeachSeawaterInfo1");// 상세기능명
		builder.append("?ServiceKey=" + serviceKey);// 서비스키
		builder.append("&SIDO_NM=" + URLEncoder.encode(SIDO_NM, "UTF-8"));
		builder.append("&pageNo=1");
		builder.append("&numOfRows=10");// 한 페이지의 결과 수
		builder.append("&RES_YEAR=" + RES_YEAR);
		builder.append("&resultType=json");// 응답결과형식

		// System.out.println(builder.toString());

		// 1. URL 생성
		URL url = new URL(builder.toString());

		// 2. URL 주소로 접속
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 3. 받아온 데이터 가져오기
		byte[] result = con.getInputStream().readAllBytes();

		// 4. 읽은 data String으로 변환
		String data = new String(result, "UTF-8");
		// System.out.println(data);

		// 5. JSON형태의 데이터를 java 객체 형태로 변환
		SeaWaterMonthlyResponse response = new Gson().fromJson(data, SeaWaterMonthlyResponse.class);
		// System.out.println("total count 체크" +
		// response.getGetOceansBeachSeawaterInfo().getTotalCount());
		return response.getGetOceansBeachSeawaterInfo().getTotalCount();
	}

	public void getSeaWaterMonthlyData(String SIDO_NM, int RES_YEAR, int totalCount) throws IOException {

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1");// url
		builder.append("/getOceansBeachSeawaterInfo1");// 상세기능명
		builder.append("?ServiceKey=" + serviceKey);// 서비스키
		builder.append("&SIDO_NM=" + URLEncoder.encode(SIDO_NM, "UTF-8"));
		builder.append("&pageNo=1");
		builder.append("&numOfRows=" + totalCount);// 한 페이지의 결과 수
		builder.append("&RES_YEAR=" + RES_YEAR);
		builder.append("&resultType=json");// 응답결과형식

		// System.out.println(builder.toString());

		// 1. URL 생성
		URL url = new URL(builder.toString());

		// 2. URL 주소로 접속
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 3. 받아온 데이터 가져오기
		byte[] result = con.getInputStream().readAllBytes();

		// 4. 읽은 data String으로 변환
		String data = new String(result, "UTF-8");
		System.out.println(data);

		// 5. JSON형태의 데이터를 java 객체 형태로 변환
		SeaWaterMonthlyResponse response = new Gson().fromJson(data, SeaWaterMonthlyResponse.class);

		// System.out.println(response.getGetOceansBeachSeawaterInfo().getItem());
		// List<Item> item= response.getGetOceansBeachSeawaterInfo().getItem();
//		for(int i=0; i<item.; i++) {
//			
//		}
		for (SeaWaterMonthlyResponse.Item item : response.getGetOceansBeachSeawaterInfo().getItem()) {

			// data filtering
			// 1. 조사일자가 null일때 저장 안함
			// 엔티티 클래스에서 프라이머리키인 필드가 널값을 전달받을 때 에러가 발생
			if (item.getRes_date() == null) {
				continue;
			} else {

				try {
					// 2. 500MPN/100mL이하(res1), 100MPN/100mL(res2) mpn 뒤부터 없애기
					if (item.getRes1() == "500MPN/100ml이하" || item.getRes2() == "100MPN/100m")
						continue;

					// 2. 숫자가 아닌 데이터 없애기
					item.setRes1(item.getRes1().replaceAll("\\D", ""));
					item.setRes2(item.getRes2().replaceAll("\\D", ""));
				} catch (NullPointerException e) {
					System.out.println("res 데이터가 없습니다.");
				}

			}

			System.out.println("시도명: " + item.getSido_nm());
			System.out.println("지점명: " + item.getSta_nm());
			System.out.println("조사지점" + item.getRes_loc());
			System.out.println("장구균수: " + item.getRes1());
			System.out.println("대장균수: " + item.getRes2());
			System.out.println("적합여부판정: " + item.getRes_yn());
			System.out.println("조사년도: " + item.getRes_year());
			System.out.println("조사일자: " + item.getRes_date());
			System.out.println("위도: " + item.getLat());
			System.out.println("경도: " + item.getLon());
			if (item.getRes_loc().equals("A")) {
				System.out.println("조사지점 " + item.getRes_loc() + "만 저장");
				repo.save(new SeaWaterMonthly(item));
			}

		}
	}

}
