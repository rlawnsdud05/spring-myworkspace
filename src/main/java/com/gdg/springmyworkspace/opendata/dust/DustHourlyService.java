package com.gdg.springmyworkspace.opendata.dust;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

//�ܺνý����ϰ� �������̽��ϴ� Ŭ������ ���񽺶�� �Ѵ�.
@Service
public class DustHourlyService {

	DustHourlyRepository repo;

	@Autowired
	public DustHourlyService(DustHourlyRepository repo) {
		this.repo = repo;
	}

	// @���� �۾���û= �������� �����ϴ� �޼���
	// "�� �� �� �� �� ��"
	// @Scheduled(cron = "0 30 * * * *") // �Ž� 30�п� ����
	// ��������, ms(milli second ����), 1000 = 1��
	@SuppressWarnings("deprecation")
	// @Scheduled(fixedRate = 1000 * 60 * 30) // �׳� 30�� ����
	@Scheduled(cron = "*/30 * * * * *")
	public void requestDustHourlyData() throws IOException {
		//System.out.println(new Date().toLocaleString() + "---����---");
		 //getDustHorulyData("PM10");
	}

	// �����͸� ��û�ϴ� �޼���
	private void getDustHorulyData(String itemCode) throws IOException {

		String serviceKey = "&ServiceKey=FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";

		// ������ ��û URL�� ��������
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584/ArpltnStatsSvc"); // �����ּ�
		builder.append("/getCtprvnMesureLIst"); // �� ��� �ּ�
		builder.append("?itemCode=" + itemCode); // ������ �ڵ�
		builder.append("&dataGubun=HOUR"); // �ð����� ��ȸ
		builder.append("&pageNo=1"); // ������� ����� �������� ��ȸ
		builder.append("&numOfRows=1"); // ������� 24�ð��� ������ ��ȸ
		builder.append("&returnType=JSON"); // ���� �����ʹ� JSON �������� ����
		builder.append(serviceKey);

		 System.out.println(builder.toString());

		// 1. url�ּҷ� ���� �� ������ �б�;
		URL url = new URL(builder.toString()); // ���ڿ��κ��� URL ��ü ����// ����ε� url�� �ƴϸ� ���α׷��� ����ǹ����� ������ ����ó�� ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); // URL �ּҿ� ������ ��
		byte[] result = con.getInputStream().readAllBytes();

		// 2. byte[] -> String(JSON)���� ��ȯ
		String data = new String(result);
		System.out.println(data);

		// 3. ���ڿ��� Object�� ��ȯ�ϱ�(gson���̺귯�� Ȱ��= google���� ���� json�� java ��ü�� ������ִ� )
		// DustHourlyResponse response = new Gson().fromJson(data,
		// DustHourlyResponse.class);
		DustHourlyResponse response = new Gson().fromJson(data, DustHourlyResponse.class);
		System.out.println(response);

		for (DustHourlyResponse.Item item : response.getResponse().getBody().getItems()) {
			repo.save(new DustHourly(item));
		}

	}

}
