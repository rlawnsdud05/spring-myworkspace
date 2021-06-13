package com.gdg.springmyworkspace.exercise;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseController {

	private ExerciseRepository repo;

	@Autowired
	public ExerciseController(ExerciseRepository repo) {
		this.repo = repo;// TODO Auto-generated constructor stub
	}

	@GetMapping(value = "/exercise/{id}")
	public Optional<Exercise> getExercise(@PathVariable int id) {

//		Optional<Exercise> opEx = Optional.of(repo.getById(id));
//		System.out.println("에러메시지 뜨나? : " + repo.findById(id));
		return repo.findById(id);
	}

}
