package com.gdg.springmyworkspace.opendata.dust;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DustHourlyRepository extends JpaRepository<DustHourly, Long> {

}
