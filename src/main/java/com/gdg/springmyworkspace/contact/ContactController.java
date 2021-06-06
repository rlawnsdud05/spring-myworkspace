package com.gdg.springmyworkspace.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	private ContactRepository contact;

	@Autowired
	public ContactController(ContactRepository contact) {
		this.contact = contact;
	}

	@GetMapping(value = "/contacts")
	public List<Contact> getContactList() {
		return contact.findAll();
	}
}
