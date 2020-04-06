package com.toprate.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toprate.erp.dto.ContactDTO;
import com.toprate.erp.service.impl.ContactServiceImpl;

@RestController
@RequestMapping("/v1")
public class ContactController {

	@Autowired
	ContactServiceImpl contactService;

	@PostMapping("/contact/createContact")
	public ResponseEntity<?> create(@RequestBody ContactDTO contactDTO) throws Exception {
		return new ResponseEntity(contactService.addContact(contactDTO), HttpStatus.CREATED);
	}

}
