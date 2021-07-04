package com.gdg.springmyworkspace.opendata.seawater;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@IdClass(SeaWaterMonthlyId.class)
@Entity
@NoArgsConstructor
public class SeaWaterMonthly {

	private String sido_nm; // 시도명
	@Id
	@Column(length = 100)
	private String sta_nm; // 정점명
	private String res1; // 대장균수
	private String res2; // 장구균수
	private String res_yn; // 적합 부적합 여부
	@Id
	@Column(length = 100)
	private String res_year; // 조사년도
	@Id
	@Column(length = 100)
	private String res_date; // 조사일자
	private String lat; // 위도
	private String lon; // 경도

	public SeaWaterMonthly(SeaWaterMonthlyResponse.Item item) {

		this.sido_nm = item.getSido_nm();
		this.sta_nm = item.getSta_nm();
		this.res1 = item.getRes1();
		this.res2 = item.getRes2();
		this.res_yn = item.getRes_yn();
		this.res_year = item.getRes_year();
		this.res_date = item.getRes_date();
		this.lat = item.getLat();
		this.lon = item.getLon();

	}
}
