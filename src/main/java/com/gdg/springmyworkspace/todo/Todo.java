package com.gdg.springmyworkspace.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

//@Entity = 데이터베이스의 테이블과 연결함(mapping)
//ORM이라고 한다. Object relational Mapping : 객체와 테이블을 맵핑하는 것
//class와 테이블은 pascal-case -> snake-case로 맵핑
//StudendInfo -> sutdent_info

//필드와 컬럼은 camel-case -> snake-case로 맵핑
// CreatedTime -> created_time

//코드 설계에 따라 데이터베이스가 만들어지는 것 : auto-migration

@Data
@Entity
public class Todo {

	// @Id -> 테이블의 primary key
	// @GeneratedValue : 필드값을 생성하는 방법을 정의하는 것 / IDENTITY : 데이터베이스의 자동 증가값을 사용한다는 의미
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String memo;
	private Long CreatedTime;
}
