package com.gdg.springmyworkspace.todo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	// id������ ������ �Ѱ� ��ȸ
//	@GetMapping(value = "/todos/{id}")
	@RequestMapping(method = RequestMethod.GET, value = ".todos/{id}")
	public Todo getTodo(@PathVariable int id, HttpServletResponse res) {
		// id���� ������ ��ü�� �����ϰ� ������ null�� �����Ѵ�.

		Optional<Todo> todo = repo.findById(id);

		// ���ҽ��� ������ 404 ������ �����
		if (todo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		return todo.get();
	}

// @RequestBody ������̼��� ����ϸ� Json������ HTTP Body ������ Java ��ü�� ��ȯ�����ش�.
//	@PostMapping(value = "/todos")
//	public Todo addTodo(@RequestBody Todo todo) {
//		todo.setCreatedTime(new Date().getTime());
//		return repo.save(todo);
//	}

	@DeleteMapping(value = "/todos/{id}")
	public boolean removeTodo(@PathVariable int id, HttpServletResponse res) {

		Optional<Todo> todo = repo.findById(id);

		// ���ҽ��� ������ 404 ������ �����
		if (todo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// soft - delete : Ư�� �÷��� ������Ʈ�ؼ� ��ȸ�� �� �Ⱥ��̰� ��
		// hard-delete : ������ delete ������ ���ڵ带 ����
		repo.deleteById(id);

		return true;
	}

	@PutMapping(value = "/todos/{id}")
	public Todo modifyTodo(@PathVariable int id, @RequestBody Todo todo, HttpServletResponse res) {

		Optional<Todo> findedTodo = repo.findById(id);
		
		// ���ҽ��� ������ 404 ������ �����
		if (findedTodo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// ��ȸ�� �����Ϳ��� ������ �ʵ常 ����
		Todo toUpdateTodo = findedTodo.get(); // ���� ������
		toUpdateTodo.setMemo(todo.getMemo()); // ������ �ʵ�

		return repo.save(toUpdateTodo);
		// save �޼���� id���� �����ϰ� ��ü �ʵ带 ������Ʈ��
		// return repo.save(todo);
	}

	@PostMapping(value = "/todos")
	public Todo addTodo(@RequestBody Todo todo, HttpServletResponse res) {

		if (todo.getMemo() == null || todo.getMemo().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		todo.setCreatedTime(new Date().getTime());
		return repo.save(todo);

	}

//	@GetMapping(value = "/todos")
//	public List<Todo> getTodoList() {
//
//		// sql���� ��� jpa �Լ��� ���.
//		// �����ͺ��̽��� ���Ӽ��� �����ϱ� ���ؼ� = �����ͺ��̽����� sql���� �ٸ��� �Լ��� ������
//		return repo.findAll(Sort.by("id").descending());
//	}

	// page : ���° page����, size : page�� ��� �����Ͱ� �ִ���
	// /todos/paging/page=?&size=?
	//
	// HTTP GET �޼���� �����͸� ��ȸ�� �� �Ű������� �ѱ�� ���
	// key=value <- �Ű������� Query String(���� ���ڿ�)
	// Spring������ @requestParam ������̼����� �ش� �Ű������� �޴´�.

	@GetMapping(value = "/todos/paging")
	public Page<Todo> getTodoListPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	// POST /todos {memo:"Spring �����ϱ�"} -> jSON ������ ���ڿ�
//	@PostMapping(value = "/todos")
//	public Todo addTodo(@RequestBody Todo todo, HttpServletResponse res) {
//
//		// �����͸� �������� ó���ϴ� ��Ŀ� �°� ������ �ʾ�����
//		if (todo.getMemo() == null || todo.getMemo().equals("")) {
//			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			return null;
//		}
//
//		// Ŭ���̾�Ʈ���� �Ѿ���� �����Ϳ� ���ؼ� ����
//		todo.setCreatedTime(new Date().getTime());
//
//		// id���� �����ϸ� update ������ insert
//		return repo.save(todo);
//
//	}
}
