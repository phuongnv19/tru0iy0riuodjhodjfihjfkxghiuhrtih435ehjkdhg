package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.ResultDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.*;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.Projects;
import com.toprate.erp.model.SysUser;
import com.toprate.erp.model.TaskAssignees;
import com.toprate.erp.model.Tasks;
import com.toprate.erp.repositories.ProjectRepository;
import com.toprate.erp.repositories.TaskAssigneesRepository;
import com.toprate.erp.repositories.TaskCommentRepository;
import com.toprate.erp.repositories.TaskRepository;
import com.toprate.erp.service.ProjectManagementService;
import com.toprate.erp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * ProjectManagementServiceImpl.java
 */
@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskAssigneesRepository taskAssigneesRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskCommentRepository taskCommentRepository;

    @Override
    public TaskDto insertTask(TaskDto obj) throws Exception {
        Long id = taskRepository.insert(obj.toModel());
        obj.setTaskId(id);
        return obj;
    }

    @Override
    public TaskAssigneesDto insertSubTask(TaskAssigneesDto obj) throws Exception {
        Tasks tasks = taskRepository.findByFiled("taskId", obj.getTaskId());
        if (tasks == null) {
            throw new BusinessException(MessageUtils.getMessage("taskId.not.exist"));
        }
        Long id = taskAssigneesRepository.insert(obj.toModel());
        obj.setAssigneeId(id);
        return obj;
    }

    @Override
    public ProjectDto insertProject(ProjectDto obj) throws Exception {
        obj.setCreatedTime(new Date());
        Long id = projectRepository.insert(obj.toModel());
        obj.setProjectId(id);
        return obj;
    }

    @Override
    public TaskCommentDto insertCommentTask(TaskCommentDto obj) throws Exception {
        obj.setModifiedTime(new Date());
        Long id = taskCommentRepository.insert(obj.toModel());
        obj.setCommentId(id);
        return obj;
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) throws Exception {

        Tasks tasks = taskRepository.findByFiled("taskId", taskDto.getTaskId());
        if (tasks == null) {
            throw new BusinessException(MessageUtils.getMessage("taskId.not.exist"));
        }
        tasks.setPhaseId(taskDto.getPhaseId());
        tasks.setModifiedTime(new Date());
        taskRepository.update(tasks);
        return tasks.toDto();
    }

    @Override
    public TaskAssigneesDto updateSubTask(TaskAssigneesDto obj) throws Exception {
        TaskAssignees taskAssignees = taskAssigneesRepository.findByFiled("assigneeId", obj.getAssigneeId());
        if (taskAssignees == null) {
            throw new BusinessException(MessageUtils.getMessage("assigneeId.not.exist"));
        }
        taskAssigneesRepository.update(obj.toModel());
        return obj;
    }

    @Override
    public ProjectDto updateProject(ProjectDto obj) throws Exception {
        Projects projects = projectRepository.findByFiled("projectId", obj.getProjectId());
        if (projects == null) {
            throw new BusinessException(MessageUtils.getMessage("projectId.not.exist"));
        }
        projectRepository.update(obj.toModel());
        return obj;
    }

    @Override
    public boolean deleteTask(TaskDto taskDto) {
        //delete task
        Tasks tasks = taskRepository.findByFiled("taskId", taskDto.getTaskId());
        if (tasks == null) {
            return false;
        } else {
            taskRepository.delete(tasks);
            List<TaskAssignees> tasksAssignees = taskAssigneesRepository.find("taskId", taskDto.getTaskId());
            //delete TaskAssignees
            if (tasksAssignees != null) {
                for (TaskAssignees task : tasksAssignees) {
                    taskAssigneesRepository.delete(task);
                }
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean deleteProject(ProjectDto obj) {
        Projects projects = projectRepository.findByFiled("projectId", obj.getProjectId());
        if (projects == null) {
            return false;
        }
        projectRepository.delete(projects);
        List<Tasks> listTask = taskRepository.find("projectId", obj.getProjectId());
        if (listTask != null) {
            removeListTask(listTask);
            return true;
        }
        return false;
    }

    private void removeListTask(List<Tasks> listTask) {
        //delete list task
        for (Tasks task : listTask) {
            deleteTask(task.toDto());
        }
    }

    @Override
    public List<TaskDto> findAll() {
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<Tasks> listTaskRes = taskRepository.findAll();
        if (listTaskRes != null && !listTaskRes.isEmpty()) {
            for (Tasks tasks : listTaskRes) {
                taskDtoList.add(tasks.toDto());
            }
        }
        return taskDtoList;
    }

    @Override
    public DataListDTO findAllProject(SearchDTO obj) {
        List<ProjectDto> ls = projectRepository.getAllProject(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

    @Override
    public TaskDto getTaskDetail(TaskDto obj) throws Exception {
        TaskDto ls = taskRepository.getTaskDetail(obj);

        setNameForType(ls);

        List<TaskAssigneesDto> taskAssigneesList = taskAssigneesRepository.getTaskAssigneesDto(obj.getTaskId());
        if (taskAssigneesList == null) {
            throw new BusinessException(MessageUtils.getMessage("taskId.not.exist"));
        }
        ls.setTaskAssigneesList(taskAssigneesList);
        //get list sub task
        List<TaskDto> listSubTask = taskRepository.getListSubTask(obj);
        List<TaskDto> listTask =  new ArrayList();
        if (listSubTask != null || listSubTask.isEmpty()) {
            for (TaskDto dto : listSubTask) {
                setNameForType(dto);
                listTask.add(dto);
            }
            ls.setListSubTask(listTask);
        }
        //get comment for task
        List<TaskCommentDto> taskCommentDtos = taskCommentRepository.getCommentTask(obj.getTaskId());
        if (taskCommentDtos != null || taskCommentDtos.isEmpty()) {
            ls.setListComment(taskCommentDtos);
        }

        return ls;
    }

    private void setNameForType(TaskDto ls) {
        if (ls.getIsPinned() == 0) {
            ls.setIsPinnedName("No");
        } else if (ls.getIsPinned() == 1) {
            ls.setIsPinnedName("Yes");
        }

        if (ls.getPriorityLevel() != null) {
            if (ls.getPriorityLevel() == 0) {
                ls.setPriorityName("Low");
            } else if (ls.getPriorityLevel() == 1) {
                ls.setPriorityName("Medium");
            } else if (ls.getPriorityLevel() == 2) {
                ls.setPriorityName("High");
            } else if (ls.getPriorityLevel() == 3) {
                ls.setPriorityName("Urgent");
            }
        }

        if (ls.getTaskTypeId() != null) {
            if (ls.getTaskTypeId() == 1) {
                ls.setTaskTypeName("Task");
            } else if (ls.getTaskTypeId() == 2) {
                ls.setTaskTypeName("Story");
            } else if (ls.getTaskTypeId() == 3) {
                ls.setTaskTypeName("Sub-task");
            }
        }

        if (ls.getTaskStatus() != null) {
            if (ls.getTaskStatus() == 1) {
                ls.setTaskStatusName("New");
            } else if (ls.getTaskStatus() == 2) {
                ls.setTaskStatusName("Started");
            } else if (ls.getTaskStatus() == 3) {
                ls.setTaskStatusName("Finished");
            }
        }
    }
}
