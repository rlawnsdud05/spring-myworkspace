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

	// @Cacheable 리턴 객체를 캐시함
	// cacheNames: 캐시할 객체의 명칭(임의로 정함)
	// key: 캐시할 객체의 key
	// @Cacheable(cacheNames = "dust-hourly", key = "0")
	@RequestMapping(value = "/opendata/dust/hourly", method = RequestMethod.GET)
	public List<DustHourly> getDustHourlyData() {

		return repo.findAll();
	}

}
