package com.gdg.springmyworkspace.opendata.dust;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@IdClass(DustHourlyId.class)
@NoArgsConstructor
public class DustHourly {

	// dataTime + itemCode를 합쳐서 id를 만듦(primary key) 2개니깐 id class를 따로 생성해서 만들어 준다.
	// DB관점 -> 합성키(Composite Key): 여러개의 컬럼을 합쳐서 Primary Key로 사용함
	// JPA관점 -> 합성Id(Composite Key): 여러개의 필드를 합쳐서 Primary key로 사용함
	@Id
	private String dataTime;
	@Id
	private String itemCode;

	private String seoul;
	private String gyeonggi;
	private String incheon;
	private String gangwon;
	private String sejong;
	private String chungbuk;
	private String chungnam;
	private String daejeon;
	private String gyeongbuk;
	private String gyeongnam;
	private String daegu;
	private String ulsan;
	private String busan;
	private String jeonbuk;
	private String jeonnam;
	private String gwangju;
	private String jeju;

	public DustHourly(DustHourlyResponse.Item item) {
		this.dataTime = item.getDataTime();
		this.itemCode = item.getItemCode();
		this.busan = item.getBusan();
		this.chungbuk = item.getChungbuk();
		this.chungnam = item.getChungnam();
		this.daegu = item.getDaegu();
		this.daejeon = item.getDaejeon();
		this.gangwon = item.getGangwon();
		this.gwangju = item.getGwangju();
		this.gyeongbuk = item.getGyeongbuk();
		this.gyeonggi = item.getGyeonggi();
		this.gyeongnam = item.getGyeongnam();
		this.incheon = item.getIncheon();
		this.jeju = item.getJeju();
		this.jeonbuk = item.getJeonbuk();
		this.jeonnam = item.getJeonnam();
		this.sejong = item.getSejong();
		this.seoul = item.getSeoul();
		this.ulsan = item.getUlsan();
	}

}
