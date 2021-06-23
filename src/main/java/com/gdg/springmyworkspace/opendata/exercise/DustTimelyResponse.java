package com.gdg.springmyworkspace.opendata.exercise;

import java.util.List;

import lombok.Data;

@Data
public class DustTimelyResponse {
	private GetOceansBeachSandInfo getOceansBeachSandInfo;

	@Data
	public class GetOceansBeachSandInfo {
		private List<Item> items;
	}

	@Data
	public class Item {
		private String sidoNm;// 시도명
		private String gugunNm;// 구군명
		private String staNm;// 정점명??
		private String res1;// 카드뮴
		private String res2;// 비소
		private String res3;// 수은
		private String res4;// 납
		private String res5;// 6가크롬
		private String resYear;// 조사년도
		private String resDate;// 조사일자
		private String resYn;// 적합여부

	}

}
