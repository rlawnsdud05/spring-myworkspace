package com.gdg.springmyworkspace.opendata.seawater;

import java.util.List;

import lombok.Data;

@Data
public class SeaWaterMonthlyResponse {
	private GetOceansBeachSeawaterInfo getOceansBeachSeawaterInfo;

	@Data
	public class GetOceansBeachSeawaterInfo {
		private List<Item> item;
		private int totalCount;
	}

	@Data
	public class Item {

		private String sido_nm; // 시도명
		private String sta_nm; // 정점명
		private String res_loc;
		private String res1; // 대장균수
		private String res2; // 장구균수
		private String res_yn; // 적합 부적합 여부
		private String res_year; // 조사년도
		private String res_date; // 조사일자
		private String lat; // 위도
		private String lon; // 경도

	}
}
