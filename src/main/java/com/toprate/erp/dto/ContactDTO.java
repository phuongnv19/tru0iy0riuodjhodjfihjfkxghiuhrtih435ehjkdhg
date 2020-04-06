package com.toprate.erp.dto;

import javax.validation.constraints.NotNull;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.Contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO extends ErpBaseDTO {

    private Long contactId;

    @AdjHistory(field = "CONTACT_FIRST_NAME")
    private String contactFirstName;

    @AdjHistory(field = "CONTACT_LAST_NAME")
    private String contactLastName;

    @AdjHistory(field = "COMPANY_ID")
    private Long companyId;

    @NotNull
    @AdjHistory(field = "CONTACT_EMAIL")
    private String contactEmail;

    @AdjHistory(field = "CONTACT_PHONE")
    private String contactPhone;

    public Contact toModel(){
        Contact contact = new Contact();
        contact.setContactId(contactId);
        contact.setContactFirstName(contactFirstName);
        contact.setContactLastName(contactLastName);
        contact.setCompanyId(companyId);
        contact.setContactEmail(contactEmail);
        contact.setContactPhone(contactPhone);

        return  contact;
    }

}
