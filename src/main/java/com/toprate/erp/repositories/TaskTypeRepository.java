package com.toprate.erp.repositories;

import com.toprate.erp.model.TaskType;
import com.toprate.erp.repositories.common.HibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SonNPT.
 * Date: 12/20/2019
 */
@Repository
public class TaskTypeRepository extends HibernateRepository<TaskType, Long> {
}
