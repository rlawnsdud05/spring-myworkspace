package com.gdg.springmyworkspace.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

	private TodoRepository repo;

	// @Autowired : 객체를 Spring IoC 컨테이너에서 생성하여 주입함.
	// DI(Dependency Injection): 의존성 주입
	// 의존성 주입 : 객체를 사용하는 곳이 아닌 외부에서 객체를 생성하여 주입하여 주는 것

	// ex) TodoController 객체가 생성되는 시점에 @Autowired이 붙어있는 곳에 객체를 생성하여 넣어줌
	// TodoController 인스턴스는 Spring Ioc에서 생성함

	// Entity - Repository: JPA(Java Persistence API)
	// Persistence: 영속화 -> 메모리에 있는 객체를 디스크 또는 데이터베이스 같이 비휘발성 장치에 저장

	@Autowired
	public TodoController(TodoRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/todos")
	public List<Todo> getTodoList() {

		return repo.findAll();
	}
}
