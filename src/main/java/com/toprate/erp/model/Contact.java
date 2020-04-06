package com.toprate.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.toprate.erp.dto.ContactDTO;

import lombok.Data;

@Entity
@Table(name = "CONTACT")
@Data
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTACT_ID")
	private Long contactId;

	@Column(name = "CONTACT_FIRST_NAME")
	private String contactFirstName;

	@Column(name = "CONTACT_LAST_NAME")
	private String contactLastName;

	@Column(name = "COMPANY_ID")
	private Long companyId;

	@Column(name = "CONTACT_EMAIL")
	private String contactEmail;

	@Column(name = "CONTACT_PHONE")
	private String contactPhone;

	public ContactDTO toDto() {
		ContactDTO contact = new ContactDTO();
		contact.setContactId(contactId);
		contact.setContactFirstName(contactFirstName);
		contact.setContactLastName(contactLastName);
		contact.setCompanyId(companyId);
		contact.setContactEmail(contactEmail);
		contact.setContactPhone(contactPhone);

		return contact;
	}
}
