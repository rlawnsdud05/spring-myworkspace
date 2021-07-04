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

		private String sido_nm; // �õ���
		private String sta_nm; // ������
		private String res_loc;
		private String res1; // ����ռ�
		private String res2; // �屸�ռ�
		private String res_yn; // ���� ������ ����
		private String res_year; // ����⵵
		private String res_date; // ��������
		private String lat; // ����
		private String lon; // �浵

	}
}
