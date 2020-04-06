package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.*;

import java.util.List;

public interface ProjectManagementService {
    TaskDto insertTask(TaskDto obj) throws Exception;

    TaskAssigneesDto insertSubTask(TaskAssigneesDto obj) throws Exception;

    ProjectDto insertProject(ProjectDto obj) throws Exception;

    TaskCommentDto insertCommentTask(TaskCommentDto obj) throws Exception;

    TaskDto updateTask(TaskDto taskDto) throws Exception;

    TaskAssigneesDto updateSubTask(TaskAssigneesDto obj) throws Exception;

    ProjectDto updateProject(ProjectDto obj) throws Exception;

    boolean deleteTask(TaskDto obj);

    boolean deleteProject(ProjectDto obj);

    List<TaskDto> findAll();

    DataListDTO findAllProject(SearchDTO obj);

    TaskDto getTaskDetail(TaskDto obj) throws Exception;

}
