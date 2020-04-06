package com.toprate.erp.repositories;

import com.toprate.erp.model.InventoryCalendar;
import com.toprate.erp.repositories.common.HibernateRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryCalendarRepository  extends HibernateRepository<InventoryCalendar,Long> {
}
