package com.gdg.springmyworkspace.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

	private TodoRepository repo;

	// @Autowired : ��ü�� Spring IoC �����̳ʿ��� �����Ͽ� ������.
	// DI(Dependency Injection): ������ ����
	// ������ ���� : ��ü�� ����ϴ� ���� �ƴ� �ܺο��� ��ü�� �����Ͽ� �����Ͽ� �ִ� ��

	// ex) TodoController ��ü�� �����Ǵ� ������ @Autowired�� �پ��ִ� ���� ��ü�� �����Ͽ� �־���
	// TodoController �ν��Ͻ��� Spring Ioc���� ������

	// Entity - Repository: JPA(Java Persistence API)
	// Persistence: ����ȭ -> �޸𸮿� �ִ� ��ü�� ��ũ �Ǵ� �����ͺ��̽� ���� ���ֹ߼� ��ġ�� ����

	@Autowired
	public TodoController(TodoRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/todos")
	public List<Todo> getTodoList() {

		return repo.findAll();
	}
}
