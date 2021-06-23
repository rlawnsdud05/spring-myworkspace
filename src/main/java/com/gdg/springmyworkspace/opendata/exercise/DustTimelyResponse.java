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
		private String sidoNm;// �õ���
		private String gugunNm;// ������
		private String staNm;// ������??
		private String res1;// ī���
		private String res2;// ���
		private String res3;// ����
		private String res4;// ��
		private String res5;// 6��ũ��
		private String resYear;// ����⵵
		private String resDate;// ��������
		private String resYn;// ���տ���

	}

}
