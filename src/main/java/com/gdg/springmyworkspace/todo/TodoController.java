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

	// id값으로 데이터 한건 조회
//	@GetMapping(value = "/todos/{id}")
	@RequestMapping(method = RequestMethod.GET, value = ".todos/{id}")
	public Todo getTodo(@PathVariable int id, HttpServletResponse res) {
		// id값이 있으면 객체를 리턴하고 없으면 null을 리턴한다.

		Optional<Todo> todo = repo.findById(id);

		// 리소스가 없으면 404 에러를 띄워줌
		if (todo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		return todo.get();
	}

// @RequestBody 어노테이션을 사용하면 Json형태의 HTTP Body 내용을 Java 객체로 변환시켜준다.
//	@PostMapping(value = "/todos")
//	public Todo addTodo(@RequestBody Todo todo) {
//		todo.setCreatedTime(new Date().getTime());
//		return repo.save(todo);
//	}

	@DeleteMapping(value = "/todos/{id}")
	public boolean removeTodo(@PathVariable int id, HttpServletResponse res) {

		Optional<Todo> todo = repo.findById(id);

		// 리소스가 없으면 404 에러를 띄워줌
		if (todo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// soft - delete : 특정 컬럼을 업데이트해서 조회할 때 안보이게 함
		// hard-delete : 실제로 delete 문으로 레코드를 삭제
		repo.deleteById(id);

		return true;
	}

	@PutMapping(value = "/todos/{id}")
	public Todo modifyTodo(@PathVariable int id, @RequestBody Todo todo, HttpServletResponse res) {

		Optional<Todo> findedTodo = repo.findById(id);
		
		// 리소스가 없으면 404 에러를 띄워줌
		if (findedTodo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 조회한 데이터에서 변경할 필드만 수정
		Todo toUpdateTodo = findedTodo.get(); // 기존 데이터
		toUpdateTodo.setMemo(todo.getMemo()); // 변경할 필드

		return repo.save(toUpdateTodo);
		// save 메서드는 id값을 제외하고 전체 필드를 업데이트함
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
//		// sql문장 대신 jpa 함수를 사용.
//		// 데이터베이스의 종속성을 배제하기 위해서 = 데이터베이스마다 sql문이 다른데 함수는 데이터
//		return repo.findAll(Sort.by("id").descending());
//	}

	// page : 몇번째 page인지, size : page당 몇개의 데이터가 있는지
	// /todos/paging/page=?&size=?
	//
	// HTTP GET 메서드로 데이터를 조회할 때 매개변수를 넘기는 방법
	// key=value <- 매개변수를 Query String(질의 문자열)
	// Spring에서는 @requestParam 어노테이션으로 해당 매개변수를 받는다.

	@GetMapping(value = "/todos/paging")
	public Page<Todo> getTodoListPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	// POST /todos {memo:"Spring 공부하기"} -> jSON 형태의 문자열
//	@PostMapping(value = "/todos")
//	public Todo addTodo(@RequestBody Todo todo, HttpServletResponse res) {
//
//		// 데이터를 서버에서 처리하는 양식에 맞게 보내지 않앗을때
//		if (todo.getMemo() == null || todo.getMemo().equals("")) {
//			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			return null;
//		}
//
//		// 클라이언트에서 넘어오는 데이터에 대해서 점검
//		todo.setCreatedTime(new Date().getTime());
//
//		// id값이 존재하면 update 없으면 insert
//		return repo.save(todo);
//
//	}
}
