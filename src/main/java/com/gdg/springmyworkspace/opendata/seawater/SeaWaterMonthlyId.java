package com.gdg.springmyworkspace.opendata.seawater;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeaWaterMonthlyId implements Serializable {

	private String sta_nm;
	private String res_year;
	private String res_date;

}
