package com.toprate.erp.service.impl;

import com.toprate.erp.model.TaskType;
import com.toprate.erp.repositories.TaskTypeRepository;
import com.toprate.erp.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SonNPT.
 * Date: 12/20/2019
 */
@Service
public class TaskTypeServiceImpl implements TaskTypeService {
    @Autowired
    TaskTypeRepository taskTypeRepository;

    @Override
    public List<TaskType> findAll() {
        List<TaskType> typeList =  taskTypeRepository.findAll();
        return typeList;
    }
}
