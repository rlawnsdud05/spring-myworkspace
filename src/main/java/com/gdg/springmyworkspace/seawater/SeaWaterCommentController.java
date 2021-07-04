package com.gdg.springmyworkspace.seawater;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeaWaterCommentController {

	private SeaWaterCommentRepository repo;

	@Autowired
	public SeaWaterCommentController(SeaWaterCommentRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/seawater-comment")
	public List<SeaWaterComment> getSeaWaterComment() {
		return repo.findAll();
		// comment data찾아서 가져오기

	}

	@PostMapping(value = "/seawater-comment")
	public SeaWaterComment addSeaWaterComment(@RequestBody SeaWaterComment comment) {
		return repo.save(comment);
	}

	@PutMapping(value = "/seawater-comment/{id}")
	public SeaWaterComment modifySeaWaterComment(@PathVariable int id, @RequestBody SeaWaterComment comment,
			HttpServletResponse res) {
		Optional<SeaWaterComment> findedComment = repo.findById(id);

		// 400에러처리
		if (comment.getComment() == null || comment.getComment().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		} else if (findedComment.isEmpty()) {
			// 404에러처리
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		SeaWaterComment toUpdateComment = findedComment.get();
		toUpdateComment.setComment(comment.getComment());
		return repo.save(toUpdateComment);
	}

	@DeleteMapping(value = "/seawater-comment/{id}")
	public boolean removeSeaWaterComment(@PathVariable int id, HttpServletResponse res) {
		Optional<SeaWaterComment> seaWaterComment = repo.findById(id);
		if (seaWaterComment == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		repo.deleteById(id);

		return true;

	}

}
