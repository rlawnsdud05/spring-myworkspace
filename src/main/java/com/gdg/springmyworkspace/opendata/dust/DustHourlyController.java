package com.gdg.springmyworkspace.opendata.dust;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DustHourlyController {

	private DustHourlyRepository repo;

	@Autowired
	public DustHourlyController(DustHourlyRepository repo) {
		this.repo = repo;
	}

	@RequestMapping(value = "/opendata/dust/hourly", method = RequestMethod.GET)
	public List<DustHourly> getDustHourlyData() {

		return repo.findAll();
	}

}
