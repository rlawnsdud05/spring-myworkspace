package com.gdg.springmyworkspace.opendata.seawater;

import java.util.List;

import lombok.Data;

@Data
public class SeaWaterMonthlyResponse {
	private GetOceansBeachSeawaterInfo getOceansBeachSeawaterInfo;

	@Data
	public class GetOceansBeachSeawaterInfo {
		private List<Item> item;
	}

	@Data
	public class Item {
		private String sidoNm; // 시도명
		private String gugunNm; // 구군명
		private String staNm; // 정점명
		private String resLoc; // 조사지점 A,B,C가 있다
		private String res1; // 대장균수
		private String res2; // 장구균수
		private String resYn; // 적합 부적합 여부
		private String resYear; // 조사년도
		private String resDate; // 조사일자
		private String lat; // 위도
		private String lon; // 경도

	}
}
