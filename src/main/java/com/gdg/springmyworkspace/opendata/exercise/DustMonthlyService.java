package com.gdg.springmyworkspace.opendata.exercise;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class DustMonthlyService {

	// @Scheduled(cron = "*/30 * * * * *")
	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void requestHourlyService() throws IOException {
		// getDustHourlyData();
	}

	// ���̿�û �޼���
	private void getDustMonthlyData() throws IOException {
		String serviceKey = "FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1");// ��û url
		builder.append("/getOceansBeachSandInfo1");// �� ��� �ּ�
		builder.append("?serviceKey=" + serviceKey);
		builder.append("&SIDO_NM=" + URLEncoder.encode("��õ", "UTF-8"));// �õ���
		builder.append("&RES_YEAR=" + 2016);// ����⵵
		builder.append("&resultType=json");// ���� ������ ����

		// System.out.println(builder);

		// 1. url �ּҷ� ����
		URL url = new URL(builder.toString());

		// 2. url �ּҷ� ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		byte[] result = con.getInputStream().readAllBytes();
		String data = new String(result, "UTF-8");
		// System.out.println(data);

		// 3. ���ڿ��� Object�� ��ȯ�ϱ�
		DustTimelyResponse response = new Gson().fromJson(data, DustTimelyResponse.class);
		for (DustTimelyResponse.Item item : response.getGetOceansBeachSandInfo().getItems()) {

		}
	}
}
