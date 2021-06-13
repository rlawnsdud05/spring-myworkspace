package com.gdg.springmyworkspace.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//왜 interface에다가 repository라고 설정해놨지?
//확실한건 @Repository 어노테션이 붙어있으면 이 인터페이스를 구현한 구현클래스를 생성한다.
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
