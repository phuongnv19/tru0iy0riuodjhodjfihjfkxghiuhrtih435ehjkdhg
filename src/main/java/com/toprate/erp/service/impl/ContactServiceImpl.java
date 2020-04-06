package com.toprate.erp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toprate.erp.dto.ContactDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.Contact;
import com.toprate.erp.repositories.ContactRepository;
import com.toprate.erp.service.ContactService;
import com.toprate.erp.util.EmailUtils;
import com.toprate.erp.util.ErpConstants;
import com.toprate.erp.util.MessageUtils;
import com.toprate.erp.util.PhoneUtils;
import com.toprate.erp.util.ValidateUtils;

@Service
public class ContactServiceImpl implements ContactService, ErpConstants {

	private static final String CONTACT_EMAIL = "Contact email";
	private static final String CONTACT_FISTNAME = "Contact fistname";
	private static final String CONTACT_LASTNAME = "Contact lastname";
	private static final String CONTACT_PHONE = "Contact phone";
	private static final int MAX_LENGHT = 255;

	@Autowired
	ContactRepository contactRepository;

	@Override
	public ContactDTO addContact(ContactDTO contactDTO) throws Exception {
		checkRequire(contactDTO);
		checkMaxlength(contactDTO);
		checkFormat(contactDTO);
		long id = contactRepository.insert(contactDTO.toModel());
		contactDTO.setContactId(id);
		return contactDTO;

	}

	@Override
	public ContactDTO saveAndUpdate(ContactDTO contactDTO) {
		contactRepository.update(contactDTO.toModel());
		return contactDTO;
	}

	private void checkFormat(ContactDTO contactDTO) {
		if (!EmailUtils.isEmail(contactDTO.getContactEmail())) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_FORMAT, CONTACT_EMAIL));
		}

		if (!PhoneUtils.isPhone(contactDTO.getContactPhone())) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_FORMAT, CONTACT_PHONE));
		}
	}

	private void checkMaxlength(ContactDTO contactDTO) {
		ValidateUtils.checkMaxlength(contactDTO.getContactEmail(), ErpConstants.PARAM_IS_MAXLENGTH, CONTACT_EMAIL,
				MAX_LENGHT);
		ValidateUtils.checkMaxlength(contactDTO.getContactFirstName(), ErpConstants.PARAM_IS_MAXLENGTH,
				CONTACT_FISTNAME, MAX_LENGHT);
		ValidateUtils.checkMaxlength(contactDTO.getContactLastName(), ErpConstants.PARAM_IS_MAXLENGTH, CONTACT_LASTNAME,
				MAX_LENGHT);

	}

	private void checkRequire(ContactDTO contactDTO) {

		ValidateUtils.checkRequire(contactDTO.getContactEmail(), ErpConstants.PARAM_IS_REQUIRED, CONTACT_EMAIL);
		ValidateUtils.checkRequire(contactDTO.getContactFirstName(), ErpConstants.PARAM_IS_REQUIRED, CONTACT_FISTNAME);
		ValidateUtils.checkRequire(contactDTO.getContactLastName(), ErpConstants.PARAM_IS_REQUIRED, CONTACT_LASTNAME);
		ValidateUtils.checkRequire(contactDTO.getContactPhone(), ErpConstants.PARAM_IS_REQUIRED, CONTACT_PHONE);
	}

	@Override
	public Contact findContactById(long contactId) {
		return contactRepository.find(contactId);
	}

}
