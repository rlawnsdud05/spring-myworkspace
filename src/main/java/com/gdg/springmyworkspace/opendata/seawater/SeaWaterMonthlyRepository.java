package com.gdg.springmyworkspace.opendata.seawater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeaWaterMonthlyRepository extends JpaRepository<SeaWaterMonthly, Integer> {

}
