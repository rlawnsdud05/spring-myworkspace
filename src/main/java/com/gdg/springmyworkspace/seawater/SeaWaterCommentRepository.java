package com.gdg.springmyworkspace.seawater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeaWaterCommentRepository extends JpaRepository<SeaWaterComment, Integer> {

}
