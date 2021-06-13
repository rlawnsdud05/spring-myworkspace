package com.gdg.springmyworkspace.contact;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	private ContactRepository repo;

	@Autowired
	public ContactController(ContactRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/contacts")
	public List<Contact> getContactList() {
		return repo.findAll();
	}

	@PutMapping(value = "/contacts/{id}")
	public Contact modifyContact(@PathVariable int id, @RequestBody Contact contact, HttpServletResponse res) {

		Optional<Contact> findedContact = repo.findById(id);

		// 리소스가 없으면 404 에러를 띄워줌
		if (findedContact.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 400 에러처리
		if (contact.getName() == null || contact.getName().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Contact toUpdateContact = findedContact.get();

		toUpdateContact.setName(contact.getName());
		toUpdateContact.setPhone(contact.getPhone());
		toUpdateContact.setEmail(contact.getEmail());

		return repo.save(toUpdateContact);
	}
}
