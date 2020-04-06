package com.toprate.erp.service;

import com.toprate.erp.dto.ContactDTO;
import com.toprate.erp.model.Contact;

public interface ContactService {

	ContactDTO addContact(ContactDTO contactDTO) throws Exception;

	ContactDTO saveAndUpdate(ContactDTO contactDTO);
	
	Contact findContactById (long contactId);

}
