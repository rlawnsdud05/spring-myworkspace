package com.gdg.springmyworkspace.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TodoComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String memo;
	private Long CreatedTime; // Long - nullable <-> long - nonnullable(0)
}
