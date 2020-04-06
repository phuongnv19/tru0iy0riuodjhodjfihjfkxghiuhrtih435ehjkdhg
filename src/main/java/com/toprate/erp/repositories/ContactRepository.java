package com.toprate.erp.repositories;

import org.springframework.stereotype.Repository;

import com.toprate.erp.model.Contact;
import com.toprate.erp.repositories.common.HibernateRepository;

@Repository
public class ContactRepository extends HibernateRepository<Contact, Long> {
    
}
