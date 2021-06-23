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
		private String sidoNm; // �õ���
		private String gugunNm; // ������
		private String staNm; // ������
		private String resLoc; // �������� A,B,C�� �ִ�
		private String res1; // ����ռ�
		private String res2; // �屸�ռ�
		private String resYn; // ���� ������ ����
		private String resYear; // ����⵵
		private String resDate; // ��������
		private String lat; // ����
		private String lon; // �浵

	}
}
