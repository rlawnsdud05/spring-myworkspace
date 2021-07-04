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

	private String sido_nm; // �õ���
	@Id
	@Column(length = 100)
	private String sta_nm; // ������
	private String res1; // ����ռ�
	private String res2; // �屸�ռ�
	private String res_yn; // ���� ������ ����
	@Id
	@Column(length = 100)
	private String res_year; // ����⵵
	@Id
	@Column(length = 100)
	private String res_date; // ��������
	private String lat; // ����
	private String lon; // �浵

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
