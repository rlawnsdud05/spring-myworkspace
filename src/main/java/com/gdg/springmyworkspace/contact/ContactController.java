package com.gdg.springmyworkspace.contact;

import java.util.Date;
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

	@PostMapping(value = "/contacts/{id}")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) {
		System.out.println("�Ѿ�� ������" + contact);
		if (contact.getName() == null || contact.getName().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		} else if (contact.getPhone() == null || contact.getPhone().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		} else if (contact.getEmail() == null || contact.getEmail().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		contact.setCreatedTime(new Date().getTime());

		// System.out.println(contact);
		return repo.save(contact);
	}

	@PutMapping(value = "/contacts/{id}")
	public Contact modifyContact(@PathVariable int id, @RequestBody Contact contact, HttpServletResponse res) {

		Optional<Contact> findedContact = repo.findById(id);

		// 400 ����ó��
		if (contact.getName() == null || contact.getName().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// ���ҽ��� ������ 404 ������ �����
		if (findedContact.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		Contact toUpdateContact = findedContact.get();

		toUpdateContact.setName(contact.getName());
		toUpdateContact.setPhone(contact.getPhone());
		toUpdateContact.setEmail(contact.getEmail());

		return repo.save(toUpdateContact);
	}

	@DeleteMapping(value = "/contacts/{id}")
	public boolean removeContact(@PathVariable int id, HttpServletResponse res) {
		Optional<Contact> contact = repo.findById(id);

		if (contact.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		repo.deleteById(id);
		return true;
	}

}
