package com.gdg.springmyworkspace.opendata.seawater;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class SeaWaterMonthlyService {

	private SeaWaterRepository repo;
	private String serviceKey = "FRW8yI%2FAIAv2vV%2BGHlNfqCLcKhfhFMEx%2F%2F6Dsiju3kUd%2BG4ZZ%2FRABAx5vMlBaBBhSTA5BV3%2BZh%2BBOKDrELvjgg%3D%3D";

	@Autowired
	public SeaWaterMonthlyService(SeaWaterRepository repo) {
		this.repo = repo;
	}

	// @Scheduled(cron = "0 0 0 15,L 5,6,7,8,9 ?")
	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void requestSeaWaterMonthlyData() throws IOException {
		// ����,�泲,���,�λ�,���,��õ,����,����,����,�泲
		final String[] SIDO_NM = { "����", "�泲", "���", "�λ�", "���", "��õ", "����", "����", "����", "�泲" };

//		for (int j = 0; j < SIDO_NM.length; j++) {
//			int totalCount = getSeaWaterMonthlyData(SIDO_NM[j], Calendar.getInstance().get(Calendar.YEAR));// totalCount��������
//			getSeaWaterMonthlyData(SIDO_NM[j], Calendar.getInstance().get(Calendar.YEAR), totalCount);
//		}

		// 2014�� ~2020�� �����ڷ� ������Ʈ
//		int[] RES_YEAR = { 2014, 2015, 2016, 2017, 2018, 2019, 2020 };
//		for (int i = 0; i < RES_YEAR.length; i++) {
//
//			for (int j = 0; j < SIDO_NM.length; j++) {
//				int totalCount = getSeaWaterMonthlyData(SIDO_NM[j], RES_YEAR[i]);// totalCount��������
//				// System.out.println(RES_YEAR[i] + "�� " + SIDO_NM[j] + "�� �����ڷ� �� ����:" +
//				// totalCount);
//				getSeaWaterMonthlyData(SIDO_NM[j], RES_YEAR[i], totalCount);
//			}
//
//		}

	}

	public int getSeaWaterMonthlyData(String SIDO_NM, int RES_YEAR) throws IOException {

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1");// url
		builder.append("/getOceansBeachSeawaterInfo1");// �󼼱�ɸ�
		builder.append("?ServiceKey=" + serviceKey);// ����Ű
		builder.append("&SIDO_NM=" + URLEncoder.encode(SIDO_NM, "UTF-8"));
		builder.append("&pageNo=1");
		builder.append("&numOfRows=10");// �� �������� ��� ��
		builder.append("&RES_YEAR=" + RES_YEAR);
		builder.append("&resultType=json");// ����������

		// System.out.println(builder.toString());

		// 1. URL ����
		URL url = new URL(builder.toString());

		// 2. URL �ּҷ� ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 3. �޾ƿ� ������ ��������
		byte[] result = con.getInputStream().readAllBytes();

		// 4. ���� data String���� ��ȯ
		String data = new String(result, "UTF-8");
		// System.out.println(data);

		// 5. JSON������ �����͸� java ��ü ���·� ��ȯ
		SeaWaterMonthlyResponse response = new Gson().fromJson(data, SeaWaterMonthlyResponse.class);
		// System.out.println("total count üũ" +
		// response.getGetOceansBeachSeawaterInfo().getTotalCount());
		return response.getGetOceansBeachSeawaterInfo().getTotalCount();
	}

	public void getSeaWaterMonthlyData(String SIDO_NM, int RES_YEAR, int totalCount) throws IOException {

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1");// url
		builder.append("/getOceansBeachSeawaterInfo1");// �󼼱�ɸ�
		builder.append("?ServiceKey=" + serviceKey);// ����Ű
		builder.append("&SIDO_NM=" + URLEncoder.encode(SIDO_NM, "UTF-8"));
		builder.append("&pageNo=1");
		builder.append("&numOfRows=" + totalCount);// �� �������� ��� ��
		builder.append("&RES_YEAR=" + RES_YEAR);
		builder.append("&resultType=json");// ����������

		// System.out.println(builder.toString());

		// 1. URL ����
		URL url = new URL(builder.toString());

		// 2. URL �ּҷ� ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 3. �޾ƿ� ������ ��������
		byte[] result = con.getInputStream().readAllBytes();

		// 4. ���� data String���� ��ȯ
		String data = new String(result, "UTF-8");
		System.out.println(data);

		// 5. JSON������ �����͸� java ��ü ���·� ��ȯ
		SeaWaterMonthlyResponse response = new Gson().fromJson(data, SeaWaterMonthlyResponse.class);

		// System.out.println(response.getGetOceansBeachSeawaterInfo().getItem());
		// List<Item> item= response.getGetOceansBeachSeawaterInfo().getItem();
//		for(int i=0; i<item.; i++) {
//			
//		}
		for (SeaWaterMonthlyResponse.Item item : response.getGetOceansBeachSeawaterInfo().getItem()) {

			// data filtering
			// 1. �������ڰ� null�϶� ���� ����
			// ��ƼƼ Ŭ�������� �����̸Ӹ�Ű�� �ʵ尡 �ΰ��� ���޹��� �� ������ �߻�
			if (item.getRes_date() == null) {
				continue;
			} else {

				try {
					// 2. 500MPN/100mL����(res1), 100MPN/100mL(res2) mpn �ں��� ���ֱ�
					if (item.getRes1() == "500MPN/100ml����" || item.getRes2() == "100MPN/100m")
						continue;

					// 2. ���ڰ� �ƴ� ������ ���ֱ�
					item.setRes1(item.getRes1().replaceAll("\\D", ""));
					item.setRes2(item.getRes2().replaceAll("\\D", ""));
				} catch (NullPointerException e) {
					System.out.println("res �����Ͱ� �����ϴ�.");
				}

			}

			System.out.println("�õ���: " + item.getSido_nm());
			System.out.println("������: " + item.getSta_nm());
			System.out.println("��������" + item.getRes_loc());
			System.out.println("�屸�ռ�: " + item.getRes1());
			System.out.println("����ռ�: " + item.getRes2());
			System.out.println("���տ�������: " + item.getRes_yn());
			System.out.println("����⵵: " + item.getRes_year());
			System.out.println("��������: " + item.getRes_date());
			System.out.println("����: " + item.getLat());
			System.out.println("�浵: " + item.getLon());
			if (item.getRes_loc().equals("A")) {
				System.out.println("�������� " + item.getRes_loc() + "�� ����");
				repo.save(new SeaWaterMonthly(item));
			}

		}
	}

}
