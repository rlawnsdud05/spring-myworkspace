package com.gdg.springmyworkspace.opendata.seawater;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SeaWaterMonthlyService {

	// @Scheduled(cron = "0 0 0 15,L 5,6,7,8,9 ?")
	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void requestSeaWaterMonthlyData() throws IOException {
		// ����,�泲,���,�λ�,���,��õ,����,����,����,�泲
		final String[] SIDO_NM = { "����", "�泲", "���", "�λ�", "���", "��õ", "����", "����", "����", "�泲" };
		String[] RES_YEAR = { "2014", "2015", "2016", "2017", "2018", "2019", "2020" };
		// 2014���ڷ����~ 6,7,8�� �ڷ� ������Ʈ�ϴ� ��
		for (int i = 0; i < RES_YEAR.length; i++) {
			// System.out.println("cron üũ : " + new SimpleDateFormat("HH�� mm��
			// ss��").format(new Date().getTime()));
			for (int j = 0; j < SIDO_NM.length; j++) {
				getSeaWaterMonthlyData(SIDO_NM[j], RES_YEAR[i]);
			}

		}

	}

	public void getSeaWaterMonthlyData(String SIDO_NM, String RES_YEAR) throws IOException {
		String serviceKey = "FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1");// url
		builder.append("/getOceansBeachSeawaterInfo1");// �󼼱�ɸ�
		builder.append("?ServiceKey=" + serviceKey);// ����Ű
		builder.append("&SIDO_NM=" + URLEncoder.encode(SIDO_NM, "UTF-8"));
		builder.append("&pageNo=1");
		builder.append("&numOfRows=10");
		builder.append("&RES_YEAR=" + RES_YEAR);
		builder.append("&resultType=json");// ����������

		System.out.println(builder.toString());

		// 1. URL ����
//		URL url = new URL(builder.toString());
//
//		// 2. URL �ּҷ� ����
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//		// 3. �޾ƿ� ������ ��������
//		byte[] result = con.getInputStream().readAllBytes();
//
//		// 4. ���� data String���� ��ȯ
//		String data = new String(result, "UTF-8");
//		System.out.println(data);

	}

}
