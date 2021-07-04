package com.gdg.springmyworkspace.opendata.seawater;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeaWaterController {

	private SeaWaterRepository repo;

	@Autowired
	public SeaWaterController(SeaWaterRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/opendata/seawater/monthly")
	public List<SeaWaterMonthly> getSeaWaterSidoData() {

		return repo.findAll();
	}
}
